package practica2.controller;

import java.util.ArrayList;
import java.util.Stack;
import practica2.Event;
import practica2.EventListener;
import practica2.Main;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import practica2.model.Model;
import practica2.model.ModelEvent;
import practica2.pieces.Piece;
import practica2.view.ViewEvent;

/**
 *
 * @author usuario
 */
/*
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
*/
class PieceState {
    final public int posx;
    final public int posy;
    final public int movement;
    
    public PieceState (int x, int y, int movement) {
        this.posx = x;
        this.posy = y;
        this.movement = movement;
    }
}

public class Controller extends Thread implements EventListener {
    private Main main;
    static private AtomicBoolean stop;  // Avoid dirty reads between threads
    private int speed;
    
    public Controller(Main main) {
        this.main = main;
        this.speed = Main.DEFAULT_SPEED;
    }
    
    @Override
    public void run () {
        Model model = this.main.getModel();
        boolean isSolution = false, noSolution = false;
        int turn;
        int modelSize = model.getSize();
        Stack<PieceState> states = new Stack<PieceState>();   // Stack of states
        // Start algorithm
        while (!isSolution && !noSolution) {
            turn = model.getPieceTurn();
            // Generate descendance
            for (int j = 0; j < model.getNumPieceMovements(turn); j++) {
                PieceState state = new PieceState(
                        model.getPiecePosX(turn) + model.getPieceMovX(turn, j),
                        model.getPiecePosY(turn) + model.getPieceMovY(turn, j),
                        model.getMovement()
                );
                if (model.isValidMovement(state.posx, state.posy))
                    states.add(state);
            }
            noSolution = states.empty();
            if (!noSolution) {
                PieceState current = states.pop();
                
                if (current.movement < model.getMovement())
                    model.prune(current.movement);
                
                main.notify(new ModelEvent(current.posx, current.posy, current.movement));
                main.notify(new ViewEvent());
                
                if (model.getOccupiedCells() == (modelSize * modelSize)) 
                    isSolution = true;
            }
            
            try {
                Thread.sleep(10000/speed);
            } catch (InterruptedException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
/*
                
        Model model = this.main.getModel();
        Stack<PieceState>[] states = new Stack[model.getNumPieces()];
        for (int i = 0; i < states.length; i++) states[i] = new Stack<PieceState>();
        boolean isSolution = false, noSolution = false;
        int modelSize = model.getSize();
        
        // Put first state into the stack
        for (int turn = 0; turn < model.getNumPieces() && !isSolution && !noSolution; turn++) {
            // Generate descendance
            for (int j = 0; j < model.getNumPieceMovements(turn); j++) {
                PieceState state = new PieceState(
                        turn,
                        model.getPieceCurrentX(turn) + model.getPieceMovX(turn, j),
                        model.getPieceCurrentY(turn) + model.getPieceMovY(turn, j),
                        model.getPieceLastMov(turn) + 1
                );
                if (model.isValidMovement(state.posx, state.posy))
                    states[turn].add(state);
            }
            
            noSolution = states[turn].empty();
            if (!noSolution) {
                PieceState current = states[turn].pop();
                int pieceLastMov = model.getPieceLastMov(turn);
                
                if (current.movementCounter < pieceLastMov)
                    model.prunePieceRoute(turn, current.movementCounter);
                
                main.notify(new ModelEvent(turn, current.posx, current.posy, current.movementCounter));
                main.notify(new ViewEvent());
                
                if (model.getOccupiedCells() == (modelSize * modelSize)) 
                    isSolution = true;
            }
            
            if (turn == model.getNumPieces()-1) turn = -1;
            
            try {
                Thread.sleep(10000/speed);
            } catch (InterruptedException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
*/
    }
    
    @Override
    public void notify(Event e) {
        ControllerEvent event = (ControllerEvent) e;
        
        switch (event.type) {
            case SET_SPEED -> {
                this.speed = event.speed;
            }
            case START -> {
                (new Controller(this.main)).start();
            }
            case STOP -> {
                this.stop.set(true);    // Stops the backtracking algorithm
            }
        }
    }
}
