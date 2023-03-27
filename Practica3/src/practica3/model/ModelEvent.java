package practica3.model;

import practica3.Event;

/**
 *
 * @author usuario
 */
public class ModelEvent extends Event {
    public ModelEventType type;
    
    public ModelEvent(boolean start) {
        super(EventType.Model);

    }
    
    enum ModelEventType {

    }
}
