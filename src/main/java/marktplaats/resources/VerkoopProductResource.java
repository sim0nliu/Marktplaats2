package marktplaats.resources;

import marktplaats.domain.Artikel;
import marktplaats.domain.Bezorgwijze;
import marktplaats.domain.Categorie;
import marktplaats.domain.Product;
import marktplaats.services.VerkoopProductService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static java.util.Collections.singletonList;

@Path("verkoop")
public class VerkoopProductResource {

    @Inject
    VerkoopProductService verkoopProductService;

    @GET
    public Response helloworld2() {
        return Response.ok().entity("HOI").build();
    }

    @GET
    @Path("categorieen")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlleDistinctCategorieen() {
        List<Categorie> categorieen = verkoopProductService.getCategorieen();
        return Response.ok(categorieen).build();
    }

//    @POST
//    @Path("testAngular")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void post(
//            @FormParam(value = "productNaam") String productNaam,
//            @FormParam(value = "categorie") String categorie,
//            @FormParam(value = "omschrijving") String omschrijving,
//            @FormParam(value = "prijs") double prijs,
//            @FormParam(value = "bijlage") String bijlage,
//            @FormParam(value = "verzendmethode") String verzendmethode
//    ) {
//        new Product(
//                Arrays.asList(new Categorie(categorie)),
//                productNaam,
//                omschrijving,
//                new BigDecimal(prijs),
//                Arrays.asList(Bezorgwijze.VERSTUREN));
//    }

    @POST
    @Path("item")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Artikel addArtikel() {

        Product teVerkopenProduct = new Product(
                new HashSet<>(singletonList(new Categorie("Nieuwe Categorie"))),
                "Nieuw Product",
                "Nieuwe Omschrijving",
                new BigDecimal("1337"),
                Arrays.asList(Bezorgwijze.Versturen));

        return verkoopProductService.verkoopArtikel(teVerkopenProduct);
    }

}
