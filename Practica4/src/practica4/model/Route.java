package practica4.model;

/**
 *
 * @author usuario
 */
public class Route {
    private int [] poblations;
    private double value;
    
    public Route(int p1, int p2, double v) {
        this.value = v;
        this.poblations = new int[2];
        this.poblations[0] = p1;
        this.poblations[1] = p2;
    }
    
    public int getPoblationFrom(int indexp) {
        return this.poblations[0] == indexp ? this.poblations[1] : this.poblations[0];
    }
    
    public double getValue() {
        return this.value;
    }
}
