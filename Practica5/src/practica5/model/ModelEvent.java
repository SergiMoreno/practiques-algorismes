package practica5.model;

import practica5.Event;

/**
 *
 * @author usuario
 */
public class ModelEvent extends Event {
    public int lanCompare, lanCompareWith; // if lanCompareWith == -1, means with all
    public ModelEventType type;
    public String [] textWords;
    
    public ModelEvent(int lan1, int lan2) {
        super(EventType.Model);
        
        this.lanCompare = lan1;
        this.lanCompareWith = lan2;
        this.type = ModelEventType.START_DICTIONARIES;
    }
    
    public ModelEvent(String [] w) {
        super(EventType.Model);
        
        this.textWords = w.clone();
        this.type = ModelEventType.START_TEXT;
    }
    
    enum ModelEventType {
        START_DICTIONARIES,
        START_TEXT
    }
}
