package marktplaats.dto;

import lombok.Data;
import marktplaats.domain.Bezorgwijze;

import java.util.List;
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
