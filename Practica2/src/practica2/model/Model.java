package practica2.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import practica2.Event;
import practica2.EventListener;
import practica2.Main;
import practica2.pieces.*;

/**
 *
 * @author usuario
 */

public class Model implements EventListener {
    
    private Main main;
    
    // Data structure that conatains the chess board
    private boolean[][] board;
    private ArrayList<Piece> selectedPieces;
    
    // Contains the pieces playing
    private Piece [] pieces;
    
    private int boardSize;
    
    public Model(Main main, int boardSize) {
        this.main = main;
        this.selectedPieces = new ArrayList<Piece>();
        createBoard(boardSize);
    }
    
    private void createBoard (int size) {
        this.boardSize = size;
        this.board = new boolean[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = false; 
            }
        }
    }
    
    private Piece addPiecePlayer (String name, int x, int y) {
        try {
            // Instantiating the object from the class using the class name (string)
            Class loader = Class.forName("practica2.pieces."+name);
            Constructor ctor = loader.getDeclaredConstructor(new Class[0]);
            Piece newpiece = (Piece) ctor.newInstance(new Object[0]);
            newpiece.expandRoute(x, y, 0);
            selectedPieces.add(newpiece);
            return newpiece;
        } catch (ClassNotFoundException ex) {
            System.out.println("ERROR(Controller): The specified piece doesn't exist.");
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } 
    
    private void removePiecePlayers () {
        for (int i = 0; i < this.pieces.length; i++) {
            this.pieces[i] = null;
        }
    } 
    
    private void resetModel (int boardSize) {
        removePiecePlayers();
        createBoard(boardSize);
    }
    
    public boolean[][] getBoard () {
        return this.board;
    }

    @Override
    public void notify(Event e) {
        ModelEvent event = (ModelEvent) e;
        
        switch (event.type) {
            case START:
                pieces = new Piece[selectedPieces.size()];
                int i = 0;
                for (Piece p : selectedPieces) {
                    pieces[i] = p;
                    i++;
                }
                break;
            case SET_DIMENSION:
                createBoard(event.dimension);
                break;
            case MOVE_PIECE:
                if (isOutOfBounds(event.posx, event.posy)) {
                    System.out.println("ERROR(Model): The specified coordinades are incorrect.");
                    break;
                }
                movePiece(event.pieceIndex, event.posx, event.posy, event.movement);
                break;
            case ADD_SELECTED_PIECE:
                if (isOutOfBounds(event.posx, event.posy)) {
                    System.out.println("ERROR(Model): The specified coordinades are incorrect.");
                    break;
                }
                Piece piece = addPiecePlayer(event.name, event.posx, event.posy);
                if (piece != null) 
                        this.board[event.posx][event.posy] = true;
                break;
        }
    }
    
    public int getSize () {
        return this.boardSize;
    }
    
    private boolean isOutOfBounds (int x, int y) {
        return x < 0 || x >= this.boardSize || y < 0 || y >= this.boardSize;
    }
    
    public int getNumPieces () {
        if (this.pieces != null) return this.pieces.length;
        return this.selectedPieces.size();
    }
    
    public boolean isValidMovement (int x, int y) {
        return !(isOutOfBounds(x, y) || this.board[x][y]);        
    }
    
    public int getPieceRouteNodeX (int pieceIndex, int nodeIndex) {
        if (this.pieces != null) return this.pieces[pieceIndex].getRouteNodeX(nodeIndex);
        return this.selectedPieces.get(pieceIndex).getRouteNodeX(nodeIndex);
    }
    
    public int getPieceRouteNodeY (int pieceIndex, int nodeIndex) {
        if (this.pieces != null) return this.pieces[pieceIndex].getRouteNodeY(nodeIndex);
        return this.selectedPieces.get(pieceIndex).getRouteNodeY(nodeIndex);
    }
    
    public int getPieceRouteSize (int pieceIndex) {
        if (this.pieces == null ) return this.selectedPieces.get(pieceIndex).getRouteSize();
        return this.pieces[pieceIndex].getRouteSize();
    }
    
    public String getPieceImage (int pieceIndex) {
        if (this.pieces == null ) return this.selectedPieces.get(pieceIndex).getImage();
        return this.pieces[pieceIndex].getImage();
    }
    
    public void movePiece (int pieceIndex, int x, int y, int movement) {
        // Reflecting changes onto the board
        this.board[x][y] = true;
        // Adding node to the piece route
        this.pieces[pieceIndex].expandRoute(x, y, movement);
    }
}
