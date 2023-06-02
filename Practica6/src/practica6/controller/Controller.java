package practica6.controller;

import java.time.Duration;
import java.util.PriorityQueue;
import practica6.Event;
import practica6.EventListener;
import practica6.Main;
import practica6.model.Model;
import practica6.view.ViewEvent;

/**
 *
 * @author usuario
 */
public class Controller extends Thread implements EventListener {
    private Main main;
    private Model model;
    private Thread executionThread;
    private Heuristics heuristic;
    
    public Controller(Main main) {
        this.main = main;
    }
    
    @Override
    public void run() {
        model = this.main.getModel();
        
        PriorityQueue<PuzzleState> minHeap = new PriorityQueue<PuzzleState>();
        PuzzleState first = new PuzzleState(model.getCurrentState(), 
                                                  model.getEmptyPositionX(), model.getEmptyPositionY(), 
                                                model.getEmptyPositionX(), model.getEmptyPositionY(), 
                                               0);
        first.setCost(0);
        minHeap.add(first);

        /* Branch and Bound */
        int acc = 1, totalCost = 0;
        int prevx = first.x;
        int prevy = first.y;
        try {
            while (!minHeap.isEmpty()) {
                PuzzleState p = minHeap.poll();
                model.updateCurrentState(p.currentState);
                totalCost += p.getCost();
                minHeap.removeIf(filter -> (filter.cost + filter.level) > (p.cost+ p.level));
                //System.out.println(matToString(p.currentState));
                //this.main.notify(new ViewEvent());
                Thread.sleep(Duration.ZERO);
                /*System.out.println("Actual cost: " + (p.cost+p.level) 
                                    + "(Lvl -> " + p.level +  ", Wrong -> " + p.cost + ")");*/
                if (this.goalAchived(p.currentState)) break;
                for (int i = 0; i < 4; i++) {
                    int movx = model.getMovementX(i);
                    int movy = model.getMovementY(i);
                    if ((model.isOutOfBounds(p.x + movx, p.y + movy))
                        || (prevx == p.x + movx && prevy == p.y + movy)) continue;

                    PuzzleState ps = new PuzzleState(p.currentState,
                            p.x, p.y,
                            p.x + movx, p.y + movy,
                            acc);
                    int g = calculateHeuristic(ps);
                    ps.setCost(g);
                    //System.out.println(matToString(ps.currentState));
                    if (this.isReachable(ps)) minHeap.add(ps);
                }
                prevx = p.x;
                prevy = p.y;
                acc++;
            }
            this.main.notify(new ViewEvent(totalCost));
        } catch (InterruptedException ex) {
            System.out.println("Execution Stopped. Cost calculated until stop : " + totalCost);
        }
    }

    private int calculateHeuristic(PuzzleState ps) {
        switch (this.heuristic) {
            case N_WRONG_CELLS -> {
                int numWrongCells = 0;
                int dim = model.getPuzzleSize();
                for (int i = 0; i < dim; i++) {
                    for (int j = 0; j < dim; j++) {
                        if (ps.currentState[i][j] != -1 && ps.currentState[i][j] != model.getGoalIndex(i, j)) numWrongCells++;
                    }
                }
                return numWrongCells;
            }
            case MANHATTAN -> {
                
            }
        }
        return 0;
    }
    
    private String matToString(int [][] mat) {
        String result = "";
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                result += mat[i][j] + "\t";
            }
            result += "\n";
        }
        return result;
    }
    
    private boolean goalAchived(int [][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                if (mat[i][j] != model.getGoalIndex(i, j)) return false;
            }
        }
        return true;
    }
    
    private boolean isReachable(PuzzleState ps) {
        int linearPuzzle[] = new int[ps.currentState.length*ps.currentState.length];
        int num = 0;
        for (int i = 0; i < ps.currentState.length; i++) {
            for (int j = 0; j < ps.currentState.length; j++) {
                linearPuzzle[num++] = ps.currentState[i][j];
            }
        }
        
        int inv_count = 0;
        for (int i = 0; i < linearPuzzle.length; i++) {
            for (int j = i + 1; j < linearPuzzle.length; j++) {
                // Value 0 is used for empty space
                //if (linearPuzzle[i] > -1 && linearPuzzle[j] > -1 && linearPuzzle[i] > linearPuzzle[j]) {
                if (linearPuzzle[j] > -1 && linearPuzzle[i] > linearPuzzle[j]) {
                    inv_count++;
                }
                if (linearPuzzle[i] == -1) {
                    inv_count++;
                }
            }
        }
        
        
        if ((ps.x + ps.y) % 2 != 0) inv_count++;
        //System.out.println("INV " + inv_count);
        
        /*if (inv_count % 2 == 0) {
            //System.out.println("SOL");
            return true;
        }
        else {
           // System.out.println("ERROR");
            return false;
        }*/
        return inv_count % 2 == 0;
    }
    
    @Override
    public void notify(Event e) {
        ControllerEvent event = (ControllerEvent) e;
        
        switch (event.type) {
            case START -> {
                this.heuristic = event.heuristic;
                // When start event notified, new Thread is initialized
                this.executionThread = new Thread(this);
                this.executionThread.start();
            }
            case STOP -> {
                if (this.executionThread != null) {
                    this.executionThread.interrupt();
                }
            }
        }
    }    
}
