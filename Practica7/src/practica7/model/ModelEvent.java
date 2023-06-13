package practica7.model;

import practica7.Event;

/**
 *
 * @author usuario
 */
public class ModelEvent extends Event {
    public ModelEventType type;
    public String number;
    public long time;
    
    // Event to update the puzzle size
    public ModelEvent(String n) {
        super(EventType.Model);
        
        this.number = n;
        this.type = ModelEventType.UPDATE_NUMBER;
    }
    
    public ModelEvent(long t) {
        super(EventType.Model);
        
        this.time = t;
        this.type = ModelEventType.UPDATE_NUMBER;
    }
    
    enum ModelEventType {
        UPDATE_NUMBER,
        ADD_TIME
    }
}
