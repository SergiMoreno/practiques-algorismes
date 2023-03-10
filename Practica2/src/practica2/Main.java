package practica2;

import mesurament.Mesurament;
import practica2.control.Control;
import practica2.model.Model;
import practica2.vista.Vista;

/**
 *
 * @author usuario
 */
public class Main implements EventListener {
    // components MVC centralitzat
    private Model model;
    private Vista vista;
    private Control control;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Mesurament.mesura();
        (new Main()).init();
    }
    
    private void init() {
        model = new Model(this);
        control = new Control(this);
        vista = new Vista(this);
    }
    
    public void reset() {
        model = new Model(this);
        control = new Control(this);
    }

    @Override
    public void notify(Event e) {
        switch (e.getEventOrigin()){
            case Model -> {
                model.notify(e);
            }
            case Vista -> {
                vista.notify(e);
            }
            case Control -> {
                control.notify(e);
            }
        }
    }
    
    public Model getModel() {
        return this.model;
    }
}
