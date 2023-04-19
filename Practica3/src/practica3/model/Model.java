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
    public final int xRANGE = 300;
    public final int yRANGE = 200;
    
    public Model(Main main, int nPoints) {
        this.main = main;
        this.nPoints = nPoints;
        
        initializeCloud();
    }
    
    private void initializeCloud() {
        points = new Point[this.nPoints];
        Random rnd = new Random();
        
        int xupperBound = this.xRANGE+1;
        int xlowerBound = -this.xRANGE;
        
        int yupperBound = this.yRANGE+1;
        int ylowerBound = -this.yRANGE;
        
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point(
                    // Generating x coord
                    rnd.nextDouble(xlowerBound, xupperBound), 
                    // Generating y coord
                    rnd.nextDouble(ylowerBound, yupperBound)
            );
        }
    }
    
    private void reset() {
        this.initializeCloud();
    }
    
    // Gets distance between Point indexed by origin and Point indexed by end
    public double getDistance(int origin, int end) {
        return this.points[origin].distanceTo(this.points[end]);
    }
    
    public int getNumberOfPoints() {
        return this.nPoints;
    }
    
    public double getPointX(int index) {
        return this.points[index].x;
    }
    
    public double getPointY(int index) {
        return this.points[index].y;
    }
    
    public Point[] getPointsRef () {
        return this.points;
    }
    
    public List<Integer> getNearPointsRef(int mid, double d, int left, int right) {
        List<Integer> l = new ArrayList<Integer>();
        
        double xleft = this.points[mid].x - d;
        double xright = this.points[mid].x + d;
        
        double yleft = this.points[mid].y - d;
        double yright = this.points[mid].y + d;

        for (int i = left; i <= right; i++) {
            if (this.points[i].x >= xleft && this.points[i].x <= xright
                && this.points[i].y >= yleft && this.points[i].y <= yright) {
                l.add(i);
            }
        }
        
        return l;
    }

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
