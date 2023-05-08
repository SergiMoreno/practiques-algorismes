package practica4.model;

/**
 *
 * @author usuario
 */
public class Route {
    private int [] poblations;
    private double distance, time, money;
    private boolean quality;
    
    public Route(int p1, int p2, double d) {
        this.distance = d;
        this.time = 0.0;
        this.money = 0.0;
        this.poblations = new int[2];
        this.poblations[0] = p1;
        this.poblations[1] = p2;
    }
    
    public int getPoblationFrom(int indexp) {
        return this.poblations[0] == indexp ? this.poblations[1] : this.poblations[0];
    }
    
    public double getDistance() {
        return this.distance;
    }
    
    public double getTime() {
        return this.time;
    }
    
    public double getMoney() {
        return this.money;
    }
}
