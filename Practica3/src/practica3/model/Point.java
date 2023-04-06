package practica3.model;

/**
 *
 * @author usuario
 */
public class Point implements Comparable<Point> {
    public final double x, y;
    
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    // Return the euclidean distance between this point and the one passed by parameter
    public double distanceTo(Point o) {
        // X
        double difX = o.x - this.x;
        double powerX = difX * difX;
        // Y
        double difY = o.y - this.y;
        double powerY = difY * difY;
        
        double sum = powerX + powerY;
        
        long aux = Math.round(Math.sqrt(sum));
        
        return (double) aux;
    }

    // Override method to compare Point objects
    @Override
    public int compareTo(Point o) {
        // Order by X
        if (this.x > o.x) {
            return 1;
        }
        if (this.x < o.x) {
            return -1;
        }
        
        // Equal X, order by Y
        if (this.y > o.y) {
            return 1;
        }
        if (this.y < o.y) {
            return -1;
        }
        
        // Equal Point
        return 0;
    }
}
