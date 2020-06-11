package marktplaats.domain;

import marktplaats.domain.exceptions.InvalidPasswordException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import java.util.List;

@Getter
@Setter
@Entity
//@Inheritance(strategy = TABLE_PER_CLASS)
public class Gebruiker extends AbstracteEntiteit {

    @Column(unique = true)
    @Email
    @NotNull
    protected String email;

    private String adres;

    @NotNull
    @ElementCollection
    @CollectionTable(name = "bezorgwijzeverkoper")
    @Enumerated(EnumType.STRING)
    private List<Bezorgwijze> bezorgwijzen;

    @Lob
    @NotNull
    private byte[] wachtwoord;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "verkoper")
    private List<Artikel> lijstVanTeVerkopenArtikelen;


    @NotNull
    boolean regelementAkkoord;

    @NotNull
    boolean actiefAccount;

    public Gebruiker() {
    }

    public Gebruiker(String email, String wachtwoord) throws InvalidPasswordException {
        this.email = email;
        setWachtwoord(wachtwoord);
    }

    //TODO: response entity moet exception afvangen
    public void setWachtwoord(String password) throws InvalidPasswordException {
        if(!checkPassword(password,this.email)) {
            throw new InvalidPasswordException();
        }
        this.wachtwoord = encodePassword(password);
    }

    public void verkoopArtikel(Artikel artikel){
        this.lijstVanTeVerkopenArtikelen.add(artikel);
        artikel.setVerkoper(this);
    }

    private byte[] encodePassword(String password)  {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        }catch (NoSuchAlgorithmException ex)
        {
            throw new RuntimeException(ex.getCause()+" "+ex.getMessage());
        }
        byte[] encodedhash = digest.digest(
                password.getBytes(StandardCharsets.UTF_8));
        return encodedhash;
    }

    public boolean verifyPassword(char[] password){
        StringBuilder passwordS = new StringBuilder("");
        for (char x :password){
            passwordS.append(x);
        }
        return verifyPassword(passwordS.toString());
    }

    public boolean verifyPassword(String password) {
        byte[] verier;
        verier = encodePassword(password);
        System.gc();
        return Arrays.equals(verier, this.wachtwoord);
    }

    public static boolean checkPassword(String passwordToBeChecked, String email) {
        if (passwordToBeChecked.length() < 9
                || passwordToBeChecked.equals(email)
                || containsNONumber(passwordToBeChecked)) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean containsNONumber(String tobechecked) {
        return !tobechecked.matches(".*\\d.*");
    }

    public void addArtikel(Artikel artikel) {
        lijstVanTeVerkopenArtikelen.add(artikel);
        artikel.setVerkoper(this);
    }
}
