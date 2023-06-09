package practica6;

import mesurament.Mesurament;
import practica6.controller.Controller;
import practica6.model.Model;
import practica6.view.View;

/**
 *
 * @author usuario
 */
/*
* Video :
*/
public class Main implements EventListener {
    // components MVC centralitzat
    private Model model;
    private View view;
    private Controller controller;
    
    public final static int PUZZLE_SIZE = 4;
    public final static int DEFAULT_SPEED = 5;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Mesurament.mesura();
        (new Main()).init();
    }
    
    private void init() {
        this.model = new Model(this);
        this.controller = new Controller(this);
        this.view = new View(this);
    }

    @Override
    public void notify(Event e) {
        switch (e.getEventType()){
            case Model -> {
                model.notify(e);
            }
            case View -> {
                view.notify(e);
            }
            case Controller -> {
                controller.notify(e);
            }
        }
    }
    
    public Model getModel() {
        return this.model;
    }
}
