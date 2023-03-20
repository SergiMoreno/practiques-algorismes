/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2.pieces;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import practica2.model.Model;

/**
 *
 * @author mascport
 */

public abstract class Piece {
    protected int movx[];
    protected int movy[];
    protected String name;
    protected String image;
    protected boolean affectsdimension = false;
    protected int x;
    protected int y;

    public boolean afectaDimension() {
        return affectsdimension;
    }
    
    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
    
    public int getNumMovs() {
        return movx.length;
    }
    
    public int getMovX(int i) {
        return movx[i];
    }
    
    public int getMovY(int i) {
        return movy[i];
    }
    
    public static String [] getPiecesTypes() {
        String [] result = new String[PieceTypes.values().length];
        int i = 0;
        for (PieceTypes p : PieceTypes.values()) {
            result[i] = p.name();
            i++;
        }
        return result;
    }
    
    public void setPos (int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getPosX () {
        return this.x;
    }
    
    public int getPosY () {
        return this.y;
    }
    
    public enum PieceTypes {
        BicMac,
        Bishop,
        Horse,
        King,
        Queen,
        Tower
    }
}
