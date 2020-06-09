package Marktplaats.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import java.util.List;

@Getter
@Setter
@Entity
//@Inheritance(strategy = TABLE_PER_CLASS)
public class Gebruiker extends AbstracteEntiteit {

    @Column(unique = true)
    @Email
    protected String email;
    protected String wachtwoord;

    @OneToMany(cascade = CascadeType.ALL)
    protected List<Artikel> lijstVanTeVerkopenArtikelen;

    public Gebruiker() {
    }

    public Gebruiker(String email, String wachtwoord) {
        this.email = email;
        this.wachtwoord = wachtwoord;
    }


}
