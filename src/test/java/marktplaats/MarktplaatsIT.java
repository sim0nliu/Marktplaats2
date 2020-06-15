package marktplaats;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.net.URL;

import static marktplaats.App.appPath;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Arquillian.class)
public class MarktplaatsIT {

    @ArquillianResource
    private URL deploymentURL;

    private final String slash = "/";

    private String artikelenResource;
    private String loginResource;

    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive webArchive = ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackages(true, App.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml") // voor JPA
                .addAsResource("test-beans.xml", "META-INF/beans.xml"); // voor CDI

        System.out.println(webArchive.toString(true));

        return webArchive // dont forget!
                // .addClass(ZoekArtikelenService.class)
                // .addClass(ZoekDao.class)
                // .addPackage(Artikel.class.getPackage())
                // .addPackage(ArtikelDto.class.getPackage())
                // .addPackage(ArtikelenResource.class.getPackage())
                ;
    }

    @Before
    public void setup() {
        artikelenResource = deploymentURL + appPath + slash + "artikelen";
        loginResource = deploymentURL + appPath + slash + "login";
        System.out.println("------------------------------------" + artikelenResource);
    }

    @Test @Ignore
    public void testArtikelZoeken() {
        Client postman = ClientBuilder.newClient();

        String message = postman
                .target(artikelenResource + "/4")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);

        assertThat(message, containsString("{\"id\":"));
    }
}