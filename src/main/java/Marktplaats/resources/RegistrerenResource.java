package Marktplaats.resources;

import Marktplaats.domain.Gebruiker;
import Marktplaats.services.RegistrerenService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("registreren")
public class RegistrerenResource {


    @Inject
    RegistrerenService registrerenService;

    @GET
    public Response helloworld() {
        return Response.ok().entity("HOI").build();
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

