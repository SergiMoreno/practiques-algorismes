package practica7.model;

import practica7.Event;

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
    
    enum ModelEventType {
        UPDATE_PUZZLE_SIZE
    }
}
