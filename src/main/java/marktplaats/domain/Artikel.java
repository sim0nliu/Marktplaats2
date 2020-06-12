package marktplaats.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter @Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Artikel extends AbstracteEntiteit {

    @NotNull
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artikel")
    @LazyCollection(LazyCollectionOption.FALSE)
    protected List<Categorie> categorie = new ArrayList<>();

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
}
