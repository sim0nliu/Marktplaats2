package Marktplaats.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
//@Inheritance(strategy = TABLE_PER_CLASS)
public class Gebruiker extends AbstracteEntiteit {

    @Column(unique = true)
    @Email
    private String email;
    private String wachtwoord;
    private String adres;

    @NotNull
    @ElementCollection
    @CollectionTable(name = "bezorgwijzeVerkoper")
    @Enumerated(EnumType.STRING)
    private List<Bezorgwijze> bezorgwijzen;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Artikel> lijstVanTeVerkopenArtikelen;


    public Gebruiker() {
    }

    public Gebruiker(String email, String wachtwoord) {
        this.email = email;
        this.wachtwoord = wachtwoord;
    }


}
