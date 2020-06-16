package marktplaats.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Artikel extends AbstracteEntiteit {

    @NotNull
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artikel", fetch = FetchType.EAGER)
    protected Set<Categorie> categorie = new HashSet<>();

    @NotNull
    protected String artikelNaam;

    @NotNull
    protected BigDecimal prijs;

    protected String omschrijving;

    @ManyToOne(cascade = CascadeType.ALL)
    protected Gebruiker verkoper;

    protected boolean bod = false;

    LocalDate tijdVanPlaatsen = LocalDate.now();

    public void addCategorie(Categorie categorie) {
        this.categorie.add(categorie);
        categorie.setArtikel(this);
    }

    public void setVerkoper(Gebruiker gebruiker) {
        this.verkoper = gebruiker;
    }

    public long getId() {
        return id;
    }

    public void setCategorie(String categorieNaam) {
        Categorie categorie = new Categorie(categorieNaam);
        this.categorie.add(categorie);
    }

    public void setCategorie(Set<Categorie> categorie) {
        this.categorie = categorie;
    }
}
