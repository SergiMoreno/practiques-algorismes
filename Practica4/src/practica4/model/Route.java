package practica4.model;

/**
 *
 * @author usuario
 */
public class Route implements Comparable<Route> {
    private int destination;
    private double [] criterias;
    private double weight;
    
    public Route(int p, double d, double t, double m) {
        this.criterias = new double[3];
        this.criterias[0] = d;
        this.criterias[1] = t;
        this.criterias[2] = m;
        
        this.destination = p;
        
        this.weight = 0.0;
    }
    
    public int getDestination() {
        return this.destination;
    }
    
    public double getValue() {
        return this.weight;
    }
    
    public void setWeight(boolean [] c, double [] p) {
        for (int i = 0; i < this.criterias.length; i++) {
            if (c[i]) {
                this.weight += this.criterias[i] * p[i];
            }
        }
    }

    @Override
    public int compareTo(Route o) {
        if (this.weight > o.weight) {
            return -1;
        }
        
        if (this.weight < o.weight) {
            return 1;
        }
        
        return 0;
    }
}
