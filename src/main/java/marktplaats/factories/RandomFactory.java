package marktplaats.factories;

import marktplaats.dao.GebruikerDao;
import marktplaats.domain.Gebruiker;
import marktplaats.domain.exceptions.InvalidEmailException;
import marktplaats.domain.exceptions.InvalidPasswordException;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class RandomFactory {

    @Inject
    GebruikerFactory gebruikerFactory;

    @Inject
    GebruikerDao gebruikerDao;

    public boolean filldatabase()  {

        try {
        /*
        Categorie decoratie = new Categorie("decoratie");
        Categorie kleding = new Categorie("kleding");
        Categorie computer = new Categorie("computer");
        Categorie tuinonderhoud = new Categorie("grasmaaien");
        Categorie tuinplanten = new Categorie("tuinplanten");
        */

            Gebruiker merchandise = gebruikerFactory.create(GebruikerType.BEZOEKER, "merchandise@Belastingdienst.nl", "merchWoord1");
            Gebruiker randomGebruikerTuin = gebruikerFactory.create(GebruikerType.BEZOEKER, "groeneVingers@email.nl", "groenWoord1");
            Gebruiker randomGebruikerDeco = gebruikerFactory.create(GebruikerType.BEZOEKER, "goudenHanden@email.nl", "goudWoord1");
            merchandise.setRegelementAkkoord(true);
            randomGebruikerDeco.setRegelementAkkoord(true);
            randomGebruikerTuin.setRegelementAkkoord(true);
/*
        Artikel graszaad = new Artikel("overgebleven graszaad 250 g", tuinplanten, "1.00", randomGebruikerTuin);
        Artikel grasmaaien = new Artikel("grasmaaien door mijn zoon, prijs per m2", tuinonderhoud, "0.20", randomGebruikerTuin);
        Artikel lkinmtshirt = new Artikel("Leuker kan ik het niet maken t-shirt", kleding, "7.50", merchandise);
        Artikel schilderen = new Artikel("Eigengemaakt schilderij", decoratie, "50.00", randomGebruikerDeco);

        ArtikelDao artikelDao = new ArtikelDao(em);
        GebruikerDao gebruikerDao = new GebruikerDao(em);
        CategorieDao categorieDao = new CategorieDao(em);
*/
            //categorieDao.insert(decoratie,kleding,tuinonderhoud,tuinplanten,computer);
            gebruikerDao.gebruikersToevoegen(randomGebruikerDeco, randomGebruikerTuin, merchandise);
            //artikelDao.insert(graszaad,grasmaaien,lkinmtshirt,schilderen);
            return true;
        }catch (InvalidPasswordException e) {
            e.printStackTrace();
            return false;
        } catch (InvalidEmailException e){
            e.printStackTrace();
            return false;
        }

    }
}
