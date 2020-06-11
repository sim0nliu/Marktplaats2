package marktplaats.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class Categorie extends AbstracteEntiteit {
    private String categorieNaam;

    @ManyToOne(cascade = CascadeType.ALL)
    private Artikel artikel;

    public Categorie() {
    }

    public Categorie(String categorieNaam) {
        this.categorieNaam = categorieNaam;
    }

    public void setArtikel(Artikel artikel) { this.artikel = artikel; }
}
