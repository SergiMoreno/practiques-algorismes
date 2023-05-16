package practica5.model;

import practica5.Event;

/**
 *
 * @author usuario
 */
public class ModelEvent extends Event {
    public int lanCompare, lanCompareWith; // if lanCompareWith == -1, means with all
    public ModelEventType type;
    
    public ModelEvent(int lan1, int lan2) {
        super(EventType.Model);
        
        this.lanCompare = lan1;
        this.lanCompareWith = lan2;
        this.type = ModelEventType.START;
    }
    
    enum ModelEventType {
        START
    }
}
