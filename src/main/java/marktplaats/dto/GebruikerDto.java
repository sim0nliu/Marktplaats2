package marktplaats.dto;

import marktplaats.domain.Bezorgwijze;
import lombok.Data;

import java.util.Set;

@Data
public class GebruikerDto {
    private long id;
    private String email;
    private boolean regelementAkkoord;
    private String adres;
    private Set<Bezorgwijze> bezorgwijzen;
    private String wachtwoord;
}
