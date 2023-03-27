package practica3.model;

import practica3.Event;
import practica3.EventListener;
import practica3.Main;

/**
 *
 * @author usuario
 */

public class Model implements EventListener {
    private Main main;    
    
    public Model(Main main) {
        this.main = main;

    }

    @Override
    public void notify(Event e) {
        ModelEvent event = (ModelEvent) e;
        
    }
}
