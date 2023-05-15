package practica5.model;

import practica5.Event;

/**
 *
 * @author usuario
 */
public class ModelEvent extends Event {
    private int lanCompare, lanCompareWith; // if lanCompareWith == -1, means with all
    
    public ModelEvent(int lan1, int lan2) {
        super(EventType.Model);
    }
    
    enum ModelEventType {
        START
    }
}
