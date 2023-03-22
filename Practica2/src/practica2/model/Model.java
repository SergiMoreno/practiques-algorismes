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
    private int boardSize;
    
    // Contains de list of pieces placed by the user on the board
    private ArrayList<Piece> selectedPieces;
    // Contains the pieces playing
    private Piece [] pieces;
    
    // Counter of total movements on board
    private int movementCounter = 0;
    
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
    
    // NOT IMPLEMENTED
    private void resetModel (int boardSize) {
        //removePiecePlayers();
        createBoard(boardSize);
    }
    
    public int getBoardSize() {
        return this.boardSize;
    }
    
    public int getOccupiedCells () {
        return this.movementCounter;
    }
    
    public int getNumPieces() {
        if (this.pieces != null) return this.pieces.length;
        return this.selectedPieces.size();
    }
    
    public int getCellPiece(int x, int y) {
        return this.board[x][y].piece;
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
    
    // Return turn to move as piece index
    public int getPieceTurn() {
        if (this.pieces != null) return this.movementCounter % this.pieces.length;
        return this.movementCounter % this.selectedPieces.size();
    }
    
    // Return movement to be taken by the piece
    public int getMovement() {
        if (this.pieces != null) return this.movementCounter / this.pieces.length;
        return this.movementCounter / this.selectedPieces.size();
    }
    
    // Return number of moves that can performed the indexed piece
    public int getNumPieceMoves(int pieceIndex) {
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
    
    public boolean isOutOfBounds(int x, int y) {
        return x < 0 || x >= this.boardSize || y < 0 || y >= this.boardSize; 
    }
    
    // Cell position is inside board and empty
    public boolean isValidMovement(int x, int y) {
        return !isOutOfBounds(x, y) && this.board[x][y].piece == -1;
    }
    
    // Cell position is inside board and empty or occupied by less priority piece with greater movement than the paramter one
    public boolean isFreeCell(int x, int y, int movement, int pieceIndex) {
        return !isOutOfBounds(x, y) && (this.board[x][y].piece == -1 || (this.board[x][y].piece > pieceIndex && this.board[x][y].movement >= movement));
    }
    
    // Check all the board and reset cells to return to previous movements
    public void prune (int movementToPrune, int pieceIndex) {
        BoardCell val;
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                val = this.board[i][j];
                if (pieceIndex <= val.piece) { // cell with piece of less priority than the one that moves
                    if (val.movement >= movementToPrune) { // reset cells with greater or equal movement than the one to be taken
                        this.board[i][j] = new BoardCell(-1, -1);
                        this.movementCounter--;
                    } else if (val.movement == movementToPrune - 1) { // reset piece position to the one previous to the movement
                        this.pieces[val.piece].setPos(i, j);
                    }
                } else if (val.piece != -1 && pieceIndex > val.piece) { // cell with piece of mpre priority than the one that moves
                    if (val.movement > movementToPrune) { // reset cells with greater movement than the one to be taken
                        this.board[i][j] = new BoardCell(-1, -1);
                        this.movementCounter--;
                    } else if (val.movement == movementToPrune) { // reset piece position to the one with the movement
                        this.pieces[val.piece].setPos(i, j);
                    }
                }
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    private Piece addPiecePlayer (String name, int x, int y) {
        try {
            // Instantiating the object from the class using the class name (string)
            Class loader = Class.forName("practica2.pieces."+name);
            /**********************************************************/
            int numberOfConstructors = loader.getConstructors().length;
            Piece newpiece;
            if (numberOfConstructors > 1) {
                Constructor ctor = loader.getConstructor(int.class);
                newpiece = (Piece) ctor.newInstance(this.boardSize );
            } else {
                Constructor ctor = loader.getDeclaredConstructor(new Class[0]);
                newpiece = (Piece) ctor.newInstance(new Object[0]);
            }
            
            /*if (newpiece.afectaDimension()) {
                newpiece = (Piece) ctor.newInstance(new Object[] { this.boardSize });
            }*/
            // Set position piece
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
                break;
        }
    }
}
