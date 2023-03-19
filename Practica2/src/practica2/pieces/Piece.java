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

class RouteNode {
    final public int x;
    final public int y;
    final public int movement;
    
    public RouteNode (int x, int y, int movement) {
        this.x = x;
        this.y = y;
        this.movement = movement;
    }
}

public abstract class Piece {
    protected int movx[];
    protected int movy[];
    protected String name;
    protected String image;
    protected boolean affectsdimension = false;
    protected final ArrayList<RouteNode> route = new ArrayList<RouteNode>();

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
    
    public void pruneRoute (int movementToPrune) {
        // Prunning (rollback) a piece route once a branch has been cutted 
        // during the backtracking process
        for (int i = this.route.size()-1; i >= movementToPrune; i--) {
            this.route.remove(i);
        }
    }   
    
    public void expandRoute (int x, int y, int movement) {
        this.route.add(new RouteNode(x, y, movement));
    }
    
    public int getRouteSize () {
        return this.route.size();
    } 
    
    public int getRouteNodeX (int nodeIndex) {
        return this.route.get(nodeIndex).x;
    }
    
    public int getRouteNodeY (int nodeIndex) {
        return this.route.get(nodeIndex).y;
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
