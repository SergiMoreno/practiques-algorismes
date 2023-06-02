package practica6.model;

import practica6.Event;

/**
 *
 * @author usuario
 */
public class ModelEvent extends Event {
    public ModelEventType type;
    public int puzzleSize;
    
    // Event to update the puzzle size
    public ModelEvent(int pSize) {
        super(EventType.Model);
        
        this.puzzleSize = pSize;
        this.type = ModelEventType.UPDATE_PUZZLE_SIZE;
    }
    
    // Event to reset the puzzle
    public ModelEvent() {
        super(EventType.Model);
        
        this.type = ModelEventType.RESET;
    }
    
    enum ModelEventType {
        UPDATE_PUZZLE_SIZE,
        RESET
    }
}
