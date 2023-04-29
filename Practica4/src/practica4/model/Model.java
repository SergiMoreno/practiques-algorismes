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
    //private ArrayList <Route> routes;
    //private double[][] solucion;
    private String type = "";
    
    public Model(Main main) {
        this.main = main;
        this.poblations = new ArrayList<>();
        //this.routes = new ArrayList<>();
        //solucion = null;
        this.type = "nodirigido";
    }
    
    public void addPoblation(String n) {
        this.poblations.add(new Poblation(n));
    }
    
    public void addRoute(String namei, String namef, double v) {
        Route r = new Route(getPoblation(namef), v);
        getPoblation(namei).addRoute(r);
    }
    
    public void setType(String t) {
        this.type = t;
    }
    
    /*public Poblation getPoblation(int index) {
        
    }*/
    
    public Poblation getPoblation(String name) {
        boolean res = false;
        Poblation p = null;
        
        for (int i = 0; i < this.poblations.size() && !res; i++) {
            if (name.contentEquals((this.poblations.get(i)).getName())) {
                res = true;
                p = this.poblations.get(i);
            }
        }
        
        if (p == null) {
            //Errores.informaError(new Exception("Nodo inexistente."));
            System.out.println("Poblacion inexistente");
        }
        return p;
    }

    @Override
    public void notify(Event e) {

    }
    
}
