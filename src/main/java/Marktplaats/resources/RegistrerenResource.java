package Marktplaats.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("registreren")
public class RegistrerenResource {

    @GET
    public Response helloworld() {
        return Response.ok().entity("HOI").build();
    }
}

