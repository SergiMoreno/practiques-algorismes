package practica2.model;

import practica2.Event;

/**
 *
 * @author usuario
 */
public class ModelEvent extends Event {
    public int posx;
    public int posy;
    public int movement;
    public String name;
    
    public int pieceIndex;
    
    public ModelEventType type;
    public int dimension;
    
    // Set the piece to be played
    public ModelEvent(boolean start) {
        super(EventType.Model);
        
        if (start) {
            this.type = ModelEventType.START;
        } else {
            this.type = ModelEventType.RESET;
        }
    }
    
    // Move piece on the board
    public ModelEvent(int x, int y, int movement, int pieceIndex) {
        super(EventType.Model);
        
        this.posx = x;
        this.posy = y;
        this.movement = movement;
        this.pieceIndex = pieceIndex;
        this.type = ModelEventType.MOVE_PIECE;
    }
    
    // Assign board dimension
    public ModelEvent(int dimension) {
        super(EventType.Model);
        this.dimension = dimension;
        this.type = ModelEventType.SET_DIMENSION;
    }
    
    // Add selected piece
    public ModelEvent(String name, int x, int y) {
        super(EventType.Model);
        this.name = name;
        this.posx = x;
        this.posy = y;
        this.type = ModelEventType.ADD_SELECTED_PIECE;  
    }
    
    // Prune descendants from board
    public ModelEvent(int movement, int pieceIndex) {
        super(EventType.Model);
 
        this.movement = movement;
        this.pieceIndex = pieceIndex;
        this.type = ModelEventType.PRUNE;
    }
    
    enum ModelEventType {
        SET_DIMENSION,
        ADD_SELECTED_PIECE,
        START,
        MOVE_PIECE,
        PRUNE,
        RESET
    }
}
