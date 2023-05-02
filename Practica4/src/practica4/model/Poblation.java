package practica4.model;

import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public class Poblation {
    private String name;
    private ArrayList<Integer> routes;
    
    public Poblation(String n) {
        this.name = n;
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
}
