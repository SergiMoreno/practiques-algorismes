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
    
    // Return the euclidean distance between this point and the one passed by parameter
    public int distanceTo(Point o) {
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

    // Override method to compare Point objects
    @Override
    public int compareTo(Point o) {
        // Order by X
        if (this.x > o.x) {
            return 1;
        } else if (this.x < o.x) {
            return -1;
        }
        
        // Equal X, order by Y
        if (this.y > o.y) {
            return 1;
        } else if (this.y < o.y) {
            return -1;
        }
        
        // Equal Point
        return 0;
    }
}
