package practica6.controller;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import practica6.Event;
import practica6.EventListener;
import practica6.Main;
import practica6.model.Model;
import practica6.model.ModelEvent;
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
    private int speed;
    
    public Controller(Main main) {
        this.main = main;
        
        this.speed = Main.DEFAULT_SPEED;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public void run() {
        
        /* Branch and Bound */
        
        model = this.main.getModel();
        HashMap<String, Integer> hash = new HashMap<String, Integer>();
        PriorityQueue<PuzzleState> minHeap = new PriorityQueue<>();
        PuzzleState first = new PuzzleState(model.getCurrentState(), 
                                             model.getEmptyPositionX(), model.getEmptyPositionY(), 
                                           model.getEmptyPositionX(), model.getEmptyPositionY(), 
                                          0,
                                           0,
                                            null);
        first.setCost(calculateHeuristic(first));
        minHeap.add(first);
        
        boolean result = false;
        int totalCost = 0, pathLevel;
        int [] mov = new int[2];
        try {
            while (!minHeap.isEmpty()) {
                PuzzleState p = minHeap.poll();
                totalCost = p.accCost;
                pathLevel = p.level + 1;

                Thread.sleep(Duration.ZERO);
                if (goalAchived(p.currentState)) {
                    ArrayList<PuzzleState> solList = new ArrayList();                  
                    
                    // Get the solution path ordered from first to last
                    while (p.parent != null) {
                        solList.add(0, p);
                        p = p.parent;
                    }
                    
                    // Show every step
                    for (int i = 0; i < solList.size(); i++) {
                        this.main.notify(new ModelEvent(solList.get(i).currentState));
                        this.main.notify(new ViewEvent(true));
                        Thread.sleep(1000/speed);
                    }
                    
                    result = true;
                    break;
                } else if (p.level == 0) {
                    p.accCost = 0;
                }
                
                for (int i = 0; i < 4; i++) {
                    mov[0] = model.getMovementX(i);
                    mov[1] = model.getMovementY(i);
                    if (model.isOutOfBounds(p.x + mov[0], p.y + mov[1])) continue;

                    PuzzleState ps = new PuzzleState(p.currentState,
                                                      p.x, p.y,
                                                        p.x + mov[0], p.y + mov[1],
                                                   pathLevel,
                                                   p.accCost,
                                                        p);
                    ps.setCost(calculateHeuristic(ps));
                    
                    if (hash.containsKey(ps.key)) {
                        if (pathLevel < hash.get(ps.key)) {
                            hash.replace(ps.key, pathLevel);
                            minHeap.add(ps);
                        }
                    } else {
                        hash.put(ps.key, pathLevel);
                        minHeap.add(ps);
                    }
                }
            }

            if (result) this.main.notify(new ViewEvent(totalCost));
            else this.main.notify(new ViewEvent(false));
        } catch (InterruptedException ex) {
            System.out.println("Execution Stopped. Cost calculated until stop : " + totalCost);
        }
    }

    private double calculateHeuristic(PuzzleState ps) {
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
            case EUCLIDEAN -> {
                double distance = 0;
                for (int i = 0; i < dim; i++) {
                    for (int j = 0; j < dim; j++) {
                        if (ps.currentState[i][j] != -1 && ps.currentState[i][j] != model.getGoalIndex(i, j)) {
                            int row = model.getRow(ps.currentState[i][j]);
                            int column = model.getColumn(ps.currentState[i][j]);
                            distance += Math.sqrt(Math.abs(i - row) * Math.abs(i - row) + Math.abs(j - column) * Math.abs(j - column));
                        }
                    }
                }
                return distance;
            }
            case LINEAR_CONFLICT -> {
                int conflicts = 0;
                // Row conflicts
                for (int i = 0; i < dim; i++) {
                    for (int j = 0; j < dim-1; j++) {
                        int row = model.getRow(ps.currentState[i][j]);
                        if (row != i) continue;
                        for (int k = j+1; k < dim; k++) {
                            int rowk = model.getRow(ps.currentState[i][k]);
                            if (row == rowk && (ps.currentState[i][j] > ps.currentState[i][k] || ps.currentState[i][j] == -1)) {
                                conflicts++;
                            }
                        }
                    }
                }
                
                // Column conflicts
                for (int j = 0; j < dim; j++) {
                    for (int i = 0; i < dim-1; i++) {
                        int column = model.getColumn(ps.currentState[i][j]);
                        if (column != j) continue;
                        for (int k = i+1; k < dim; k++) {
                            int columnk = model.getColumn(ps.currentState[k][j]);
                            if (column == columnk && (ps.currentState[i][j] > ps.currentState[k][j] || ps.currentState[i][j] == -1)) {
                                conflicts++;
                            }
                        }
                    }
                }
                conflicts *= 2;
                return conflicts;
            }
            case MAX_HEURISTIC -> {
                // max distance calculated
                int distance = 0;
                for (int i = 0; i < dim; i++) {
                    for (int j = 0; j < dim; j++) {
                        if (ps.currentState[i][j] != -1 && ps.currentState[i][j] != model.getGoalIndex(i, j)) {
                            int row = model.getRow(ps.currentState[i][j]);
                            int column = model.getColumn(ps.currentState[i][j]);
                            distance = Math.max(Math.abs(i - row) + Math.abs(j - column), distance);
                        }
                    }
                }
                return distance;
            }
            case ID -> {
                int linearPuzzle[] = new int[dim*dim];
                int num = 0;
                // Get the cells in a single array
                for (int i = 0; i < dim; i++) {
                    for (int j = 0; j < dim; j++) {
                        linearPuzzle[num++] = ps.currentState[i][j];
                    }
                }

                // Vertical inversions
                int inv_count_v = 0;
                for (int i = 0; i < linearPuzzle.length; i++) {
                    for (int j = i + 1; j < linearPuzzle.length; j++) {
                        if (linearPuzzle[i] > -1 && linearPuzzle[j] > -1 && linearPuzzle[i] > linearPuzzle[j]) {
                            inv_count_v++;
                        }
                    }
                }
                
                // Get the cells in a single array
                num = 0;
                for (int j = 0; j < dim; j++) {
                    for (int i = 0; i < dim; i++) {
                        linearPuzzle[num++] = ps.currentState[i][j];
                    }
                }
                
                // Horizontal inversions
                int inv_count_h = 0;
                for (int i = 0; i < linearPuzzle.length; i++) {
                    for (int j = i + 1; j < linearPuzzle.length; j++) {
                        // Value 0 is used for empty space
                        if (linearPuzzle[i] > -1 && linearPuzzle[j] > -1 && linearPuzzle[i] > linearPuzzle[j]) {
                            inv_count_h++;
                        }
                    }
                }
                
                inv_count_v = inv_count_v / 3 + inv_count_v % 3;
                inv_count_h = inv_count_h / 3 + inv_count_h % 3;
                
                return inv_count_v + inv_count_h;
            }
            case MD_LC -> {
                /* Manhattan Distance */
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
                
                /* Linear Conflict */
                
                // Row conflicts
                int conflicts = 0;
                for (int i = 0; i < dim; i++) {
                    for (int j = 0; j < dim-1; j++) {
                        int row = model.getRow(ps.currentState[i][j]);
                        if (row != i) continue;
                        for (int k = j+1; k < dim; k++) {
                            int rowk = model.getRow(ps.currentState[i][k]);
                            if (row == rowk && (ps.currentState[i][j] > ps.currentState[i][k] || ps.currentState[i][j] == -1)) {
                                conflicts++;
                            }
                        }
                    }
                }
                
                // Column conflicts
                for (int j = 0; j < dim; j++) {
                    for (int i = 0; i < dim-1; i++) {
                        int column = model.getColumn(ps.currentState[i][j]);
                        if (column != j) continue;
                        for (int k = i+1; k < dim; k++) {
                            int columnk = model.getColumn(ps.currentState[k][j]);
                            if (column == columnk && (ps.currentState[i][j] > ps.currentState[k][j] || ps.currentState[i][j] == -1)) {
                                conflicts++;
                            }
                        }
                    }
                }
                conflicts *= 2;
                distance += conflicts;
                return distance;
            }
            case MD_ID -> {
                /* Manhattan Distance */
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

                /* Inversion Distance */
                
                int linearPuzzle[] = new int[dim*dim];
                int num = 0;
                // Get cells in one array
                for (int i = 0; i < dim; i++) {
                    for (int j = 0; j < dim; j++) {
                        linearPuzzle[num++] = ps.currentState[i][j];
                    }
                }
            
                // Vertical inverions
                int inv_count_v = 0;
                for (int i = 0; i < linearPuzzle.length; i++) {
                    for (int j = i + 1; j < linearPuzzle.length; j++) {
                        // Value 0 is used for empty space
                        if (linearPuzzle[i] > -1 && linearPuzzle[j] > -1 && linearPuzzle[i] > linearPuzzle[j]) {
                            inv_count_v++;
                        }
                    }
                }
                
                // Get cells in one array
                num = 0;
                for (int j = 0; j < dim; j++) {
                    for (int i = 0; i < dim; i++) {
                        linearPuzzle[num++] = ps.currentState[i][j];
                    }
                }
                
                // Horizontal inversions
                int inv_count_h = 0;
                for (int i = 0; i < linearPuzzle.length; i++) {
                    for (int j = i + 1; j < linearPuzzle.length; j++) {
                        // Value 0 is used for empty space
                        if (linearPuzzle[i] > -1 && linearPuzzle[j] > -1 && linearPuzzle[i] > linearPuzzle[j]) {
                            inv_count_h++;
                        }
                    }
                }
                
                inv_count_v = inv_count_v / 3 + inv_count_v % 3;
                inv_count_h = inv_count_h / 3 + inv_count_h % 3;
                return distance + inv_count_v + inv_count_h;
            }
        }
        return 0;
    }
    
    private boolean goalAchived(int [][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                if (mat[i][j] != model.getGoalIndex(i, j)) return false;
            }
        }
        return true;
    }
    
    @Override
    public void notify(Event e) {
        ControllerEvent event = (ControllerEvent) e;
        
        switch (event.type) {
            case UPDATE_SPEED -> {
                this.speed = event.speed;
            }
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
