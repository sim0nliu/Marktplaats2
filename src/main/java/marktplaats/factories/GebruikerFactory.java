package marktplaats.factories;

import marktplaats.domain.Gebruiker;
import marktplaats.domain.exceptions.InvalidEmailException;
import marktplaats.domain.exceptions.InvalidPasswordException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class GebruikerFactory {

    public Gebruiker create(GebruikerType gebruikerType, String email, String Password) throws InvalidPasswordException, InvalidEmailException {
        switch (gebruikerType){
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
        return intern;
    }

    private Gebruiker creerBezoeker(String email, String password) throws InvalidPasswordException, InvalidEmailException {
        Gebruiker intern = new Gebruiker(email, "standaardWW1");
        return intern;
    }
}
