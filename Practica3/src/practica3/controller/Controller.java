package practica3.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import practica3.AlgorithmType;
import practica3.Event;
import practica3.EventListener;
import practica3.Main;
import practica3.model.Model;
import practica3.view.ViewEvent;

/**
 *
 * @author usuario
 */
// Class to keep the index references to the points and the distance between them
class PointsPair implements Comparable<PointsPair> {

    final public int refPoint1, refPoint2;
    final public double dist;

    public PointsPair(int r1, int r2, double dist) {
        this.refPoint1 = r1;
        this.refPoint2 = r2;
        this.dist = dist;
    }
    
    static public PointsPair maxDistance() {
        return new PointsPair(-1, -1, Double.MAX_VALUE);
    }

    @Override
    public String toString() {
        return "PointsPair{" + "refPoint1=" + refPoint1 + ", refPoint2=" + refPoint2 + ", dist=" + dist + "}";
    }
    
    @Override
    public int compareTo(PointsPair o) {
        if (this.dist > o.dist) {
            return -1;
        }
        if (this.dist < o.dist) {
            return 1;
        }

        return 0;
    }
}

class MinPairs {
    PointsPair [] list;
    int n;
    
    public MinPairs(int k) {
        list = new PointsPair[k];
        n = 0;
    }
    
    public MinPairs(PointsPair a, PointsPair b, PointsPair c) {
        list = new PointsPair[3];
        n = 3;
        list[0] = a;
        list[1] = b;
        list[2] = c;
        Arrays.sort(list);
    }
    
    public void checkPoint(PointsPair p) {
        if (n < list.length) {
            list[n] = p;
            n++;
            if (n == list.length) Arrays.sort(list);
        } else if (list[0].dist > p.dist) {
            list[0] = p;
            Arrays.sort(list);
        }
    }
    
    public ArrayList<Integer> getIndexs() {
        ArrayList<Integer> solution = new ArrayList<Integer>();
        for(int i = 0; i < n; i++) {
            PointsPair p = list[i];
            solution.add(p.refPoint1);
            solution.add(p.refPoint2);
        }
        
        return solution;
    }
    
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < n; i++) {
            result += "\nPair " + i + " : " + list[i].toString();
        }
        return result;
    }
}

public class Controller extends Thread implements EventListener {
    private Main main;

    // Represents the algorithm to be executed
    private AlgorithmType algorithm;
    // Thread to do the execution of the algorithm, being able to interrupt it
    private Thread executionThread;

    // DEMANAR A NES PROFE
    Model model;

    public Controller(Main main) {
        this.main = main;
    }

    @Override
    public void run() {
        model = this.main.getModel();

        try {
            MinPairs result = null;
            switch (this.algorithm) {
                case BRUTE -> {
                    result = exponentialSearch();
                }
                case DIVIDE_AND_CONQUER -> {
                    // Executing D&C approach
                    logarithmicSearch();
                }
            }
            
            if (result != null) {
                System.out.println(result);
                this.main.notify(new ViewEvent(result.getIndexs()));
            }
        } catch (InterruptedException ex) {
            System.out.println(this.algorithm.toString() + ": EXECUTION INTERRUPTED");
        }
    }

    // n^2 algorithm
    public MinPairs exponentialSearch() throws InterruptedException {        
        MinPairs list = new MinPairs(3);
        for (int i = 0; i < model.getNumberOfPoints(); i++) {
            for (int j = i+1; j < model.getNumberOfPoints(); j++) {
                PointsPair p = new PointsPair(i, j, model.getDistance(i, j));
                //System.out.println(p);
                list.checkPoint(p);
                Thread.sleep(1);
            }
        }
        return list;
    }

    // nlogn algorithm, D&C solution
    public MinPairs logarithmicSearch () throws InterruptedException {            
        // Ordering the elements with mergesort
        Arrays.sort(model.getPointsRef());
            
        return closestPairs(0, model.getNumberOfPoints()-1, 3);
    }
    
    private MinPairs closestPairs(int left, int right, int k) throws InterruptedException {
        // Base case 1, 1 point
        if (left == right) {
            return new MinPairs(PointsPair.maxDistance(),
            PointsPair.maxDistance(),
            PointsPair.maxDistance());
        }
        // Base case 2, 2 points
        if (left == right-1) {
            return new MinPairs(new PointsPair(left, right, model.getDistance(left, right)),
            PointsPair.maxDistance(),
            PointsPair.maxDistance());
        }
        // Base case 3, 3 points
        if (left == right-2) {
            double dist13 = model.getDistance(left, right);
            double dist12 = model.getDistance(left, right-1);
            double dist23 = model.getDistance(left+1, right);

            return new MinPairs(new PointsPair(left, right, dist13),
            new PointsPair(left, right-1, dist12),
            new PointsPair(left+1, right, dist23));
        }
        
        // Divide
        int mid = (left + right) / 2;
        MinPairs dl = closestPairs(left, mid, 3);
        MinPairs dr = closestPairs(mid+1, right, 3);
        MinPairs d;
        PointsPair [] combine = new PointsPair[6];
        for (int i = 0; i < 3; i++) {
            combine[i] = dl.list[i];
            combine[i+3] = dr.list[i];
        }
        Arrays.sort(combine);
        d = new MinPairs(combine[3], combine[4], combine[5]);
        
        // Combine
        List<Integer> nearPoints = model.getNearPointsRef(mid, d.list[0].dist);
        // for i = 1 to S.length
        for (int i = 0; i < nearPoints.size(); i++) {
            int ind1 = nearPoints.get(i);
            //for (int j = 1; j <= 7 && (i+j) < nearPoints.size(); j++) {
            for (int j = 1; (i+j) < nearPoints.size(); j++) {
                int ind2 = nearPoints.get(i+j);
                Thread.sleep(1);
                double val = model.getDistance(ind1, ind2);
                d.checkPoint(new PointsPair(ind1, ind2, val));
            }
        }
        return d;
    }

    @Override
    public void notify(Event e) {
        ControllerEvent event = (ControllerEvent) e;

        switch (event.type) {
            case START:
                this.algorithm = event.algorithm;
                // When start event notified, new Thread is initialized
                this.executionThread = new Thread(this);
                this.executionThread.start();
                break;
            case STOP:
                if (this.executionThread != null) {
                    this.executionThread.interrupt();
                }
                break;
        }
    }
}
