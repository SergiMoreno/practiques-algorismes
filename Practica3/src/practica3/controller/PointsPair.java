package practica3.controller;

/**
 *
 * @author usuario
 */
// Class to keep the index references to the points and the distance between them
public class PointsPair implements Comparable<PointsPair> {
    final public int refPoint1, refPoint2;
    final private double dist;

    public PointsPair(int r1, int r2, double dist) {
        this.refPoint1 = r1;
        this.refPoint2 = r2;
        this.dist = dist;
    }
    
    public double getDistance() {
        return this.dist;
    }
    
    static public PointsPair maxDistance() {
        return new PointsPair(-1, -1, Double.MAX_VALUE);
    }

    @Override
    public String toString() {
        return "PointsPair{" + "refPoint1=" + refPoint1 + ", refPoint2=" + refPoint2 + ", dist=" + dist + "}";
    }
    
    @Override
    public int compareTo(PointsPair o) {
        if (this.dist > o.dist) {
            return -1;
        }
        if (this.dist < o.dist) {
            return 1;
        }

        return 0;
    }
    
    public boolean equals(PointsPair p) {
        return (this.refPoint1 == p.refPoint1 && this.refPoint2 == p.refPoint2) ||
                (this.refPoint1 == p.refPoint2 && this.refPoint2 == p.refPoint1);
    }
}
