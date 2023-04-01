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
    
    // Array to represent the cloud of points
    private Point [] points;
    // Number of points
    private int nPoints;
    
    // Constant to keep the coordinates range
    private final int RANGE = 100;
    
    public Model(Main main, int nPoints) {
        this.main = main;
        this.nPoints = nPoints;
        
        initializeCloud();
    }
    
    private void initializeCloud() {
        points = new Point[this.nPoints];
        Random rnd = new Random();
        
        for (int i = 0;i < points.length; i++) {
            float x = rnd.nextFloat(-this.RANGE, this.RANGE+1);
            float y = rnd.nextFloat(-this.RANGE, this.RANGE+1);

            int xc = Math.round(x);
            int yc = Math.round(y);
            points[i] = new Point(xc, yc);
        }
    }
    
    // Gets distance between Point indexed by origin and Point indexed by end
    public int getDistance(int origin, int end) {
        return this.points[origin].compareTo(this.points[end]);
    }
    
    public int getNumberOfPoints() {
        return this.nPoints;
    }
    
    public int getPointX(int index) {
        return this.points[index].getX();
    }
    
    public int getPointY(int index) {
        return this.points[index].getY();
    }

    @Override
    public void notify(Event e) {
        ModelEvent event = (ModelEvent) e;
        
        switch (event.type) {
            case CHANGE_N_POINTS -> {
                this.nPoints = event.nPoints;
                this.initializeCloud();
            }
        }
    }
}
