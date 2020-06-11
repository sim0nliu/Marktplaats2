package marktplaats.resources;

import marktplaats.dao.exceptions.GebruikerNotFoundException;
import marktplaats.domain.Gebruiker;
import marktplaats.factories.RandomFactory;
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

    @POST
    public Gebruiker post(Gebruiker gebruiker){
        if(registrerenService.add(gebruiker)){
            return gebruiker;
        }else {
            throw new RuntimeException("database failure");
        }
    }

}

