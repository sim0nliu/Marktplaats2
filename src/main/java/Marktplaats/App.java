package Marktplaats;

import Marktplaats.services.MainService;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class App {
    public static void main(String[] args) {
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();


        MainService mainService = container.select(MainService.class).get();
//        mainService.start();
    }
}
