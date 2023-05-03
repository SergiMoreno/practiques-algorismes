package practica4.model;

import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public class Poblation {
    private String name;
    private int coordx, coordy;
    private ArrayList<Integer> routes;
    
    public Poblation(String n, int x, int y) {
        this.name = n;
        this.coordx = x;
        this.coordy = y;
        this.routes = new ArrayList<Integer>();
    }
    
    protected void addRoute(Integer r) {
        this.routes.add(r);
    }
    
    protected String getName() {
        return this.name;
    }

    protected int getNRoutes() {
        return this.routes.size();
    }

    protected int getRoute(int i) {
        return this.routes.get(i);
    }
    
    protected int getCoordx() {
        return this.coordx;
    }
    
    protected int getCoordy() {
        return this.coordy;
    }
}
