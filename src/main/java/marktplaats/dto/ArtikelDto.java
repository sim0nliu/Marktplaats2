package marktplaats.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import marktplaats.domain.Categorie;
import marktplaats.domain.Gebruiker;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class ArtikelDto {
    private long id;
    private String artikelNaam;
    private BigDecimal prijs;
    private String omschrijving;
    private Gebruiker verkoper;
    private boolean bod;
    private LocalDate tijdVanPlaatsen;
    private byte[] bijlagen;
    private List<Categorie> categories;
    private boolean afhalenThuis;
    private boolean afhalenMagazijn;
    private boolean versturen;
    private boolean versturenOnderRembours;

    public ArtikelDto() { }
}
