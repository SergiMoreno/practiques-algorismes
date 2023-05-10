package practica4.model;

/**
 *
 * @author usuario
 */
public class Route {
    private int [] poblations;
    private double [] criterias;
    private double weight;
    
    public Route(int p1, int p2, double d, double t, double m) {
        this.poblations = new int[2];
        this.poblations[0] = p1;
        this.poblations[1] = p2;
        
        this.criterias = new double[3];
        this.criterias[0] = d;
        this.criterias[1] = t;
        this.criterias[2] = m;
    }
    
    public int getOrigin() {
        return this.poblations[0];
    }
    
    public int getDestination() {
        return this.poblations[1];
    }
    
    public double getValue() {
        return this.weight;
    }
    
    public void setWeight(boolean [] c, double [] p) {
        this.weight = 0.0;
        for (int i = 0; i < this.criterias.length; i++) {
            if (c[i]) {
                this.weight += this.criterias[i] * p[i];
            }
        }
    }
}
