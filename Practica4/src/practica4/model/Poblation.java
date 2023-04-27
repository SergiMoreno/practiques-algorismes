package practica4.model;

import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public class Poblation {
    private String name;
    private ArrayList<Route> routes;
    
    public Poblation(String n) {
        this.name = n;
        this.routes = new ArrayList<Route>();
    }
    
    protected void addRoute(Route r) {
        this.routes.add(r);
    }
    
    protected String getName() {
        return this.name;
    }

    protected int getNRoutes() {
        return this.routes.size();
    }

    protected Route getRoute(int i) {
        return this.routes.get(i);
    }
}
