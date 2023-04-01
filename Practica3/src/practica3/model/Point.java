package practica3.model;

/**
 *
 * @author usuario
 */
public class Point implements Comparable<Point> {
    private int x, y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }

    // Override method to compare Point objects
    @Override
    public int compareTo(Point o) {
        // X
        int difX = o.getX() - this.x;
        int powerX = difX * difX;
        // Y
        int difY = o.getY() - this.y;
        int powerY = difY * difY;
        
        int sum = powerX + powerY;
        
        long aux = Math.round(Math.sqrt((double) sum));

        int dis = (int) aux;
        
        return dis;
    }
}
