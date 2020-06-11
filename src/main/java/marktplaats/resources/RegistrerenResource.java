package marktplaats.resources;

import marktplaats.dao.exceptions.GebruikerNotFoundException;
import marktplaats.domain.Gebruiker;
import marktplaats.domain.exceptions.InvalidEmailException;
import marktplaats.domain.exceptions.InvalidPasswordException;
import marktplaats.dto.GebruikerDto;
import marktplaats.factories.RandomFactory;
import marktplaats.services.GebruikerService;
import marktplaats.services.RegistrerenService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("registreren")
public class RegistrerenResource {


    @Inject
    RegistrerenService registrerenService;

    @Inject
    GebruikerService gebruikerService;

    @Inject
    RandomFactory randomFactory;


    @GET
    public Response helloworld() {
        return Response.ok().entity("HOI").build();
    }

    @GET @Path("factory")
    public Response factory()
    {
        try {
            randomFactory.filldatabase();
            return Response.ok().entity("Database gevuld met test vulling!").build();
        }catch (javax.ejb.EJBTransactionRolledbackException ex){
            return Response.ok().entity("Database heeft al vulling!").build();
        }
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET @Path("q") // read
    public Gebruiker get(@QueryParam("email") String email) {
        try {
            return registrerenService.find(email);
        }catch (GebruikerNotFoundException e) {
            throw new RuntimeException("Gebruiker niet gevonden");
        }
    }

    //TODO: gebruik localization file, ipv van return strings in code.
    @POST
    public Gebruiker post(GebruikerDto gebruikerDto){
        Gebruiker gebruiker;
        try{
            gebruiker = gebruikerService.mapDtoNaarGebruiker(gebruikerDto);
            if(registrerenService.add(gebruiker)){
                return gebruiker;
            }else {
                if(registrerenService.bestaatGebruiker(gebruiker.getEmail()))
                {
                    throw new RuntimeException(
                            "Eeen Account met dit email adres kan niet worden gemaakt, waarschijnlijk omdat het al bestaat"
                    );
                }else{
                    throw new RuntimeException("database failure");}
            }
        } catch (InvalidPasswordException e) {
            throw new RuntimeException("Ingevoerd Wachtwoord is niet toegestaan");
        } catch (InvalidEmailException e) {
            throw new RuntimeException("Ingevoerd Emailadres is niet toegestaan");
        }
    }
}

