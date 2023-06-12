package practica7.model;

import practica7.Event;
import practica7.EventListener;
import practica7.Main;

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
