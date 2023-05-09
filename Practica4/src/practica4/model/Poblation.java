package practica4.model;

import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public class Poblation {
    private String name;
    private int coordx, coordy;
    private ArrayList<Integer> exitRoutes;
    private ArrayList<Integer> entryRoutes;
    
    public Poblation(String n, int x, int y) {
        this.name = n;
        this.coordx = x;
        this.coordy = y;
        this.exitRoutes = new ArrayList<Integer>();
        this.entryRoutes = new ArrayList<Integer>();
    }
    
    protected void addExitRoute(Integer r) {
        this.exitRoutes.add(r);
    }
    
    protected void addEntryRoute(Integer r) {
        this.entryRoutes.add(r);
    }
    
    protected String getName() {
        return this.name;
    }

    protected int getNExitRoutes() {
        return this.exitRoutes.size();
    }

    protected int getExitRoute(int i) {
        return this.exitRoutes.get(i);
    }
    
    protected int getNEntryRoutes() {
        return this.entryRoutes.size();
    }

    protected int getEntryRoute(int i) {
        return this.entryRoutes.get(i);
    }
    
    protected int getCoordx() {
        return this.coordx;
    }
    
    protected int getCoordy() {
        return this.coordy;
    }
}
