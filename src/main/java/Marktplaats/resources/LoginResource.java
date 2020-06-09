package Marktplaats.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("login")
public class LoginResource {

    @GET
    public Response hello() {
        return Response.ok().entity("LoginScherm").build();
    }

   /* @GET
    public Response helloworld(@CookieParam("gebruikerNaam") String gebruikerNaam, @CookieParam("gebruikerWachtwoord") String gebruikerWachtwoord) {
        return Response.ok().entity("Login").build();
    }*/
}
