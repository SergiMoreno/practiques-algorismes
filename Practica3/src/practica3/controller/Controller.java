package practica3.controller;

import practica3.Event;
import practica3.EventListener;
import practica3.Main;

/**
 *
 * @author usuario
 */

public class Controller extends Thread implements EventListener {
    private Main main;
    
    public Controller(Main main) {
        this.main = main;
    }
    
    @Override
    public void run () {

    }
    
    @Override
    public void notify(Event e) {
        ControllerEvent event = (ControllerEvent) e;

    }
}
