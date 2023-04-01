package practica3;

import mesurament.Mesurament;
import practica3.controller.Controller;
import practica3.model.Model;
import practica3.view.View;

/**
 *
 * @author usuario
 */
public class Main implements EventListener {
    // components MVC centralitzat
    private Model model;
    private View vista;
    private Controller control;
    
    // Default number of points
    static final int DEFAULT_N_POINTS = 100;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Mesurament.mesura();
        (new Main()).init();
    }
    
    private void init() {
        model = new Model(this, DEFAULT_N_POINTS);
        control = new Controller(this);
        vista = new View(this, DEFAULT_N_POINTS);
    }
    
    @Override
    public void notify(Event e) {
        switch (e.getEventType()){
            case Model -> {
                model.notify(e);
            }
            case View -> {
                vista.notify(e);
            }
            case Controller -> {
                control.notify(e);
            }
        }
    }
    
    public Model getModel() {
        return this.model;
    }
}
