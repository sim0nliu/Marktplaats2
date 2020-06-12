package marktplaats.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
    @CollectionTable(name = "bezorgwijzeproduct")
    @Enumerated(EnumType.STRING)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Bezorgwijze> bezorgwijzen;

    //BLOB
    private byte[] bijlagen;

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
