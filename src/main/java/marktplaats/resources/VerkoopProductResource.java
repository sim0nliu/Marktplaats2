package marktplaats.resources;

import marktplaats.domain.Artikel;
import marktplaats.services.VerkoopProductService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("verkoop")
@Produces(MediaType.APPLICATION_JSON)
public class VerkoopProductResource {

    @Inject
    VerkoopProductService verkoopProductService;

    @GET
    public Response helloworld2() {
        return Response.ok().entity("HOI").build();
    }

    @GET
    @Path("test")
    public String testArtikelZoeken() {
        List<Artikel> artikel = verkoopProductService.getArtikel(4);
        System.out.println("Artikel: " + artikel.get(0).getArtikelNaam());
        return "[" + artikel.get(0).getArtikelNaam() + "]";
    }

//    @GET
//    @Path("categorieen")
//    public Categorie getAlleCategorieen() {
//        return verkoopProductService.getAlleCategorieen();
//    }
}
