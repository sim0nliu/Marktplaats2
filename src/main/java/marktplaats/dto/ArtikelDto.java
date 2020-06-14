package marktplaats.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private boolean bod;
    private LocalDate tijdVanPlaatsen;
    private List<BezorgwijzeDto> bezorgwijzen;
    private byte[] bijlagen;
    private List<CategorieDto> categories;
    private List<String> categorien;

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
}
