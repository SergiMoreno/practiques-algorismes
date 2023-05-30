package practica6.model;

import practica6.Event;

/**
 *
 * @author usuario
 */
public class ModelEvent extends Event {
    public ModelEventType type;
    
    public ModelEvent() {
        super(EventType.Model);
    }
    
    enum ModelEventType {

    }
}
