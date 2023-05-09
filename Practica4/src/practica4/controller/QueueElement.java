package practica4.controller;

/**
 *
 * @author usuario
 */
public class QueueElement implements Comparable<QueueElement> {
    int node;
    double weight;
    
    public QueueElement(int node, double weight) {
        this.node = node;
        this.weight = weight;
    }

    @Override
    public int compareTo(QueueElement o) {
        if (this.weight > o.weight) {
            return 1;
        }
        
        if (this.weight < o.weight) {
            return -1;
        }
        
        return 0;
    }
}
