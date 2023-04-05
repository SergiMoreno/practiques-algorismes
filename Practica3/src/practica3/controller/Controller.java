package practica3.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import practica3.AlgorithmType;
import practica3.Event;
import practica3.EventListener;
import practica3.Main;
import practica3.model.Model;
import practica3.model.Point;
import practica3.view.ViewEvent;

/**
 *
 * @author usuario
 */
// Class to keep the index references to the points and the distance between them
class PointsDistance implements Comparable<PointsDistance> {

    final public int refPoint1, refPoint2;
    final public int dist;

    public PointsDistance(int r1, int r2, int dist) {
        this.refPoint1 = r1;
        this.refPoint2 = r2;
        this.dist = dist;
    }

    @Override
    public String toString() {
        return "PointsDistance{" + "refPoint1=" + refPoint1 + ", refPoint2=" + refPoint2 + ", dist=" + dist + '}';
    }

    @Override
    public int compareTo(PointsDistance o) {
        if (this.dist > o.dist) {
            return -1;
        }
        if (this.dist < o.dist) {
            return 1;
        }

        return 0;
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

        switch (this.algorithm) {
            case BRUTE -> {
                exponentialSearch();
            }
            case DIVIDE_AND_CONQUER -> {
                // Executing D&C approach
                logarithmicSearch();
            }
        }
    }

    // n^2 algorithm
    public void exponentialSearch() {
        Model model = this.main.getModel();
        
        try {
            int d;
            PriorityQueue<PointsDistance> pq = new PriorityQueue<PointsDistance>(3);
            for (int i = 0; i < model.getNumberOfPoints(); i++) {
                for (int j = i+1; j < model.getNumberOfPoints(); j++) {
                    d = model.getDistance(i, j);
                    PointsDistance e = new PointsDistance(i, j, d);
                    //System.out.println(e);
                    if (pq.size() < 3) {
                        pq.add(e);
                    }else{
                        if (pq.peek().dist > e.dist){
                            pq.poll();
                            pq.add(e);
                        }
                    }
                    Thread.sleep(1);
                }
            }
            System.out.println(pq);
            ArrayList<Integer> solution = new ArrayList<Integer>();
            while(!pq.isEmpty()){
                PointsDistance p = pq.poll();
                solution.add(p.refPoint1);
                solution.add(p.refPoint2);
            }
            this.main.notify(new ViewEvent(solution));
        } catch (InterruptedException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // nlogn algorithm, D&C solution
    public void logarithmicSearch () {
        //Model model = this.main.getModel();
        
        // Ordering the elements with mergesort
        Arrays.sort(model.getPointsRef());
        
        PointsDistance p = closestPairs(0, model.getNumberOfPoints()-1, 3);
        
        System.out.println("Closest Pair -> (" + p.refPoint1 + ", " + p.refPoint2 + "), dist = " + p.dist);
    }
    
    private PointsDistance closestPairs(int left, int right, int k) {
        // Base case 1, 2 points
        if (left == right-1) {
            return new PointsDistance(left, right, model.getDistance(left, right));
        }
        // Base case 2, 3 points
        if (left == right-2) {
            int dist13 = model.getDistance(left, right);
            int dist12 = model.getDistance(left, right-1);
            int dist23 = model.getDistance(left+1, right);
            
            // Return min distance points
            if (dist13 < dist12 && dist13 < dist23) {
                return new PointsDistance(left, right, dist13);
            }
            
            if (dist12 < dist13 && dist12 < dist23) {
                return new PointsDistance(left, right-1, dist12);
            }
            
            if (dist23 < dist13 && dist23 < dist12) {
                return new PointsDistance(left+1, right, dist23);
            }
        }
        
        // Divide
        int mid = (left + right) / 2;
        PointsDistance dl = closestPairs(left, mid, 3);
        PointsDistance dr = closestPairs(mid+1, right, 3);
        PointsDistance d;
        if (dl.dist < dr.dist) {
            d = dl;
        } else {
            d = dr;
        }
        
        // Combine
        List<Integer> nearPoints = model.getNearPointsRef(mid, d.dist);
        // for i = 1 to S.length
        for (int i = 0; i < nearPoints.size(); i++) {
            int ind1 = nearPoints.get(i);
            for (int j = 1; j <= 7 && (i+j) < nearPoints.size(); j++) {
            //for (int j = i+1; (i+j) < nearPoints.size(); j++) {
                int ind2 = nearPoints.get(i+j);
                int val = model.getDistance(ind1, ind2);
                if (val < d.dist) {
                    d = new PointsDistance(ind1, ind2, val);
                }
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
