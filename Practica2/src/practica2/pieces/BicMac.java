package practica2.pieces;

/**
 *
 * @author usuario
 */
public class BicMac extends Piece {

    public BicMac() {
        name = this.getClass().getName();
        image = "bicmac.png";
        movx = new int[8];
        movy = new int[8];
        int pos = 0;
        
        movx[pos] = 2; 
        movy[pos++] = 0;            
        movx[pos] = 1; 
        movy[pos++] = -1;            
        movx[pos] = 0; 
        movy[pos++] = -2;            
        movx[pos] = -1; 
        movy[pos++] = -1;        
        movx[pos] = -2; 
        movy[pos++] = 0;            
        movx[pos] = -1; 
        movy[pos++] = 1;            
        movx[pos] = 0; 
        movy[pos++] = 2;            
        movx[pos] = 1; 
        movy[pos++] = 1;
    }
}
