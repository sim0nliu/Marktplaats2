package marktplaats.resources;

import marktplaats.domain.*;
import marktplaats.dto.ArtikelDto;
import marktplaats.dto.BezorgwijzeDto;
import marktplaats.dto.CategorieDto;
import marktplaats.dto.VerkoperDto;
import marktplaats.services.ZoekArtikelenService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("zoek")
public class ZoekArtikelenResource {

    @Inject
    ZoekArtikelenService zoekArtikelenService;

    @GET
    public Response helloworld1() {
        return Response.ok().entity("HOI").build();
    }


    @GET
    @Path("artikel")
    @Produces(MediaType.APPLICATION_JSON)
    public ArtikelDto testArtikelZoeken() {
        List<Product> producten = zoekArtikelenService.getProducten(4);
//        List<Artikel> artikelen = zoekArtikelenService.getArtikelen(4);
        return mapProductNaarDto(producten);
    }

    @GET
    @Path(("artikel2"))
    @Produces(MediaType.APPLICATION_JSON)
    public Response testArtikel() {
        List<Product> producten = zoekArtikelenService.getProducten(4);
        return Response.ok(producten).build();
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

        for (int i = 0; i < product.getBezorgwijzen().size(); i++)  {
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

    @GET
    @Path("string")
    public String testArtikelZoekeString() {
        List<Artikel> artikel = zoekArtikelenService.getArtikelen(4);
        System.out.println("Artikel: " + artikel.get(0).getArtikelNaam());
        return artikel.get(0).getArtikelNaam();
    }

    @GET
    @Path("gebruiker")
    @Produces(MediaType.APPLICATION_JSON)
    public Gebruiker testGebruikerZoeken() {
        List<Gebruiker> gebruikers = zoekArtikelenService.getGebruiker(1);
        return gebruikers.get(0);
    }
}
