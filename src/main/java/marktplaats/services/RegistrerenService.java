package marktplaats.services;

import marktplaats.dao.GebruikerDao;
import marktplaats.domain.Gebruiker;

import javax.inject.Inject;

public class RegistrerenService {

    @Inject
    GebruikerDao gebruikerDao;

    public boolean add(Gebruiker gebruiker) {
        try {
            gebruikerDao.gebruikerToevoegen(gebruiker);
            return true;
        } catch (javax.persistence.RollbackException ex) {
            return false;
        }
    }
}
