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
        /*movx = new int[48];  // 49 movimientos menos el (0,0)
        movy = new int[48];
        int pos = 0;
        for (int i = -3; i < 4; i++) {
            for (int j = -3; j < 4; j++) {
                if ((i != 0) || (j != 0)) {
                    movx[pos] = i;
                    movy[pos++] = j;
                }
            }
        }*/
    }
    
    public Bishop(int d) {
        affectsdimension = true; //se mueve en dimensión tablero
        name = this.getClass().getName();
        image = "bishop.png";
        movx = new int[(d-1)*4];
        movy = new int[(d-1)*4];
        int pos = 0;
        for (int i = -(d - 1); i < d; i++) {
            for (int j = -(d - 1); i < d; j++) {
                if ((i != 0) || (j != 0)) {
                    movx[pos] = i;
                    movy[pos++] = j;
                }
            }
        }
    }
}
