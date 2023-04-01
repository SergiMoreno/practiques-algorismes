package practica3.model;

import practica3.Event;

/**
 *
 * @author usuario
 */
public class ModelEvent extends Event {
    public ModelEventType type;
    public int n;
    
    public ModelEvent(int newN) {
        super(EventType.Model);
        
        this.n = newN;
        this.type = ModelEventType.CHANGE_N;
    }
    
    enum ModelEventType {
        CHANGE_N
    }
}
