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
    public int pieceIndex;
    
    public ModelEventType type;
    public int dimension;
    public ArrayList<Piece> pieces;
    
    // Inicialize pieces on the board
    public ModelEvent(ArrayList<Piece> pieces) {
        super(EventType.Model);
        
        this.pieces = pieces;
        this.type = ModelEventType.START;
    }
    
    // Move piece on the board
    public ModelEvent(int pieceIndex, int x, int y, int movement) {
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
    
    enum ModelEventType {
        SET_DIMENSION,
        START,
        MOVE_PIECE
    }
}
