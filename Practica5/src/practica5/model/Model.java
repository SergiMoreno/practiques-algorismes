package practica5.model;

import practica5.Event;
import practica5.EventListener;
import practica5.Main;

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
