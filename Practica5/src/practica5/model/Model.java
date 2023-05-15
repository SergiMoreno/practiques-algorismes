package practica5.model;

import practica5.Event;
import practica5.EventListener;
import practica5.Main;

/**
 *
 * @author usuario
 */
public class Model implements EventListener {
    private Main main;
    private Language [] dictionaries;
    private double [][] distanceMatrix;
    
    public Model(Main main) {
        this.main = main;
            
        /* Iterate though the number of languages, initializing one by one */
        Language s = new Language("esp.dic");
        Language it = new Language("it.dic");

        /* Distance matrix */
        this.distanceMatrix = new double[10][10];
        for (int i = 0; i < this.distanceMatrix.length;i++) {
            this.distanceMatrix[i][i] = 0.0;
        }
    }

    @Override
    public void notify(Event e) {
    }
}
