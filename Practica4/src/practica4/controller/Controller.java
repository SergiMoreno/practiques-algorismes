package practica4.controller;

import practica4.Event;
import practica4.EventListener;
import practica4.Main;

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
