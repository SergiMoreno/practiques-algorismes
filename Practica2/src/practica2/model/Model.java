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

class BoardCell {
    public int movement;
    public int piece;
    
    public BoardCell (int movement, int piece) {
        this.movement = movement;
        this.piece = piece;
    }    
}

public class Model implements EventListener {
    
    private Main main;
    
    // Data structure that conatains the chess board
    private BoardCell[][] board;
    private ArrayList<Piece> selectedPieces;
    private int occupiedCells = 0;
    private int movementCounter = 0;
    
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
        this.board = new BoardCell[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = new BoardCell(-1, -1); 
            }
        }
    }
    
    private Piece addPiecePlayer (String name, int x, int y) {
        try {
            // Instantiating the object from the class using the class name (string)
            Class loader = Class.forName("practica2.pieces."+name);
            Constructor ctor = loader.getDeclaredConstructor(new Class[0]);
            Piece newpiece = (Piece) ctor.newInstance(new Object[0]);
            newpiece.setPos(x, y);
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
    
    public int getPieceTurn () {
        if (this.pieces != null) return this.movementCounter % this.pieces.length;
        return this.movementCounter % this.selectedPieces.size();
    }
    
    public void setMovement (int movement) {
        this.movementCounter = movement;
    }
    
    public int getMovement () {
        return this.movementCounter;
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
    
    public BoardCell[][] getBoard () {
        return this.board;
    }
    
    public int getOccupiedCells () {
        return this.occupiedCells;
    }

    public void setCell (int x, int y, int value) {
        this.board[x][y] = new BoardCell(value, getPieceTurn());
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
                movePiece(event.posx, event.posy, this.movementCounter++);
                break;
            case ADD_SELECTED_PIECE:
                if (isOutOfBounds(event.posx, event.posy)) {
                    System.out.println("ERROR(Model): The specified coordinades are incorrect.");
                    break;
                }
                Piece piece = addPiecePlayer(event.name, event.posx, event.posy);
                if (piece != null) 
                        setCell(event.posx, event.posy, this.movementCounter++);
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
        return !(isOutOfBounds(x, y) || (this.board[x][y].movement > -1));        
    }
    
    public int getPiecePosX (int pieceIndex) {
        if (this.pieces != null) return this.pieces[pieceIndex].getPosX();
        return this.selectedPieces.get(pieceIndex).getPosX();
    }
    
    public int getPiecePosY (int pieceIndex) {
        if (this.pieces != null) return this.pieces[pieceIndex].getPosY();
        return this.selectedPieces.get(pieceIndex).getPosY();
    }
    
    public String getPieceImage (int pieceIndex) {
        if (this.pieces == null ) return this.selectedPieces.get(pieceIndex).getImage();
        return this.pieces[pieceIndex].getImage();
    }
    
    public int getNumPieceMovements (int pieceIndex) {
        return this.pieces[pieceIndex].getNumMovs();
    }
    
    public int getPieceMovX (int pieceIndex, int movementIndex) {
        return this.pieces[pieceIndex].getMovX(movementIndex);
    }
    
    public int getPieceMovY (int pieceIndex, int movementIndex) {
        return this.pieces[pieceIndex].getMovY(movementIndex);
    }
    
    public void prune (int movementToPrune) {
        BoardCell val;
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                val = this.board[i][j];
                if (val.movement >= movementToPrune) {
                    this.board[i][j] = new BoardCell(-1, -1);
                }
            }
        }
    }
    
    public void movePiece (int x, int y, int movement) {
        // Reflecting changes onto the board
        setCell(x, y, movement);
        // Adding node to the piece route
        this.pieces[this.getPieceTurn()].setPos(x, y);
    }
    
    public int getCellPiece (int x, int y) {
        return this.board[x][y].piece;
    }
}
