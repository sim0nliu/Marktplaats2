package marktplaats.services;

import marktplaats.dao.GebruikerDao;
import marktplaats.dao.exceptions.GebruikerNotFoundException;
import marktplaats.domain.Gebruiker;
import marktplaats.domain.Gebruikers;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Stateless
public class RegistrerenService {

    @Inject
    GebruikerDao gebruikerDao;

    public boolean add(Gebruiker gebruiker) {
        try {
            if(!gebruiker.isActiefAccount()){
                gebruiker.setActiefAccount(true);
            }
            gebruikerDao.gebruikerToevoegen(gebruiker);
            return true;
        } catch (javax.persistence.RollbackException ex) {
            return false;
        }
    }

    public Gebruiker find(String email) throws GebruikerNotFoundException {
        return gebruikerDao.selecteerOpEmail(email);
    }

    public boolean bestaatGebruiker(String email){
        return gebruikerDao.bestaatGebruiker(email);
    }



}
