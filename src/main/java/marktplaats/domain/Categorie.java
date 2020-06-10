package marktplaats.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;


@Getter
@Setter
@Entity
public class Categorie extends AbstracteEntiteit {
    private String categorieNaam;

    public Categorie() {
    }

    public Categorie(String categorieNaam) {
        this.categorieNaam = categorieNaam;
    }
}
