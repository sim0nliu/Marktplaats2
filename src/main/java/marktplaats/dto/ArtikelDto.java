package marktplaats.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import marktplaats.domain.Categorie;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Data
public class ArtikelDto {
    private long id;

    private String artikelNaam;
    private String categorie;
    private BigDecimal prijs;
    private String omschrijving;
    private byte[] bijlagen;
    private String verzendmethode;

    private List<BezorgwijzeDto> bezorgwijzen;
    private VerkoperDto verkoper;
    private LocalDate tijdVanPlaatsen;
    private boolean bod;
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

    public String getCategorie() {
        return categorie;
    }

    public Set<Categorie> stringCategorieNaarSetCategorie(String stringCategorie) {
        Set<Categorie> categorieSet = new HashSet<>();
        categorieSet.add(new Categorie(stringCategorie));
        return categorieSet;
    }
}
