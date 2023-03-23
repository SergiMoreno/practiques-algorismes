package practica2.pieces;

/**
 *
 * @author usuario
 */
public class Bishop extends Piece {

    public Bishop() {
        affectsdimension = true; //se mueve en dimensión tablero
        image = "bishop.png";
        movx = new int[0];
        movy = new int[0];
        name = this.getClass().getName();
    }
    
    public Bishop(int d) {
        affectsdimension = true; //se mueve en dimensión tablero
        name = this.getClass().getName();
        image = "bishop.png";
        movx = new int[(d-1)*4];
        movy = new int[(d-1)*4];
        int pos = 0;
        for (int i = -(d - 1); i < d; i++) {
            for (int j = -(d - 1); j < d; j++) {
                if (i != 0 && j != 0 && Math.abs(i) == Math.abs(j)) {
                    movx[pos] = i;
                    movy[pos++] = j;
                }    
            }
        }
    }
}
