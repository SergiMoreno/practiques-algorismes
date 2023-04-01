package practica3.model;

import java.util.Random;
import practica3.Event;
import practica3.EventListener;
import practica3.Main;

/**
 *
 * @author usuario
 */

public class Model implements EventListener {
    private Main main;

    private Point [] points;
    private int n;
    
    public Model(Main main, int n) {
        this.main = main;
        this.n = n;
        
        initializeCloud();
    }
    
    private void initializeCloud() {
        points = new Point[this.n];
        Random rnd = new Random();
        
        for (int i = 0;i < points.length; i++) {
            float x = rnd.nextFloat(-10, 11);
            float y = rnd.nextFloat(-10, 11);

            int xc = Math.round(x);
            int yc = Math.round(y);
            points[i] = new Point(xc, yc);
        }
    }
    
    // Gets distance between Point indexed by origin and Point indexed by end
    public int getDistance(int origin, int end) {
        return this.points[origin].compareTo(this.points[end]);
    }

    @Override
    public void notify(Event e) {
        ModelEvent event = (ModelEvent) e;
        
        switch (event.type) {
            case CHANGE_N -> {
                this.n = event.n;
                this.initializeCloud();
            }
        }
    }
}
