package marktplaats.resources;

import marktplaats.dao.ArtikelDao;
import marktplaats.domain.Categorie;
import marktplaats.services.VerkoopProductService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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

    @Inject
    ArtikelDao artikelDao;

    @GET
    @Path("categorieen")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlleDistinctCategorieen() {
        List<Categorie> categorieen = verkoopProductService.getCategorieen();
        return Response.ok(categorieen).build();
    }

//    //TODO Fix methode
//    @POST
//    @Path("testAngular")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response postArtikel(ArtikelDto artikelDto) {
//
//        Product product = verkoopProductService.mapProductDtoNaarProduct(artikelDto);
//
//        System.out.println(product.getArtikelNaam());
//        System.out.println(product.getCategorie());
//        System.out.println(product.getOmschrijving());
//        System.out.println(product.getPrijs());
//
//        return Response.status(201).entity("OK").build();
//    }

//    @POST
//    @Path("testAngular")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response postArtikel(
//            @FormParam(value = "productNaam") String artikelNaam,
//            @FormParam(value = "categorie") String categorie,
//            @FormParam(value = "omschrijving") String omschrijving,
//            @FormParam(value = "prijs") double prijs,
//            @FormParam(value = "bijlage") String bijlage,
//            @FormParam(value = "verzendmethode") String verzendmethode
//    ) {
//        Product teVerkopenProduct = new Product(
//                Arrays.asList(new Categorie(categorie)),
//                artikelNaam,
//                omschrijving,
//                new BigDecimal(prijs),
//                //TODO parameter meenemen in bezorgwijze
//                Arrays.asList(Bezorgwijze.Versturen));
//
//        verkoopProductService.verkoopArtikel(teVerkopenProduct);
//
////        ArtikelDto result = mapProductNaarDto(Arrays.asList(teVerkopenProduct));
////        return Response.status(201).entity(result).build();
//        mapProductNaarDto(Arrays.asList(teVerkopenProduct));
//        return Response.status(201).entity("OK").build();
//
//    }

//    @POST
//    @Path("testAngular")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void addArtikel(String inputStream) {
//
//        Pattern p = Pattern.compile("\"([^\"]*)\"");
//        Matcher m = p.matcher(inputStream);
//
//        int counter = 0;
//
//        String productNaam = "";
//        String categorie = "";
//        String omschrijving = "";
//        String prijs = "";
//        String bijlage = "";
//        String verzendmethode = "";
//
//        while (m.find()) {
//            counter++;
//            switch (m.group(1)) {
//                case "productNaam":
//                    System.out.println("case: productNaam");
//                    counter = 0;
//                    break;
//                case "categorie":
//                    System.out.println("case: categorie ");
//                    counter = 10;
//                    break;
//                case "omschrijving":
//                    System.out.println("case: omschrijving ");
//                    counter = 20;
//                    break;
//                case "prijs":
//                    System.out.println("case: prijs ");
//                    counter = 30;
//                    break;
//                case "bijlage":
//                    System.out.println("case: bijlage ");
//                    counter = 40;
//                    break;
//                case "verzendmethode":
//                    System.out.println("case: verzendmethode ");
//                    counter = 50;
//                    break;
//            }
//
//            if (counter < 10 && counter > 0) {
//                System.out.println("counter < 10 > 0" + m.group(1));
//                productNaam += m.group(1);
//            } else if (counter < 20 && counter > 10) {
//                System.out.println("counter < 20 > 10" + m.group(1));
//                System.out.println(m.group(1));
//                categorie += m.group(1);
//            } else if (counter < 30 && counter > 20) {
//                System.out.println("counter < 30 > 20" + m.group(1));
//                System.out.println(m.group(1));
//                omschrijving += m.group(1);
//            } else if (counter < 40 && counter > 30) {
//                System.out.println("counter < 40 > 30" + m.group(1));
//                System.out.println(m.group(1));
//                prijs += m.group(1);
//            } else if (counter < 50 && counter > 40) {
//                System.out.println("counter < 50 > 40" + m.group(1));
//                System.out.println(m.group(1));
//                bijlage += m.group(1);
//            } else if (counter < 60 && counter > 50) {
//                System.out.println("counter < 60 > 50" + m.group(1));
//                System.out.println(m.group(1));
//                verzendmethode += m.group(1);
//            }
//        }
//
//        Product teVerkopenProduct = new Product(
//                Arrays.asList(new Categorie(categorie)),
//                productNaam,
//                omschrijving,
//                new BigDecimal(Integer.parseInt(prijs)),
//                Arrays.asList(Bezorgwijze.Versturen));
//
//        verkoopProductService.verkoopArtikel(teVerkopenProduct);
//
//        mapProductNaarDto(Arrays.asList(teVerkopenProduct));
//    }
//
//    @POST
//    @Path("item")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Artikel addArtikel() {
//
//        Product teVerkopenProduct = new Product(
//                new HashSet<>(singletonList(new Categorie("Nieuwe Categorie"))),
//                "Nieuw Product",
//                "Nieuwe Omschrijving",
//                new BigDecimal("1337"),
//                Arrays.asList(Bezorgwijze.Versturen));
//
//        return verkoopProductService.verkoopArtikel(teVerkopenProduct);
//    }


    //    @POST
//    @Path("item")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public ArtikelDto addArtikel() {
//        Product teVerkopenProduct = new Product(
//                Arrays.asList(new Categorie("Nieuwe Categorie1")),
//                "Nieuw Product1",
//                "Nieuwe Omschrijving1",
//                new BigDecimal("1338"),
//                Arrays.asList(Bezorgwijze.Versturen));
//
//        verkoopProductService.verkoopArtikel(teVerkopenProduct);
//
//        return mapProductNaarDto(Arrays.asList(teVerkopenProduct));
//    }
//
//    public ArtikelDto mapProductNaarDto(List<Product> producten) {
//        Product product = producten.get(0);
//        ArtikelDto dto = new ArtikelDto();
//
//        dto.setId(product.getId());
//        dto.setArtikelNaam(product.getArtikelNaam());
//        dto.setPrijs(product.getPrijs());
//        dto.setOmschrijving(product.getOmschrijving());
//        dto.setVerkoper(mapVerkoperNaarDto(producten));
//        dto.setBod(product.isBod());
//        dto.setTijdVanPlaatsen(product.getTijdVanPlaatsen());
//        dto.setBijlagen(product.getBijlagen());
//
//        for (int i = 0; i < product.getBezorgwijzen().size(); i++) {
//            dto.setBezorgwijze(mapBezorgwijzeNaarDto(producten, i));
//        }
//
//        for (Categorie categorie : product.getCategorie()) {
//            dto.setCategories(mapCategorieNaarDto(producten));
//        }
//        return dto;
//    }
//
//    private BezorgwijzeDto mapBezorgwijzeNaarDto(List<Product> producten, int index) {
//        Product product = producten.get(0);
//        BezorgwijzeDto dto = new BezorgwijzeDto();
//
//        dto.setBezorgwijze(product.getBezorgwijzen().get(index).name());
//
//        return dto;
//    }
//
//    private CategorieDto mapCategorieNaarDto(List<Product> producten) {
//        Product product = producten.get(0);
//        CategorieDto dto = new CategorieDto();
//
//        dto.setCategorieNaam(product.getCategorie().get(0).getCategorieNaam());
//
//        return dto;
//    }
//
//    private VerkoperDto mapVerkoperNaarDto(List<Product> producten) {
//        Product product = producten.get(0);
//        VerkoperDto dto = new VerkoperDto();
//
//        dto.setId(product.getVerkoper().getId());
//        dto.setEmail(product.getVerkoper().getEmail());
//
//        return dto;
//    }
}
