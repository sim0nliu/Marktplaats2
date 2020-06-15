package marktplaats.factories;

import marktplaats.domain.Bezorgwijze;
import marktplaats.domain.Gebruiker;
import marktplaats.domain.exceptions.InvalidEmailException;
import marktplaats.domain.exceptions.InvalidPasswordException;
import marktplaats.domain.exceptions.NotImplementedException;

import javax.enterprise.context.Dependent;
import java.util.Arrays;
import java.util.List;

@Dependent
public class GebruikerFactory {

    private final List<Bezorgwijze> standaardBezorgwijze = Arrays.asList(Bezorgwijze.AfhalenMagazijn);

    public Gebruiker create(GebruikerType gebruikerType, String email, String Password) throws InvalidPasswordException, InvalidEmailException {
        switch (gebruikerType) {
            case BEZOEKER:
                return creerBezoeker(email, Password);
            case MEDEWERKER:
            case BEHEERDER:
            default:
                throw new NotImplementedException();
        }
    }

    private Gebruiker creerBezoeker(String email) throws InvalidEmailException, InvalidPasswordException {
        Gebruiker intern = new Gebruiker(email, "standaardWW1");
        intern.setBezorgwijzen(standaardBezorgwijze);
        return intern;
    }

    private Gebruiker creerBezoeker(String email, String password) throws InvalidPasswordException, InvalidEmailException {
        Gebruiker intern = new Gebruiker(email, "standaardWW1");
        intern.setBezorgwijzen(standaardBezorgwijze);
        return intern;
    }
}
