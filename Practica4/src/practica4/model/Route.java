package practica4.model;

/**
 *
 * @author usuario
 */
public class Route {
    private double value;
    
    public Route(double v) {
        this.value = v;
    }
    
    /*public Route(Nodo f) {
        apunta = f;
        valor = 0.0;
    }*/
    
    /*public Nodo apunta() {
        return apunta;
    }*/
    
    public double getValue() {
        return this.value;
    }
}
