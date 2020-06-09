package Marktplaats.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
public class Product extends Artikel {

    @NotNull
    @ElementCollection
    @CollectionTable(name = "bezorgwijzeProduct")
    @Enumerated(EnumType.STRING)
    protected List<Bezorgwijze> bezorgwijzen;

    //BLOB
    protected byte bijlagen[];

    public Product() {
    }

    public Product(List<Categorie> categorie, String artikelNaam, String omschrijving, BigDecimal prijs, List<Bezorgwijze> bezorgwijzen) {
        setCategorie(categorie);
        setArtikelNaam(artikelNaam);
        setOmschrijving(omschrijving);
        setPrijs(prijs);
        setBezorgwijzen(bezorgwijzen);
    }

//    public void setBezorgwijzen(List<Bezorgwijze> bezorgwijzen) {
//        this.bezorgwijzen = bezorgwijzen;
//    }
}
