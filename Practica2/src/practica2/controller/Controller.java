package practica2.controller;

import java.util.Stack;
import practica2.Event;
import practica2.EventListener;
import practica2.Main;
import java.util.concurrent.atomic.AtomicBoolean;
import practica2.model.Model;
import practica2.pieces.Piece;

/**
 *
 * @author usuario
 */

class PieceState {
    final public int piece;
    final public int posx;
    final public int posy;
    final public int movementCounter;
    
    public PieceState (int piece, int x, int y, int movementCounter) {
        this.piece = piece;
        this.posx = x;
        this.posy = y;
        this.movementCounter = movementCounter;
    }
}

public class Controller extends Thread implements EventListener {
    private Main main;
    static private AtomicBoolean stop;  // Avoid dirty reads between threads
    private int speed;
    
    public Controller(Main main) {
        this.main = main;
    }
    
    @Override
    public void run () {
        Model model = this.main.getModel();
        Stack<PieceState[]> states = new Stack<PieceState[]>();
        
        // Put first state into the stack
        PieceState[] gameState = new PieceState[model.getNumPieces()];
        for (int i = 0; i < gameState.length; i++) {
            //Piece piece = model.getPiece(i);
            //gameState[i] = new PieceState(i, piece.getPosX(), piece.getPosY(), 0);
        }
        states.add(gameState);
        
        // Iterate over the tree
        PieceState[] current;
        while (!states.empty()) {
            current = states.pop();
            //model.movePiece(current.piece, current.posx, current.posy, movy);
        }
    }
    
    @Override
    public void notify(Event e) {
        ControllerEvent event = (ControllerEvent) e;
        
        switch (event.type) {
            case SET_SPEED -> {
                this.speed = event.speed;
            }
            case START -> {
                run();
            }
            case STOP -> {
                this.stop.set(true);    // Stops the backtracking algorithm
            }
        }
    }
}
