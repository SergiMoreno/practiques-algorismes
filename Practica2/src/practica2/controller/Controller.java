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
import practica2.pieces.Piece;
import practica2.view.ViewEvent;

/**
 *
 * @author usuario
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
        int boardSize = model.getBoardSize();
        
        
        //Stack<PieceState> states = new Stack<PieceState>();   // Stack of states
        List<Stack<PieceState>> states = new ArrayList<Stack<PieceState>>();   // Stack of states
        for (int i = 0; i < model.getNumPieces(); i++) {
            Stack<PieceState> s = new Stack<PieceState>();
            states.add(s);
        }
        
        
        // Start algorithm
        while (!isSolution && !noSolution) {
            turn = model.getPieceTurn();
            Stack<PieceState> currentStack = states.get(turn);
            // Generate descendance
            for (int i = 0; i < model.getNumPieceMovements(turn); i++) {
                int x = model.getPieceMoveX(turn, i);
                int y = model.getPieceMoveY(turn, i);
                int px = model.getPiecePosX(turn);
                int py = model.getPiecePosY(turn);
                int movement = model.getMovement();
                int newx = x + px;
                int newy = y + py;
                PieceState state = new PieceState(newx, newy, movement);
                
                boolean valid = model.isValidMovement(newx, newy);
                if (valid) {
                    currentStack.add(state);
                }
            }
            noSolution = currentStack.empty();
            if (!noSolution) {
                PieceState current = currentStack.pop();

                boolean valid = model.isValidMovement(current.posx, current.posy);
                while (!valid) {
                    current = currentStack.pop();
                    valid = model.isValidMovement(current.posx, current.posy);
                }
                
                if (current.movement < model.getMovement())
                    model.prune(current.movement, turn);
                
                main.notify(new ModelEvent(current.posx, current.posy, current.movement, turn));
                main.notify(new ViewEvent());
                
                if (model.getOccupiedCells() == (boardSize * boardSize)) 
                    isSolution = true;
            }
            
            try {
                //Thread.sleep(10000/speed);
                Thread.sleep(10000/speed);
            } catch (InterruptedException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        System.out.println("Number of cell visited : " + model.getOccupiedCells() + "/" + (boardSize*boardSize));
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
