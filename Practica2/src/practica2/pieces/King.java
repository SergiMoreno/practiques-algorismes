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
public class King extends Piece {

    public King() {
        name = this.getClass().getName();
        image = "king.png";
        movx = new int[8];
        movy = new int[8];
        int pos = 0;
        movx[pos] = 1; 
        movy[pos++] = 0;            
        movx[pos] = 1; 
        movy[pos++] = -1;            
        movx[pos] = 0; 
        movy[pos++] = -1;            
        movx[pos] = -1; 
        movy[pos++] = -1;        
        movx[pos] = -1; 
        movy[pos++] = 0;            
        movx[pos] = -1; 
        movy[pos++] = 1;            
        movx[pos] = 0; 
        movy[pos++] = 1;            
        movx[pos] = 1; 
        movy[pos++] = 1;            
    }
}
