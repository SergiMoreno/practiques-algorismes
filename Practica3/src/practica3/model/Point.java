package practica3.model;

/**
 *
 * @author usuario
 */
public class Point implements Comparable<Point> {
    public final int x, y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Override method to compare Point objects
    @Override
    public int compareTo(Point o) {
        // X
        int difX = o.x - this.x;
        int powerX = difX * difX;
        // Y
        int difY = o.y - this.y;
        int powerY = difY * difY;
        
        int sum = powerX + powerY;
        
        long aux = Math.round(Math.sqrt((double) sum));

        int dis = (int) aux;
        
        return dis;
    }
}
