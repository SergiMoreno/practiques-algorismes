package practica6.controller;

import java.time.Duration;
import java.util.HashMap;
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
        
        HashMap<String, Integer> hash = new HashMap<String, Integer>();
        PriorityQueue<PuzzleState> minHeap = new PriorityQueue<>();
        PuzzleState first = new PuzzleState(model.getCurrentState(), 
                                                  model.getEmptyPositionX(), model.getEmptyPositionY(), 
                                                model.getEmptyPositionX(), model.getEmptyPositionY(), 
                                               0);
        first.setCost(calculateHeuristic(first));
        
        minHeap.add(first);

        /* Branch and Bound */
        boolean result = false;
        int totalCost = 0;
        int pathCost = 0;
        int [] mov = new int[2];
        try {
            while (!minHeap.isEmpty()) {
                PuzzleState p = minHeap.poll();
                pathCost = p.level + 1;

                Thread.sleep(Duration.ZERO);
                
                if (p.cost == 0) {
                    result = true;
                    break;
                }
                
                for (int i = 0; i < 4; i++) {
                    mov[0] = model.getMovementX(i);
                    mov[1] = model.getMovementY(i);
                    if (model.isOutOfBounds(p.x + mov[0], p.y + mov[1])) continue;

                    PuzzleState ps = new PuzzleState(p.currentState,
                            p.x, p.y,
                            p.x + mov[0], p.y + mov[1],
                            pathCost);
                    ps.setCost(calculateHeuristic(ps));
                    
                    if (hash.containsKey(ps.key)) {
                        if (pathCost < hash.get(ps.key)) {
                            hash.replace(ps.key, pathCost);
                            minHeap.add(ps);
                        }
                    } else {
                        hash.put(ps.key, pathCost);
                        minHeap.add(ps);
                    }
                }
            }
            
            if (result) this.main.notify(new ViewEvent(totalCost));
            else this.main.notify(new ViewEvent());
        } catch (InterruptedException ex) {
            System.out.println("Execution Stopped. Cost calculated until stop : " + totalCost);
        }
    }

    private int calculateHeuristic(PuzzleState ps) {
        int dim = model.getPuzzleSize();
        switch (this.heuristic) {
            case N_WRONG_CELLS -> {
                int numWrongCells = 0;
                for (int i = 0; i < dim; i++) {
                    for (int j = 0; j < dim; j++) {
                        if (ps.currentState[i][j] != -1 && ps.currentState[i][j] != model.getGoalIndex(i, j)) numWrongCells++;
                    }
                }
                return numWrongCells;
            }
            case MANHATTAN -> {
                int distance = 0;
                for (int i = 0; i < dim; i++) {
                    for (int j = 0; j < dim; j++) {
                        if (ps.currentState[i][j] != -1 && ps.currentState[i][j] != model.getGoalIndex(i, j)) {
                            int row = model.getRow(ps.currentState[i][j]);
                            int column = model.getColumn(ps.currentState[i][j]);
                            distance += Math.abs(i - row) + Math.abs(j - column);
                        }
                    }
                }
                return distance;
            }
        }
        return 0;
    }
    
    /*private String matToString(int [][] mat) {
        String result = "";
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                result += mat[i][j] + "\t";
            }
            result += "\n";
        }
        return result;
    }*/
    
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
