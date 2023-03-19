
package practica2;

import mesurament.Mesurament;
import practica2.controller.Controller;
import practica2.model.Model;
import practica2.view.View;

/**
 *
 * @author usuario
 */
public class Main implements EventListener {
    // components MVC centralitzat
    private Model model;
    private View vista;
    private Controller control;
    
    static final public int BOARD_DEFAULT_SIZE = 8;
    static final public int DEFAULT_SPEED = 30;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Mesurament.mesura();
        (new Main()).init();
    }
    
    private void init() {
        model = new Model(this, Main.BOARD_DEFAULT_SIZE);
        control = new Controller(this);
        vista = new View(this);
    }

    @Override
    public void notify(Event e) {
        switch (e.getEventType()){
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
