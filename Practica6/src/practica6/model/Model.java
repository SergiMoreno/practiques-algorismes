package practica6.model;

import practica6.Event;
import practica6.EventListener;
import practica6.Main;

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
        
        switch (event.type) {

        }
    }
}
