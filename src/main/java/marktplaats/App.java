package marktplaats;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import static marktplaats.App.appPath;

@ApplicationPath(appPath)
public class App extends Application {
    public static final String appPath = "main";
}
