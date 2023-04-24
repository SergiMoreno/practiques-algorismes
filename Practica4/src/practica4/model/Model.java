package practica4.model;

import practica4.Event;
import practica4.EventListener;
import practica4.Main;

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

    }
    
}
