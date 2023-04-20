package practica3.controller;

import java.time.Duration;
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
public class Controller extends Thread implements EventListener {
    private Main main;
    private Model model;

    // Represents the algorithm to be executed
    private AlgorithmType algorithm;
    // Number of pairs of points to be calculated
    private int nPairs;
    // Thread to do the execution of the algorithm, being able to interrupt it
    private Thread executionThread;

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
                    result = logarithmicSearch();
                }
            }
            
            if (result != null) {
                System.out.println(result);
                this.main.notify(new ViewEvent(result.getIndexs()));
            }
        } catch (InterruptedException ex) {
            System.out.println(this.algorithm.toString() + " ALGORITHM: EXECUTION INTERRUPTED");
        }
    }

    // n^2 algorithm
    public MinPairs exponentialSearch() throws InterruptedException {        
        MinPairs list = new MinPairs(nPairs);
        for (int i = 0; i < model.getNumberOfPoints(); i++) {
            for (int j = i+1; j < model.getNumberOfPoints(); j++) {
                PointsPair p = new PointsPair(i, j, model.getDistance(i, j));
                list.checkPoint(p);
                Thread.sleep(Duration.ZERO);
            }
        }
        return list;
    }

    // nlogn algorithm, D&C solution
    public MinPairs logarithmicSearch () throws InterruptedException {            
        // Ordering the elements with mergesort
        Arrays.sort(model.getPointsRef());
            
        return closestPairs(0, model.getNumberOfPoints()-1, this.nPairs);
    }
    
    private MinPairs closestPairs(int left, int right, int k) throws InterruptedException {
        Thread.sleep(Duration.ZERO);
        MinPairs p = new MinPairs(k);
        // Base case 1, 1 point
        if (left == right) {
            p.fill();
            return p;
        }
        // Base case 2, 2 points
        if (left == right-1) {
            p.checkPoint(new PointsPair(left, right, model.getDistance(left, right)));
            p.fill();
            return p;
        }
        // Base case 3, 3 points
        if (left == right-2) {
            double dist13 = model.getDistance(left, right);
            double dist12 = model.getDistance(left, right-1);
            double dist23 = model.getDistance(left+1, right);
            
            p.checkPoint(new PointsPair(left, right, dist13));
            p.checkPoint(new PointsPair(left, right-1, dist12));
            p.checkPoint(new PointsPair(left+1, right, dist23));
            p.fill();
            return p;
        }
        
        // Divide
        int mid = (left + right) / 2;
        MinPairs dl = closestPairs(left, mid, k);
        MinPairs dr = closestPairs(mid+1, right, k);
        MinPairs d = new MinPairs(k);
        PointsPair [] combine = new PointsPair[k*2];
        for (int i = 0; i < k; i++) {
            combine[i] = dl.getPointPair(i);
            combine[i+k] = dr.getPointPair(i);
        }
        Arrays.sort(combine);
        for (int i = k; i < combine.length; i++) {
            d.checkPoint(combine[i]);
        }
        
        // Combine
        List<Integer> nearPoints = model.getNearPointsRef(mid, d.getPointPair(0).getDistance(), left, right);
        for (int i = 0; i < nearPoints.size(); i++) {
            int ind1 = nearPoints.get(i);
            for (int j = i+1; j < nearPoints.size(); j++) {
                int ind2 = nearPoints.get(j);
                double val = model.getDistance(ind1, ind2);
                d.checkPoint(new PointsPair(ind1, ind2, val));
            }
        }
        d.fill();
        return d;
    }

    @Override
    public void notify(Event e) {
        ControllerEvent event = (ControllerEvent) e;

        switch (event.type) {
            case START:
                this.algorithm = event.algorithm;
                this.nPairs = event.nPairs;
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
