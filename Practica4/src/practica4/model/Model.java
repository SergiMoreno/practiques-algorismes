package practica4.model;

import java.util.ArrayList;
import practica4.Event;
import practica4.EventListener;
import practica4.Main;

/**
 *
 * @author usuario
 */
public class Model implements EventListener {
    private Main main;
    private ArrayList <Poblation> poblations;
    private ArrayList <Route> routes;
    //private double[][] solucion;
    private String type = "";
    
    public Model(Main main) {
        this.main = main;
        this.poblations = new ArrayList<>();
        this.routes = new ArrayList<>();
        //solucion = null;
        this.type = "nodirigido";
    }
    
    public void addPoblation(String n) {
        this.poblations.add(new Poblation(n));
    }
    
    public void addRoute(double v) {
        this.routes.add(new Route(v));
    }
    
    public void setType(String t) {
        this.type = t;
    }

    @Override
    public void notify(Event e) {

    }
    
}
