package practica6.controller;

/**
 *
 * @author usuario
 */
public class PuzzleState implements Comparable<PuzzleState> {
    public int [][] currentState;
    public int cost, x, y, level;
    public int prevx, prevy;
    public String key;
    
    public PuzzleState(int [][] cs, int x, int y, int newX, int newY, int level) {
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
         
        this.cost = Integer.MAX_VALUE;// set number of misplaced tiles
        this.level = level;// set number of moves so far
         
        // update new blank tile coordinates
        this.prevx = this.x;
        this.prevy = this.y;
        this.x = newX;
        this.y = newY;
        
        this.key = "";
        for (int i = 0; i < currentState.length; i++) {
            for (int j = 0; j < currentState.length; j++) {
                this.key += currentState[i][j];
            }
        }
    }
    
    public void setCost(int c) {
        this.cost = c;
    }
    
    public int getCost() {
        return this.cost + this.level;
    }

    @Override
    public int compareTo(PuzzleState o) {
        return (this.cost + this.level) > (o.cost + o.level) ? 1 : -1;
    }
}
