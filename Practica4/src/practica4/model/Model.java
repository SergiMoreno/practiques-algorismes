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
    private String type = "";
    private int [] pobSelected;
    private final double [] WEIGHTS = {0.6, 0.3, 0.1};
    private final int TO_SELECT = 3;
    
    public Model(Main main) {
        this.main = main;
        this.poblations = new ArrayList<>();
        this.routes = new ArrayList<>();
        this.type = "dirigido";
        
        initializePobSelected();
    }
    
    public void initializePobSelected() {
        this.pobSelected = new int[3];
        for (int i = 0; i < TO_SELECT; i++) {
            this.pobSelected[i] = -1;
        }
    }
    
    public void addPoblation(String n, int coordx, int coordy) {
        this.poblations.add(new Poblation(n, coordx, coordy));
    }
    
    public void addRoute(String namei, String namef, double v1, double v2, double v3) {
        Route r = new Route(getPoblation(namei), getPoblation(namef), v1, v2, v3);
        this.poblations.get(getPoblation(namei)).addExitRoute(this.routes.size());
        this.poblations.get(getPoblation(namef)).addEntryRoute(this.routes.size());
        this.routes.add(r);
    }
    
    public void setType(String t) {
        this.type = t;
    }
    
    private int getPoblation(String name) {
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
    
    public int getNExitRoutes(int index) {
        return this.poblations.get(index).getNExitRoutes();
    }
    
    public int getNEntryRoutes(int index) {
        return this.poblations.get(index).getNEntryRoutes();
    }
    
    public int getDestPoblation(int indexp, int indexr) {
        Route r =  this.routes.get(this.poblations.get(indexp).getExitRoute(indexr));
        return r.getDestination();
    }
    
    public double getExitRouteValue(int indexp, int indexr) {
        Route r =  this.routes.get(this.poblations.get(indexp).getExitRoute(indexr));
        return r.getValue();
    }
    
    public int getOriginPoblation(int indexp, int indexr) {
        Route r =  this.routes.get(this.poblations.get(indexp).getEntryRoute(indexr));
        return r.getOrigin();
    }
    
    public double getEntryRouteValue(int indexp, int indexr) {
        Route r =  this.routes.get(this.poblations.get(indexp).getEntryRoute(indexr));
        return r.getValue();
    }
    
    public int getOrigin() {
        return this.pobSelected[0];
    }
    
    public int getDest() {
        return this.pobSelected[2];
    }
    
    public int getMiddle() {
        return this.pobSelected[1];
    }
    
    public String getPobName(int index) {
        return this.poblations.get(index).getName();
    }
    
    public int getPoblationX(int index) {
        return this.poblations.get(index).getCoordx();
    }

    public int getPoblationY(int index) {
        return this.poblations.get(index).getCoordy();
    }
    
    public boolean isSelected(int index) {
        return this.pobSelected[0] == index ||
               this.pobSelected[1] == index ||
               this.pobSelected[2] == index;
    }
    
    public boolean selectionCompleted() {
        return this.pobSelected[0] != -1 &&
               this.pobSelected[1] != -1 &&
               this.pobSelected[2] != -1;
    }
    
    public void resetMap() {
        this.poblations = new ArrayList<>();
        this.routes = new ArrayList<>();
        this.type = "dirigido";
        
        initializePobSelected();
    }
     
    @Override
    public void notify(Event e) {
        ModelEvent event = (ModelEvent) e;
        
        switch (event.type) {
            case UPDATE_ORIGIN -> {
                this.pobSelected[0] = event.pobIndex;
            }
            case UPDATE_MIDDLE -> {
                this.pobSelected[1] = event.pobIndex;
            }
            case UPDATE_DESTINATION -> {
                this.pobSelected[2] = event.pobIndex;
            }
            case RESET -> {
                initializePobSelected();
            }
            case START -> {
                for (int i = 0; i < this.routes.size(); i++) {
                    Route r = this.routes.get(i);
                    r.setWeight(event.criterias, this.WEIGHTS);
                }
            }
        }
    }
    
}
