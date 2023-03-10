/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2.pieces;

/**
 *
 * @author mascport
 */
public class Torre extends Piece {

    public Torre() {
        affectsdimension = true; //se mueve en dimensión tablero
        name = this.getClass().getName();
        image = "/imagenes/torre.png";
        movx = new int[0];
        movy = new int[0];
    }

    public Torre(int d) {
        affectsdimension = true; //se mueve en dimensión tablero
        name = this.getClass().getName();
        image = "/imagenes/torre.png";
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
