package practica6.model;

import java.util.ArrayList;
import java.util.Random;
import practica6.Event;
import practica6.EventListener;
import practica6.Main;

/**
 *
 * @author usuario
 */
public class Model implements EventListener {
    private Main main;
    private int [][] currentState;
    private int [][] goalState;
    private int puzzleSize;
    // Position of the empty tile
    private int [] emptyPosition;
    // 4 possible movements
    private final int [] movementX = {0, 1, 0, -1};
    private final int [] movementY = {-1, 0, 1, 0};
    
    public Model(Main main, int puzzleSize) {
        this.main = main;
        
        this.puzzleSize = puzzleSize;
        this.emptyPosition = new int[2];
        setStates();
    }
    
    private void setStates() {
        // Goal State
        ArrayList<Integer> ind = new ArrayList<Integer>();
        int num = 0;
        goalState = new int[puzzleSize][puzzleSize];
        for (int i = 0; i < puzzleSize; i++) {
            for (int j = 0; j < puzzleSize; j++) {
                ind.add(num);
                goalState[i][j] = num++;
            }
        }
        ind.remove(ind.size()-1);
        ind.add(-1);
        goalState[puzzleSize-1][puzzleSize-1] = -1;
        
        // Iterate until a valid initial state is generated
        boolean validInitial = false;
        while (!validInitial) {
            ArrayList<Integer> indCopy = new ArrayList<>(ind);
            num = 0;
            int linearPuzzle[] = new int[puzzleSize*puzzleSize];
            // Initial State
            this.currentState = new int[puzzleSize][puzzleSize];
            Random rand = new Random();
            for (int i = 0; i < puzzleSize; i++) {
                for (int j = 0; j < puzzleSize; j++) {
                    int index = rand.nextInt(indCopy.size());
                    int val = indCopy.remove(index);
                    linearPuzzle[num++] = val;
                    this.currentState[i][j] = val;
                    if (val == -1) {
                        this.emptyPosition[0] = i;
                        this.emptyPosition[1] = j;
                    }
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
            if ((this.getEmptyPositionY() + this.getEmptyPositionX()) % 2 != 0) inv_count++;
            if (inv_count % 2 == 0) System.out.println("INITIAL SOL");
            else System.out.println("INITIAL ERROR");
            if (inv_count % 2 == 0) validInitial = true;
        }
    }
    
    public int getPuzzleSize() {
        return this.puzzleSize;
    }
    
    public int getCellIndex(int i, int j) {
        return this.currentState[i][j];
    }
    
    public int getEmptyPositionX() {
        return this.emptyPosition[1];
    }
    
    public int getEmptyPositionY() {
        return this.emptyPosition[0];
    }
    
    public int getMovementX(int index) {
        return this.movementX[index];
    }
    
    public int getMovementY(int index) {
        return this.movementY[index];
    }
    
    public void updateCurrentState(int [][] mat) {
        for (int i = 0; i < puzzleSize; i++) {
            for (int j = 0; j < puzzleSize; j++) {
                this.currentState[i][j] = mat[i][j];
                if (this.currentState[i][j] == -1) {
                    this.emptyPosition[0] = i;
                    this.emptyPosition[1] = j;
                }
            }
        }
    }
    
    public boolean isOutOfBounds(int i, int j) {
        return i < 0 || i == puzzleSize || j < 0 || j == puzzleSize;
    }
    
    public int getGoalIndex(int i, int j) {
        return this.goalState[i][j];
    }
    
    public int [][] getCurrentState() {
        return this.currentState;
    }
    
    public int getColumn(int val) {
        return val % puzzleSize;
    }
    
    public int getRow(int val) {
        return val / puzzleSize;
    }

    @Override
    public void notify(Event e) {
        ModelEvent event = (ModelEvent) e;
        
        switch (event.type) {
            case UPDATE_PUZZLE_SIZE:
                this.puzzleSize = event.puzzleSize;
            case RESET:
                setStates();
                break;
        }
    }
}
