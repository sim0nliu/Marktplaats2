package marktplaats.factories;

import marktplaats.domain.Bezorgwijze;
import marktplaats.domain.Gebruiker;
import marktplaats.domain.exceptions.InvalidEmailException;
import marktplaats.domain.exceptions.InvalidPasswordException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.List;

public class GebruikerFactory {
    private final List<Bezorgwijze> standaardBezorgwijze = Arrays.asList(new Bezorgwijze[]{Bezorgwijze.AfhalenMagazijn});

    public Gebruiker create(GebruikerType gebruikerType, String email, String Password) throws InvalidPasswordException, InvalidEmailException {
        switch (gebruikerType) {
            case BEZOEKER:
                return creeerBezoeker(email, Password);
            case MEDEWERKER:
            case BEHEERDER:
            default:
                throw new NotImplementedException();
        }
    }

    private Gebruiker creeerBezoeker(String email) throws InvalidEmailException, InvalidPasswordException {
        Gebruiker intern = new Gebruiker(email, "standaardWW1");
        intern.setBezorgwijzen(standaardBezorgwijze);
        return intern;
    }

    private Gebruiker creeerBezoeker(String email, String password) throws InvalidPasswordException, InvalidEmailException {
        Gebruiker intern = new Gebruiker(email, "standaardWW1");
        intern.setBezorgwijzen(standaardBezorgwijze);
        return intern;
    }
}
