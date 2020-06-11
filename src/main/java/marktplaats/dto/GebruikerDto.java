package marktplaats.dto;

import lombok.Data;

@Data
public class GebruikerDto {
    private String email;
    private boolean regelementAkkoord;
    private boolean actiefAccount;
    private String adres;

}
