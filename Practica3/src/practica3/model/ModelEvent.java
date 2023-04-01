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
    
    enum ModelEventType {
        CHANGE_N_POINTS
    }
}
