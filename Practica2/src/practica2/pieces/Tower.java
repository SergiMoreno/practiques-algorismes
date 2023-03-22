package practica2.pieces;

/**
 *
 * @author mascport
 */
public class Tower extends Piece {

    public Tower() {
        affectsdimension = true; //se mueve en dimensión tablero
        name = this.getClass().getName();
        image = "tower.png";
        movx = new int[0];
        movy = new int[0];
    }

    public Tower(int d) {
        affectsdimension = true; //se mueve en dimensión tablero
        name = this.getClass().getName();
        image = "tower.png";
        movx = new int[(d - 1) * 4];
        movy = new int[(d - 1) * 4];
        int pos = 0;
        for (int i = -(d - 1); i < d; i++) {
            if (i != 0) {
                movx[pos] = 0; // vertical
                movy[pos++] = i; //vertical
                movx[pos] = i; // horizontal
                movy[pos++] = 0; //horizontal            
            }
        }
    }
}
