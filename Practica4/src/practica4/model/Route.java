package practica4.model;

/**
 *
 * @author usuario
 */
public class Route {
    private Poblation poblationf;
    private double value;
    
    public Route(Poblation p, double v) {
        this.poblationf = p;
        this.value = v;
    }
    
    /*public Route(Nodo f) {
        apunta = f;
        valor = 0.0;
    }*/
    
    public Poblation goesTo() {
        return poblationf;
    }
    
    public double getValue() {
        return this.value;
    }
}
