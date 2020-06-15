package marktplaats.services;

import marktplaats.domain.Gebruiker;
import marktplaats.domain.exceptions.InvalidEmailException;
import marktplaats.domain.exceptions.InvalidPasswordException;
import marktplaats.dto.GebruikerDto;
import marktplaats.factories.GebruikerFactory;
import marktplaats.factories.GebruikerType;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class GebruikerService {

    @Inject
    private GebruikerFactory gebruikerFactory;

    public GebruikerDto mapGebruikerNaarDto(Gebruiker gebruiker) {
        GebruikerDto dto = new GebruikerDto();
        dto.setId(gebruiker.getId());
        dto.setEmail(gebruiker.getEmail());
        dto.setRegelementAkkoord(gebruiker.isRegelementAkkoord());
        dto.setAdres(gebruiker.getAdres());
        dto.setBezorgwijzen(gebruiker.getBezorgwijzen());
        dto.setWachtwoord("wachtwoord kan niet worden getoond");
        return dto;
    }

    public Gebruiker mapDtoNaarGebruiker(GebruikerDto gebruikerDto) throws InvalidEmailException, InvalidPasswordException {
        Gebruiker gebruiker = gebruikerFactory.create(
                GebruikerType.BEZOEKER,
                gebruikerDto.getEmail(),
                gebruikerDto.getWachtwoord()
        );
        gebruiker.setRegelementAkkoord(gebruikerDto.isRegelementAkkoord());
        gebruiker.setAdres(gebruikerDto.getAdres());
        gebruiker.setBezorgwijzen(gebruikerDto.getBezorgwijzen());
        return gebruiker;
    }
}
