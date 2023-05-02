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
    private int origin, mid, dest;
    
    
    
    public Model(Main main) {
        this.main = main;
        this.poblations = new ArrayList<>();
        this.routes = new ArrayList<>();
        //solucion = null;
        this.type = "nodirigido";
        origin = 0;
        mid = 7;
        dest = 11;
    }
    
    public void addPoblation(String n) {
        this.poblations.add(new Poblation(n));
    }
    
    public void addRoute(String namei, String namef, double v) {
        Route r = new Route(getPoblation(namef), getPoblation(namei), v);
        this.poblations.get(getPoblation(namei)).addRoute(this.routes.size());
        this.poblations.get(getPoblation(namef)).addRoute(this.routes.size());
        this.routes.add(r);
    }
    
    public void setType(String t) {
        this.type = t;
    }
    
    public int getPoblation(String name) {
        boolean res = false;
        Poblation p = null;
        int i = 0;
        for (; i < this.poblations.size() && !res; i++) {
            if (name.contentEquals((this.poblations.get(i)).getName())) {
                res = true;
                p = this.poblations.get(i);
            }
        }
        
        if (!res) {
            //Errores.informaError(new Exception("Nodo inexistente."));
            System.out.println("Poblacion inexistente");
        }
        return i-1;
    }
    
    public int getNPoblations() {
        return this.poblations.size();
    }
    
    public int getNRoutes(int index) {
        return this.poblations.get(index).getNRoutes();
    }
    
    public int getDestPoblation(int indexp, int indexr) {
        Route r =  this.routes.get(this.poblations.get(indexp).getRoute(indexr));
        return r.getPoblationFrom(indexp);
    }
    
    public double getRouteValue(int indexp, int indexr) {
        Route r =  this.routes.get(this.poblations.get(indexp).getRoute(indexr));
         return r.getValue();
    }
    
    public int getOrigin() {
        return this.origin;
    }
    
    public int getDest() {
        return this.dest;
    }
    
    public int getMid() {
        return this.mid;
    }
    
    public String getPobName(int index) {
        return this.poblations.get(index).getName();
    }

    @Override
    public void notify(Event e) {

    }
    
}
