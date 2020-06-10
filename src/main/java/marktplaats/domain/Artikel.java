package marktplaats.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter @Setter
@Entity
public class Artikel extends AbstracteEntiteit {

    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
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
}
