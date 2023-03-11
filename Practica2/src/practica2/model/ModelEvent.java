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
    public String pieceImage;
    
    public ModelEvent(int x, int y, String pieceImage, int movement) {
        super(EventOrigin.Model);
        
        this.posx = x;
        this.posy = y;
        this.movement = movement;
        this.pieceImage = pieceImage;
    }
}
