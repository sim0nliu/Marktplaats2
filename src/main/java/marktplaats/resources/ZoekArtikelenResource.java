package marktplaats.resources;

import marktplaats.domain.Artikel;
import marktplaats.domain.Gebruiker;
import marktplaats.domain.Product;
import marktplaats.dto.ArtikelDto;
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
        List<Artikel> artikelen = zoekArtikelenService.getArtikelen(4);
        List<Product> producten = zoekArtikelenService.getProducten(4);
        return mapArtikelEnProductNaarDto(artikelen, producten);
    }

    private ArtikelDto mapArtikelEnProductNaarDto(List<Artikel> artikelen, List<Product> producten) {
        Artikel artikel = artikelen.get(0);
        Product product = producten.get(0);
        ArtikelDto dto = new ArtikelDto();

        dto.setId(artikel.getId());
        dto.setArtikelNaam(artikel.getArtikelNaam());
        dto.setPrijs(artikel.getPrijs());
        dto.setOmschrijving(artikel.getOmschrijving());
        //dto.setVerkoper(artikel.getVerkoper());
        dto.setBod(artikel.isBod());
        dto.setTijdVanPlaatsen(artikel.getTijdVanPlaatsen());

        //dto.setBezorgwijzen(product.getBezorgwijzen());
        dto.setBijlagen(product.getBijlagen());
        //dto.setCategories(product.getCategorie());

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
        List<Gebruiker> gebruikers = zoekArtikelenService.getGeberuikers(1);
        return gebruikers.get(0);
    }
}
