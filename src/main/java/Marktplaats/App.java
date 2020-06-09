package Marktplaats;

import Marktplaats.services.MainService;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("main")
public class App extends Application {
//    public static void main(String[] args) {
//        Weld weld = new Weld();
//        WeldContainer container = weld.initialize();
//
//
//        MainService mainService = container.select(MainService.class).get();
////        mainService.start();
}
