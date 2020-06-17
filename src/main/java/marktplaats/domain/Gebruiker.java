package marktplaats.domain;

import lombok.Getter;
import lombok.Setter;
import marktplaats.domain.exceptions.InvalidEmailException;
import marktplaats.domain.exceptions.InvalidPasswordException;
import marktplaats.util.EmailChecker;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

// import javax.validation.constraints.Email;

@Getter
@Setter
@Entity
//@Inheritance(strategy = TABLE_PER_CLASS)
public class Gebruiker extends AbstracteEntiteit {

    // @Email
    // 2020/06/15: email spec defines a max length of 254 characters,
    // database spec specifies maximum length of 250 characters for a unique constraint
    @NotNull
    @Column(unique = true, columnDefinition = "VARCHAR(64)")
    protected String email;

    private String adres;

    @NotNull
    @ElementCollection(fetch = EAGER)
    @CollectionTable(name = "bezorgwijzeVerkoper")
    @Enumerated(EnumType.STRING)
    private Set<Bezorgwijze> bezorgwijzen;

    @Lob
    @NotNull
    private byte[] wachtwoord;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "verkoper", fetch = EAGER)
    private List<Artikel> lijstVanTeVerkopenArtikelen;

    @NotNull
    boolean regelementAkkoord;

    @NotNull
    boolean actiefAccount;

    public Gebruiker() {
    }

    public Gebruiker(String email, String wachtwoord) throws InvalidPasswordException, InvalidEmailException {
        init(email);
        setWachtwoord(wachtwoord);
    }

    private void init(String emailAdress) throws InvalidEmailException {
        setEmailAdress(emailAdress);
        regelementAkkoord = false;
        actiefAccount = true;
    }

    public void setEmailAdress(String emailAdress) throws InvalidEmailException {
        if (EmailChecker.isValideEmail(emailAdress)) {
            this.email = emailAdress;
        } else {
            throw new InvalidEmailException();
        }

    }

    //TODO: response entity moet exception afvangen
    public void setWachtwoord(String password) throws InvalidPasswordException {
        if (!checkPassword(password, this.email)) {
            throw new InvalidPasswordException();
        }
        this.wachtwoord = encodePassword(password);
    }

    public void voegArtikelToeAanLijstVanTeVerkopenArtikelen(Artikel artikel) {
        this.lijstVanTeVerkopenArtikelen.add(artikel);
        artikel.setVerkoper(this);
    }

    private byte[] encodePassword(String password) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex.getCause() + " " + ex.getMessage());
        }
        byte[] encodedhash = digest.digest(
                password.getBytes(StandardCharsets.UTF_8));
        return encodedhash;
    }

    public boolean verifyPassword(char[] password) {
        StringBuilder passwordS = new StringBuilder("");
        for (char x : password) {
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
        if (passwordToBeChecked.length() < 6
                || passwordToBeChecked.equals(email)
                || containsNONumber(passwordToBeChecked)
                || containsNoLetter(passwordToBeChecked)) {
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

    public long getId() {
        return this.id;
    }

    public static boolean containsNoLetter(String tobechecked) {
        return !tobechecked.matches(".*[a-zA-Z]+.*");
    }


}
