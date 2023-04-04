package practica3.model;

import practica3.Event;

/**
 *
 * @author usuario
 */
public class ModelEvent extends Event {
    public ModelEventType type;
    public int nPoints;
    
    // Event to update the cloud number of points
    public ModelEvent(int number) {
        super(EventType.Model);
        
        this.nPoints = number;
        this.type = ModelEventType.CHANGE_N_POINTS;
    }
    
    // Event to reset the cloud of points
    public ModelEvent() {
        super(EventType.Model);
        
        this.type = ModelEventType.RESET;
    }
    
    enum ModelEventType {
        CHANGE_N_POINTS,
        RESET
    }
}
