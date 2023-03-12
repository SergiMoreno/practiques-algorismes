package practica2.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
    private final ArrayList<Piece> players = new ArrayList<Piece>();
    
    public Model(Main main, int boardSize) {
        this.main = main;
        createBoard(boardSize);
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
    
    private void addPiecePlayer (String name) {
        /*try {
            // Instantiating the object from the class using the class name (string)
            Class loader = Class.forName("practica2.pieces."+name);
            Constructor ctor = loader.getDeclaredConstructor(new Class[0]);
            Piece newpiece = (Piece) ctor.newInstance(new Object[0]);
            players.add(newpiece);
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
        }*/
    } 
    
    private void removePiecePlayers () {
        players.clear();
    } 
    
    private void resetModel (int boardSize) {
        removePiecePlayers();
        createBoard(boardSize);
    }
    
    public BoardCell[][] getBoard () {
        return this.board;
    }
    
    @Override
    public void notify(Event e) {
        ModelEvent event = (ModelEvent) e;
    }
}
