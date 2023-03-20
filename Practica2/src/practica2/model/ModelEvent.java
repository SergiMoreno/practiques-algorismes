package practica2.model;

import java.util.ArrayList;
import practica2.Event;
import practica2.pieces.Piece;

/**
 *
 * @author usuario
 */
public class ModelEvent extends Event {
    public int posx;
    public int posy;
    public int movement;
    public String name;
    
    public ModelEventType type;
    public int dimension;
    
    // Inicialize pieces on the board
    public ModelEvent() {
        super(EventType.Model);

        this.type = ModelEventType.START;
    }
    
    // Move piece on the board
    public ModelEvent(int x, int y, int movement) {
        super(EventType.Model);
        
        this.posx = x;
        this.posy = y;
        this.movement = movement;
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
    
    enum ModelEventType {
        SET_DIMENSION,
        START,
        MOVE_PIECE,
        ADD_SELECTED_PIECE
    }
}
