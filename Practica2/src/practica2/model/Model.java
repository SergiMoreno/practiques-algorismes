package practica2.model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import practica2.Event;
import practica2.EventListener;
import practica2.EventType;
import practica2.Main;
import practica2.pieces.*;

/**
 *
 * @author usuario
 */
public class Model implements EventListener {
    
    private Main main;
    
    // Data structure that conatains the chess board
    private BoardCell[][] board;
    
    // Contains the pieces playing
    private ArrayList<Piece> players;
    
    public Model(Main main, int boardSize) {
        this.main = main;
        createBoard(boardSize);
        this.players = null;
    }
    
    private void createBoard (int size) {
        this.board = new BoardCell[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = new BoardCell(); 
            }
        }
    }
    
    private void visitCell (int x, int y, String pieceImage, int movement) {
        this.board[x][y].pieceImage = pieceImage;
        this.board[x][y].movement = movement;
    }
    
    public void addPiecePlayer (String name) {
    } 
    
    public BoardCell[][] getBoard () {
        return this.board;
    }
    
    @Override
    public void notify(Event e) {
        ModelEvent event = (ModelEvent) e;
    }
}
