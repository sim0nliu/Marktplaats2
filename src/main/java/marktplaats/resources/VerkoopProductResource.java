package marktplaats.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("verkoop")
public class VerkoopProductResource {

    @GET
    public Response helloworld2() {
        return Response.ok().entity("HOI").build();
    }
}
