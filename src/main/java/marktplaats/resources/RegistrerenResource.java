package marktplaats.resources;

import marktplaats.dao.exceptions.GebruikerNotFoundException;
import marktplaats.domain.Gebruiker;
import marktplaats.factories.RandomFactory;
import marktplaats.services.RegistrerenService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
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
        randomFactory.filldatabase();
        return Response.ok().entity("Database gevuld met test vulling!").build();
    }


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

