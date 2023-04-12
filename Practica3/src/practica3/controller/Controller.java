package practica3.controller;

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
                    result = logarithmicSearch();
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
            combine[i] = dl.getPointPair(i);
            combine[i+3] = dr.getPointPair(i);
        }
        Arrays.sort(combine);
        d = new MinPairs(combine[3], combine[4], combine[5]);
        
        // Combine
        List<Integer> nearPoints = model.getNearPointsRef(mid, d.getPointPair(0).getDistance(), left, right);
        for (int i = 0; i < nearPoints.size(); i++) {
            int ind1 = nearPoints.get(i);
            //for (int j = 1; j <= 7 && (i+j) < nearPoints.size(); j++) {
            for (int j = i+1; j < nearPoints.size(); j++) {
                int ind2 = nearPoints.get(j);
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
