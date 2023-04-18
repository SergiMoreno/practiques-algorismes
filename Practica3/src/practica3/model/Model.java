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
    
    ArrayList<Integer> meanList;
    
    public Model(Main main, int nPoints) {
        this.main = main;
        this.nPoints = nPoints;
        
        initializeCloud();
    }
    
    private void initializeCloud() {
        points = new Point[this.nPoints];
        Random rnd = new Random();
        this.meanList = new ArrayList<Integer>();
        
        int upperBound = this.RANGE+1;
        int lowerBound = -this.RANGE;
        
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point(
                    // Generating x coord
                    rnd.nextDouble(lowerBound, upperBound), 
                    // Generating y coord
                    rnd.nextDouble(lowerBound, upperBound)
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
        ArrayList<Integer> mean = new ArrayList<Integer>();
        
        double xleft = this.points[mid].x - d;
        double xright = this.points[mid].x + d;

        for (int i = left; i <= right; i++) {
            if (this.points[i].x >= xleft && this.points[i].x <= xright) {
                l.add(i);
                //mean.add(i - left);
            }
        }
        
        /*int result = 0;
        for (int i = 0; i < mean.size(); i++) {
            result += mean.get(i);
        }
        result /= mean.size();
        System.out.println("Mean points : " + result);*/
        
        this.meanList.add(l.size());
        return l;
    }
    
    public void printMeans() {
        int result = 0;
        for (int i = 0; i < this.meanList.size(); i++) {
            result += this.meanList.get(i);
        }
        result /= this.meanList.size();
        System.out.println("Mean points TOTAL : " + result);
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
