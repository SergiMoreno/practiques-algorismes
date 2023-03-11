/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica2.model;

import practica2.pieces.*;

/**
 *
 * @author josep
 */
public class BoardCell {
    public boolean visited;
    public String pieceImage;
    public int movement;
    
    public BoardCell () {
        resetCell();
    }
    
    public void resetCell () {
        this.visited = false;
        this.pieceImage = null;
        this.movement = -1;
    }
}
