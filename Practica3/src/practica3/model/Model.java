package practica3.model;

import java.util.ArrayList;
import java.util.List;
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
    public final int RANGE = 200;
    
    public Model(Main main, int nPoints) {
        this.main = main;
        this.nPoints = nPoints;
        
        initializeCloud();
    }
    
    private void initializeCloud() {
        points = new Point[this.nPoints];
        Random rnd = new Random();
        
        int upperBound = this.RANGE+1;
        int lowerBound = -this.RANGE;
        
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point(
                    // Generating x coord
                    Math.round(rnd.nextFloat(lowerBound, upperBound)), 
                    // Generating y coord
                    Math.round(rnd.nextFloat(lowerBound, upperBound))
            );
        }
    }
    
    private void reset() {
        this.initializeCloud();
    }
    
    // Gets distance between Point indexed by origin and Point indexed by end
    public int getDistance(int origin, int end) {
        return this.points[origin].distanceTo(this.points[end]);
    }
    
    public int getNumberOfPoints() {
        return this.nPoints;
    }
    
    public int getPointX(int index) {
        return this.points[index].x;
    }
    
    public int getPointY(int index) {
        return this.points[index].y;
    }
    
    public Point[] getPointsRef () {
        return this.points;
    }
    
    /*public int[] getNearPointsRef(int index, int d) {
        List<Integer> l = new ArrayList<Integer>();
        return l.
    }*/

    @Override
    public void notify(Event e) {
        ModelEvent event = (ModelEvent) e;
        
        switch (event.type) {
            case CHANGE_N_POINTS -> {
                this.nPoints = event.nPoints;
                this.initializeCloud();
            }
            case RESET -> {
                this.reset();
            }
        }
    }
}
