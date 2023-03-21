package practica2.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
    //private int occupiedCells = 0;
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
    
    private void resetModel (int boardSize) {
        //removePiecePlayers();
        createBoard(boardSize);
    }
    
    public int getBoardSize() {
        return this.boardSize;
    }
    
    public int getOccupiedCells () {
        //return this.occupiedCells;
        return this.movementCounter;
    }
    
    
    /*    *********************              */
    public int getNumPieces() {
        if (this.pieces != null) return this.pieces.length;
        return this.selectedPieces.size();
    }
    
    public int getCellPiece(int x, int y) {
        return this.board[x][y].piece;
    }
    /*    *********************              */
    
    
    
    public int getPieceTurn() {
        if (this.pieces != null) return this.movementCounter % this.pieces.length;
        return this.movementCounter % this.selectedPieces.size();
    }
    
    public int getMovement() {
        if (this.pieces != null) return this.movementCounter / this.pieces.length;
        return this.movementCounter / this.selectedPieces.size();
    }
    
    public int getNumPieceMovements(int pieceIndex) {
        return this.pieces[pieceIndex].getNumMovs();
    }
    
    public int getPieceMoveX(int pieceIndex, int movementIndex) {
        return this.pieces[pieceIndex].getMovX(movementIndex);
    }
    
    public int getPieceMoveY(int pieceIndex, int movementIndex) {
        return this.pieces[pieceIndex].getMovY(movementIndex);
    }
    
    public int getPiecePosX(int pieceIndex) {
        return this.pieces[pieceIndex].getPosX();
    }
    
    public int getPiecePosY(int pieceIndex) {
        return this.pieces[pieceIndex].getPosY();
    }
    
    public int getCellMovement(int x, int y) {
        return this.board[x][y].movement;
    }
    
    public String getCellImage (int x, int y) {
        int pieceIndex = this.board[x][y].piece;
        if (pieceIndex == -1) return null;
        if (this.pieces == null) return this.selectedPieces.get(pieceIndex).getImage();
        return this.pieces[pieceIndex].getImage();
    }

    /*public void setCell (int x, int y, int value) {
        this.board[x][y] = new BoardCell(value, getPieceTurn());
    }*/
    
    public boolean isOutOfBounds(int x, int y) {
        return x < 0 || x >= this.boardSize || y < 0 || y >= this.boardSize; 
    }
    
    public boolean isValidMovement(int x, int y) {
        return !isOutOfBounds(x, y) && this.board[x][y].piece == -1;
    }
    
    public void prune (int movementToPrune, int pieceIndex) {
        BoardCell val;
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                val = this.board[i][j];
                if (pieceIndex <= val.piece) {
                    if (val.movement >= movementToPrune) {
                        this.board[i][j] = new BoardCell(-1, -1);
                        this.movementCounter--;
                    } else if (val.movement == movementToPrune - 1) {
                        this.pieces[val.piece].setPos(i, j);
                    }
                } else if (val.piece != -1 && pieceIndex > val.piece) {
                    if (val.movement > movementToPrune) {
                        this.board[i][j] = new BoardCell(-1, -1);
                        this.movementCounter--;
                    } else if (val.movement == movementToPrune) {
                        this.pieces[val.piece].setPos(i, j);
                    }
                }
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
                board[event.posx][event.posy] = new BoardCell(event.movement, event.pieceIndex);
                this.movementCounter++;
                this.pieces[event.pieceIndex].setPos(event.posx, event.posy);
                break;
            case ADD_SELECTED_PIECE:
                if (isOutOfBounds(event.posx, event.posy)) {
                    System.out.println("ERROR(Model): The specified coordinades are incorrect.");
                    break;
                }
                Piece piece = addPiecePlayer(event.name, event.posx, event.posy);
                if (piece != null) {
                    selectedPieces.add(piece);
                    board[event.posx][event.posy] = new BoardCell(this.getMovement(), selectedPieces.size()-1);
                    this.movementCounter++;
                }
                        //setCell(event.posx, event.posy, this.movementCounter++);
                break;
        }
    }
}
