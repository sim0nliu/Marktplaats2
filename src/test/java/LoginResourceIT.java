import Marktplaats.App;
import Marktplaats.resources.LoginResource;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.net.URL;

import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class LoginResourceIT {

    @ArquillianResource
    private URL deploymentURL;

    private String loginResource;

    @Before
    public void setup() {
        loginResource = deploymentURL + "main/login";
        //loginResource = "http://localhost:9080/Marktplaats_war_exploded/" + "main/login";
    }

    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive archive = ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(App.class) // dont forget!
                .addPackage(LoginResource.class.getPackage());
        System.out.println(archive.toString(true));
        return archive;
    }

    @Test
    public void loginResourceIT() {
        String message = ClientBuilder.newClient()
                .target(loginResource)
                //.target("http://localhost:9080/Marktplaats_war_exploded/main/login/")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(String.class);

        System.out.println(deploymentURL);
        System.out.println(loginResource);

        assertTrue(true);

    }


}
