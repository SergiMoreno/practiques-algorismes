package practica6.controller;

/**
 *
 * @author usuario
 */
public class PuzzleState implements Comparable<PuzzleState> {
    public int [][] currentState;
    public int x, y, level;
    public String key;
    public int accCost;
    public double cost;
    // To keep track of the solution path
    public PuzzleState parent;
    
    public PuzzleState(int [][] cs, int x, int y, int newX, int newY, int level, int accC, PuzzleState p) {
        this.currentState = new int[cs.length][cs.length];
        for (int i = 0; i < cs.length; i++) {
            for (int j = 0; j < cs.length; j++) {
                this.currentState[i][j] = cs[i][j];
            }
        }
        // move tile by 1 position
        int temp = this.currentState[y][x];
        this.currentState[y][x] = this.currentState[newY][newX];
        this.currentState[newY][newX] = temp;
         
        this.cost = Double.MAX_VALUE;
        this.level = level;
         
        // update new blank tile coordinates
        this.x = newX;
        this.y = newY;
        
        this.accCost = accC;
        
        this.key = "";
        for (int i = 0; i < currentState.length; i++) {
            for (int j = 0; j < currentState.length; j++) {
                this.key += currentState[i][j];
            }
        }
        
        this.parent = p;
    }
    
    public void setCost(double c) {
        this.cost = c;
        this.accCost += this.cost + this.level;
    }
    
    @Override
    public int compareTo(PuzzleState o) {
        return (this.cost + this.level) > (o.cost + o.level) ? 1 : -1;
    }
}
