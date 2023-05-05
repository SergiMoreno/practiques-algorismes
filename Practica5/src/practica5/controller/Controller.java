package practica5.controller;

import practica5.Event;
import practica5.EventListener;
import practica5.Main;

/**
 *
 * @author usuario
 */
public class Controller implements EventListener {
    private Main main;
    
    public Controller(Main main) {
        this.main = main;
    }

    @Override
    public void notify(Event e) {
    }    
}
