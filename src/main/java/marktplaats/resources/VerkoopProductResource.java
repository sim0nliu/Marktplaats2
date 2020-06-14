package marktplaats.resources;

import marktplaats.dao.ArtikelDao;
import marktplaats.domain.Bezorgwijze;
import marktplaats.domain.Categorie;
import marktplaats.domain.Product;
import marktplaats.dto.ArtikelDto;
import marktplaats.dto.BezorgwijzeDto;
import marktplaats.dto.CategorieDto;
import marktplaats.dto.VerkoperDto;
import marktplaats.services.VerkoopProductService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Path("verkoop")
public class VerkoopProductResource {

    @Inject
    VerkoopProductService verkoopProductService;

    @Inject
    ArtikelDao artikelDao;

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

    //TODO Fix methode
    @POST
    @Path("testAngular")
    @Consumes(MediaType.APPLICATION_JSON)
    public ArtikelDto postArtikel(
            @FormParam(value = "productNaam") String productNaam,
            @FormParam(value = "categorie") String categorie,
            @FormParam(value = "omschrijving") String omschrijving,
            @FormParam(value = "prijs") double prijs,
            @FormParam(value = "bijlage") String bijlage,
            @FormParam(value = "verzendmethode") String verzendmethode
    ) {
        Product teVerkopenProduct = new Product(
                Arrays.asList(new Categorie(categorie)),
                productNaam,
                omschrijving,
                new BigDecimal(prijs),
                //TODO parameter meenemen in bezorgwijze
                Arrays.asList(Bezorgwijze.Versturen));

        verkoopProductService.verkoopArtikel(teVerkopenProduct);

//        ArtikelDto result = mapProductNaarDto(Arrays.asList(teVerkopenProduct));
//        return Response.status(201).entity(result).build();
        return mapProductNaarDto(Arrays.asList(teVerkopenProduct));

    }

    @POST
    @Path("item")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ArtikelDto addArtikel() {
        Product teVerkopenProduct = new Product(
                Arrays.asList(new Categorie("Nieuwe Categorie1")),
                "Nieuw Product1",
                "Nieuwe Omschrijving1",
                new BigDecimal("1338"),
                Arrays.asList(Bezorgwijze.Versturen));

        verkoopProductService.verkoopArtikel(teVerkopenProduct);

        return mapProductNaarDto(Arrays.asList(teVerkopenProduct));
    }

    private ArtikelDto mapProductNaarDto(List<Product> producten) {
        Product product = producten.get(0);
        ArtikelDto dto = new ArtikelDto();

        dto.setId(product.getId());
        dto.setArtikelNaam(product.getArtikelNaam());
        dto.setPrijs(product.getPrijs());
        dto.setOmschrijving(product.getOmschrijving());
        dto.setVerkoper(mapVerkoperNaarDto(producten));
        dto.setBod(product.isBod());
        dto.setTijdVanPlaatsen(product.getTijdVanPlaatsen());
//        dto.setBezorgwijzen(product.getBezorgwijzen());
        dto.setBijlagen(product.getBijlagen());

        for (int i = 0; i < product.getBezorgwijzen().size(); i++) {
            dto.setBezorgwijze(mapBezorgwijzeNaarDto(producten, i));
        }

        for (Categorie categorie : product.getCategorie()) {
            dto.setCategories(mapCategorieNaarDto(producten));
        }
        return dto;
    }

    private BezorgwijzeDto mapBezorgwijzeNaarDto(List<Product> producten, int index) {
        Product product = producten.get(0);
        BezorgwijzeDto dto = new BezorgwijzeDto();

        dto.setBezorgwijze(product.getBezorgwijzen().get(index).name());

        return dto;
    }

    private CategorieDto mapCategorieNaarDto(List<Product> producten) {
        Product product = producten.get(0);
        CategorieDto dto = new CategorieDto();

        dto.setCategorieNaam(product.getCategorie().get(0).getCategorieNaam());

        return dto;
    }

    private VerkoperDto mapVerkoperNaarDto(List<Product> producten) {
        Product product = producten.get(0);
        VerkoperDto dto = new VerkoperDto();

        dto.setId(product.getVerkoper().getId());
        dto.setEmail(product.getVerkoper().getEmail());

        return dto;
    }

}
