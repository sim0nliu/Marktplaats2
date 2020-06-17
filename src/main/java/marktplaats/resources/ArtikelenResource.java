package marktplaats.resources;

import marktplaats.domain.Artikel;
import marktplaats.domain.Categorie;
import marktplaats.domain.Gebruiker;
import marktplaats.domain.Product;
import marktplaats.dto.ArtikelDto;
import marktplaats.dto.BezorgwijzeDto;
import marktplaats.dto.CategorieDto;
import marktplaats.dto.VerkoperDto;
import marktplaats.services.ZoekArtikelenService;
import marktplaats.services.ArtikelenService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("artikelen")
public class ArtikelenResource {

    @Inject
    ZoekArtikelenService zoekArtikelenService;

    @Inject
    private ArtikelenService artikelenService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ArtikelDto> alleProducten() {
        List<ArtikelDto> temp = new ArrayList<>();
        List<Product> producten = zoekArtikelenService.getAlleProducten();
        for (Product p : producten) {
            temp.add(mapProductNaarDto(p));
        }
        return temp;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ArtikelDto> productOpBasisVan(@PathParam("id") long id) {
        List<ArtikelDto> temp = new ArrayList<>();
        List<Product> producten = zoekArtikelenService.getProducten(id);
        for (Product p : producten) {
            temp.add(mapProductNaarDto(p));
        }
        return temp;
    }

    private ArtikelDto mapProductNaarDto(Product product) {
        ArtikelDto dto = new ArtikelDto();

        dto.setId(product.getId());
        dto.setArtikelNaam(product.getArtikelNaam());
        dto.setPrijs(product.getPrijs());
        dto.setOmschrijving(product.getOmschrijving());
        dto.setVerkoper(mapVerkoperNaarDto(product));
        dto.setBod(product.isBod());
        dto.setTijdVanPlaatsen(product.getTijdVanPlaatsen());
        dto.setBijlagen(product.getBijlagen());

        for (int i = 0; i < product.getBezorgwijzen().size(); i++) {
            dto.setBezorgwijze(mapBezorgwijzeNaarDto(product, i));
        }

        for (Categorie categorie : product.getCategorie()) {
            dto.setCategories(mapCategorieNaarDto(product));
        }
        return dto;
    }

    private BezorgwijzeDto mapBezorgwijzeNaarDto(Product product, int index) {
        BezorgwijzeDto dto = new BezorgwijzeDto();

        dto.setBezorgwijze(product.getBezorgwijzen().get(index).name());

        return dto;
    }

    private CategorieDto mapCategorieNaarDto(Product product) {
        CategorieDto dto = new CategorieDto();

        for (Categorie cat : product.getCategorie()) {
            dto.setCategorieNaam(cat.getCategorieNaam());
        }

        return dto;
    }

    private VerkoperDto mapVerkoperNaarDto(Product product) {
        VerkoperDto dto = new VerkoperDto();

        dto.setId(product.getVerkoper().getId());
        dto.setEmail(product.getVerkoper().getEmail());

        return dto;
    }

    @GET
    @Path("categorieen")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlleDistinctCategorieen() {
        List<Categorie> categorieen = artikelenService.getCategorieen();
        return Response.ok(categorieen).build();
    }

    @POST
    @Path("verkoopArtikel")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response postArtikel(ArtikelDto artikelDto) {

        Product teVerkopenProduct = artikelenService.mapProductDtoNaarProduct(artikelDto);
        artikelenService.verkoopProduct(teVerkopenProduct);

        return Response.status(201).build();
    }
}
