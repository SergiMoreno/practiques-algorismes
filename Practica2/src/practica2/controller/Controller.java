package practica2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import practica2.Event;
import practica2.EventListener;
import practica2.Main;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import practica2.model.Model;
import practica2.model.ModelEvent;
import practica2.view.ViewEvent;

/**
 *
 * @author usuario
 */
class PieceState {
    final public int posx;
    final public int posy;
    final public int movement;
    final public int pieceIndex;
    
    public PieceState (int x, int y, int movement, int pieceIndex) {
        this.posx = x;
        this.posy = y;
        this.movement = movement;
        this.pieceIndex = pieceIndex;
    }
    
    public boolean isEqual(PieceState other) {
        return this.posx == other.posx 
                && this.posy == other.posy 
                && this.movement == other.movement 
                && this.pieceIndex == other.pieceIndex;
    }
}

public class Controller extends Thread implements EventListener {
    private Main main;
    static private AtomicBoolean stop;  // Avoid dirty reads between threads
    static int speed;
    
    public Controller(Main main) {
        this.main = main;
        this.speed = Main.DEFAULT_SPEED;
    }
    
    @Override
    public void run () {
        Model model = this.main.getModel();
        // Variable to control the end of the algorithm
        boolean isSolution = false, noSolution = false;
        int turn;
        int boardSize = model.getBoardSize();
        
        PieceState [] initStates = new PieceState[model.getNumPieces()];
        Stack<PieceState> stack = new Stack<PieceState>();   // Stack of states
        for (int i = 0; i < model.getNumPieces(); i++) { // Initialize each stack
            int x = model.getPiecePosX(i);
            int y = model.getPiecePosY(i);
            PieceState initialState = new PieceState(x,y,0,i);
            initStates[i] = initialState;
            stack.add(initialState);
        }
        
        // Start algorithm
        while (!isSolution && !noSolution) {
            turn = model.getPieceTurn(); // Piece turn
            //Stack<PieceState> currentStack = states.get(turn); // Piece states
            
            int posx = model.getPiecePosX(turn);
            int posy = model.getPiecePosY(turn);
            // Generate descendance
            for (int i = 0; i < model.getNumPieceMoves(turn); i++) {
                int movex = model.getPieceMoveX(turn, i);
                int movey = model.getPieceMoveY(turn, i);
                int movement = model.getMovement();
                
                int x = movex + posx;
                int y = movey + posy;
                PieceState state = new PieceState(x, y, movement, turn);
                
                boolean valid = model.isValidMovement(x, y);
                if (valid) {
                    stack.add(state);
                }
            }
            
            noSolution = stack.empty();
            if (!noSolution) {
                PieceState current = stack.pop();

                // Get the last valid state on the stack
                //boolean hasToPrune = false;
                boolean valid = model.isFreeCell(current.posx, current.posy, current.movement, turn);
                if (current.posx == 0 && current.posy == 1) {
                        System.out.println("");
                    }
                while ((!valid || current.pieceIndex != turn) && !stack.empty()) {
                //while ((!valid && !stack.empty()) || current.pieceIndex != turn) {
                    current = stack.pop();
                    if (current.posx == 0 && current.posy == 1) {
                        System.out.println("");
                    }
                    valid = model.isFreeCell(current.posx, current.posy, current.movement, turn);
                    //hasToPrune = true;
                }
                
                //if (stack.empty()) break;
                
                // Prune non-necessary states of each stack in the list
                /*if (hasToPrune) {
                    for (int i = 0; i < states.size(); i++) {
                        Stack<PieceState> others = states.get(i);
                        if (!others.isEmpty()) {
                            PieceState top = others.peek();
                            while (top.movement > current.movement) {
                                others.pop();
                                if (others.isEmpty()) break;
                                top = others.peek();
                            }
                        }
                    }
                }*/
                
                if (current.movement < model.getMovement()) // Prune board cells
                    model.prune(current.movement, turn);
                //else if (stack.empty()) model.prune(0, turn);
                
                // Move piece
                main.notify(new ModelEvent(current.posx, current.posy, current.movement, turn));
                // Refresh board display
                main.notify(new ViewEvent());
                
                // Check if all the cells have been occupied
                if (model.getOccupiedCells() == (boardSize * boardSize)) 
                    isSolution = true;
                /*if (current.isEqual(initStates[turn])) {
                    noSolution = true;
                }*/
            }
            
            try {
                Thread.sleep(10000/(speed*20));
                //Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        System.out.println("Number of cell visited : " + model.getOccupiedCells() + "/" + (boardSize*boardSize));
        // Notify the algorithm end
        main.notify(new ViewEvent(isSolution));
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
