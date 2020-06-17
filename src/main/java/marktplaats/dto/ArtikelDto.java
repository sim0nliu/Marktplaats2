package marktplaats.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import marktplaats.domain.Categorie;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Data
public class ArtikelDto {
    private long id;
    private String artikelNaam;
    private BigDecimal prijs;
    private String omschrijving;
    private VerkoperDto verkoper;
    private LocalDate tijdVanPlaatsen;
    private byte[] bijlagen;
    private boolean bod;
    private List<BezorgwijzeDto> bezorgwijzen;
    private String categorie;
    private List<CategorieDto> categories;
    private List<String> categorieen;

    public ArtikelDto() {
    }

    public void setCategories(CategorieDto categorie) {
        if (categories == null) {
            categories = new ArrayList<>();
        }
        categories.add(categorie);
    }

    public void setBezorgwijze(BezorgwijzeDto bezorgwijze) {
        if (bezorgwijzen == null) {
            bezorgwijzen = new ArrayList<>();
        }
        bezorgwijzen.add(bezorgwijze);
    }


   // public Set<Categorie> getCategorie() {
       // return Arrays.asList(stringCategorieNaarCategorie(categorie));
    //}

    public Categorie stringCategorieNaarCategorie(String stringCategorie) {
        return new Categorie(stringCategorie);
    }
}
