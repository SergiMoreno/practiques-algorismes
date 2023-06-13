package practica7.model;

import practica7.Event;

/**
 *
 * @author usuario
 */
public class ModelEvent extends Event {
    public ModelEventType type;
    public String number;
    
    // Event to update the puzzle size
    public ModelEvent(String n) {
        super(EventType.Model);
        
        this.number = n;
        this.type = ModelEventType.UPDATE_NUMBER;
    }
    
    enum ModelEventType {
        UPDATE_NUMBER,
        ADD_TIME
    }
}
