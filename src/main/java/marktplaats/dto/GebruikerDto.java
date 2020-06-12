package marktplaats.dto;

import lombok.Data;
import marktplaats.domain.Bezorgwijze;

import java.util.List;

@Data
public class GebruikerDto {
    private long id;
    private String email;
    private boolean regelementAkkoord;
    private String adres;
    private List<Bezorgwijze> bezorgwijzen;
    private String wachtwoord;
}
