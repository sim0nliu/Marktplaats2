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
import javax.json.Json;
import javax.json.JsonObject;
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
    @GET @Path("canBeUsed")
    public Response checkemail(@QueryParam("email") String email) {
        System.out.println("something happened to canBeUsed");
        System.out.println("email = "+ email);
        boolean bestaat = registrerenService.bestaatGebruiker(email);
        System.out.println("bestaat: "+ bestaat);
        boolean valideEmail = registrerenService.isEmailSlechtFormat(email);
        if(email == null){
            throw new NotFoundException("ontvangen email is null");
        }
        if(bestaat){
            JsonObject response = bouwJsonFalse(email);
            return Response.ok().entity(response).build();
        }else {
            JsonObject response = bouwJsonTrue(email);
            return Response.ok().entity(response).build();
        }
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET @Path("q") // read
    public GebruikerDto get(@QueryParam("email") String email) {
        try {
            GebruikerDto uitvoer = gebruikerService.mapGebruikerNaarDto(registrerenService.find(email));
            System.out.println(uitvoer);
            return uitvoer;
        }catch (GebruikerNotFoundException e) {
            throw new NotFoundException("Gebruiker niet gevonden");
        }
    }

    //TODO: gebruik localization file, ipv van return strings in code.
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public GebruikerDto post(GebruikerDto gebruikerDto){
        Gebruiker gebruiker;
        try{
            gebruiker = gebruikerService.mapDtoNaarGebruiker(gebruikerDto);
            if(registrerenService.add(gebruiker)){
                return gebruikerService.mapGebruikerNaarDto(gebruiker);
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


    private JsonObject bouwJsonFalse(String email){
        return bouwJson(email, false);
    }

    private JsonObject bouwJsonTrue(String email){
        return bouwJson(email, true);
    }

    private JsonObject bouwJson(String email, boolean toegestaan){
        if(toegestaan){
            return Json.createObjectBuilder()
                    .add("email", email)
                    .add("allowed", "true")
                    .build();
        }else{
            return Json.createObjectBuilder()
                    .add("email", email)
                    .add("allowed", "false")
                    .build();
        }
    }
}

