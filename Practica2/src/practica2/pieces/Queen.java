package practica2.pieces;

/**
 *
 * @author mascport
 */
public class Queen extends Piece {

    public Queen() {
        affectsdimension = true; //se mueve en dimensión tablero
        image = "queen.png";
        movx = new int[0];
        movy = new int[0];
        name = this.getClass().getName();
    }

    public Queen(int d) {
        affectsdimension = true; //se mueve en dimensión tablero
        name = this.getClass().getName();
        image = "/imagenes/reina.png";
        movx = new int[(d-1)*4*2];
        movy = new int[(d-1)*4*2];
        int pos = 0;
        for (int i = -(d-1); i < d; i++) {
            if (i != 0) {
                movx[pos] = 0; // vertical
                movy[pos++] = i; //vertical
                movx[pos] = i; // horizontal
                movy[pos++] = 0; //horizontal
                movx[pos] = i; //   oblicuo 1
                movy[pos++] = i; //    oblicuo1
                movx[pos] = -i; //   oblicuo 2
                movy[pos++] = i; //    oblicuo2               
            }
        }
    }
}
