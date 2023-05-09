package practica4.model;

import practica4.Event;

/**
 *
 * @author usuario
 */
public class ModelEvent extends Event {
    public ModelEventType type;
    public int pobIndex;
    public boolean [] criterias;

    // Event to update origin if isOrigin is true, else update destination
    public ModelEvent(boolean isOrigin, int index) {
        super(EventType.Model);
        
        this.pobIndex = index;
        if (isOrigin) {
            this.type = ModelEventType.UPDATE_ORIGIN;
        } else {
            this.type = ModelEventType.UPDATE_DESTINATION;
        }
    }
    
    // Event to update middle poblation
    public ModelEvent(int index) {
        super(EventType.Model);
        
        this.pobIndex = index;
        this.type = ModelEventType.UPDATE_MIDDLE;
    }
    
    // Event to reset the poblations selected
    public ModelEvent() {
        super(EventType.Model);
        
        this.type = ModelEventType.RESET;
    }
    
    // Event to start the calculation of the routes weight
    public ModelEvent(boolean [] c) {
        super(EventType.Model);
        
        this.criterias = c.clone();
        this.type = ModelEventType.START;
    }
    
    enum ModelEventType {
        UPDATE_ORIGIN,
        UPDATE_MIDDLE,
        UPDATE_DESTINATION,
        START,
        RESET
    }
}
