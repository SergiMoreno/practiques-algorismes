package practica4.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import practica4.Event;
import practica4.EventListener;
import practica4.Main;
import practica4.model.Model;
import practica4.view.ViewEvent;

/**
 *
 * @author usuario
 */
public class Controller extends Thread implements EventListener {
    private Main main;
    private Model model;
    
    // Thread to do the execution of the algorithm, being able to interrupt it
    private Thread executionThread;
    // Structure to keep the poblations indexs and their minimum distance
    private HashMap<Integer, Double> minimumDistance;
    // Structure to keep the solution indexs
    private ArrayList<Integer> solution;
    
    public Controller(Main main) {
        this.main = main;
    }
    
    @Override
    public void run() {
        model = this.main.getModel();
        
        // Recursive Dijkstra algorithm
        
        this.solution = new ArrayList<Integer>();
        
        /* from destination to middle */
        // Tag the graph by the mid node
        tagGraph(model.getMiddle());
        // Calculate the minimum route from dest node to mid node
        calculate(model.getDest(), model.getMiddle());
                
        /* from middle to origin */
        // Tag the graph by the origin node
        tagGraph(model.getOrigin());
        // Calculate the minimum route from mid node to origin node
        calculate(model.getMiddle(), model.getOrigin());
        
        // Reverse the route flow to be from origin to destination and notify result to view
        Collections.reverse(solution);
        this.main.notify(new ViewEvent(this.solution));
    }
    
    // Method to tag the graph nodes with the minimum distance by the index of the one passed as parameter
    private void tagGraph(int origin) {
        // Visited poblations will be putted in this structure
        minimumDistance = new HashMap<Integer, Double>();
        // Queue to keep the poblations to be visited
        Queue<Integer> visit = new LinkedList<>();
        
        visit.add(origin);
        minimumDistance.put(origin, 0.0);
        
        while (!visit.isEmpty()) {
            // Get & remove from queue
            int i = visit.remove();

            // Get routes de i
            double acc = minimumDistance.get(i);
            int nroutes = model.getNRoutes(i);
            for (int j = 0; j < nroutes; j++) {
                int dest = model.getDestPoblation(i, j);
                double v = roundDouble(model.getRouteValue(i, j) + acc, 2);
  
                // If poblation is already visited, check if the current distance is less than the stored value
                if (minimumDistance.containsKey(dest)) {
                    double hashV = minimumDistance.get(dest);
                    if (hashV > v) {
                        minimumDistance.replace(dest, v);
                        visit.add(dest);
                    }
                } else {
                    minimumDistance.put(dest, v);
                    visit.add(dest);
                }
            }
        }
    }
    
    /*private void binaryTagging(int origin, int destination) {
        minimumDistance = new HashMap<Integer, Double>();
        PriorityQueue<Min> minHeap = new PriorityQueue<>();
        
        minHeap.add(new Min(origin, 0.0));
        minimumDistance.put(origin, 0.0);
        
        while (!minHeap.isEmpty()) {
            // Get & remove from queue
            Min i = minHeap.poll();
            
            if (minimumDistance.containsKey(i.i)) {
                continue;
            }
            
            minimumDistance.put(i.i, i.v);

            // Get routes de i
            double acc = i.v;
            int nroutes = model.getNRoutes(i.i);
            for (int j = 0; j < nroutes; j++) {
                int dest = model.getDestPoblation(i.i, j);
                double v = roundDouble(model.getRouteDistance(i.i, j) + acc, 2);
  
                // If poblation is already visited, check if the current distance is less than the stored value
                if (minimumDistance.containsKey(dest)) {
                    double hashV = minimumDistance.get(dest);
                    if (hashV > v) {
                        minimumDistance.replace(dest, v);
                        
                        Min m = new Min(dest, v);
                        if (minHeap.contains(m)) {
                            minHeap.remove(m);
                            minHeap.add(m);
                        } else {
                            minHeap.add(m);
                        }
                    }
                } else {
                    minimumDistance.put(dest, v);
                    minHeap.add(new Min(dest, v));
                }
            }
        }
    }
    
    private void fibonacciTagging(int origin, int destination) {
        minimumDistance = new HashMap<Integer, Double>();
        PriorityQueue<Min> minHeap = new PriorityQueue<>();
        
        minHeap.add(new Min(origin, 0.0));
        minimumDistance.put(origin, 0.0);
        
        while (!minHeap.isEmpty()) {
            // Get & remove from queue
            Min i = minHeap.poll();
            
            if (minimumDistance.containsKey(i.i)) {
                continue;
            }
            
            minimumDistance.put(i.i, i.v);

            // Get routes de i
            double acc = i.v;
            int nroutes = model.getNRoutes(i.i);
            for (int j = 0; j < nroutes; j++) {
                int dest = model.getDestPoblation(i.i, j);
                double v = roundDouble(model.getRouteDistance(i.i, j) + acc, 2);
  
                // If poblation is already visited, check if the current distance is less than the stored value
                if (minimumDistance.containsKey(dest)) {
                    double hashV = minimumDistance.get(dest);
                    if (hashV > v) {
                        minimumDistance.replace(dest, v);
                        
                        Min m = new Min(dest, v);
                        if (minHeap.contains(m)) {
                            minHeap.remove(m);
                            minHeap.add(m);
                        } else {
                            minHeap.add(m);
                        }
                    }
                } else {
                    minimumDistance.put(dest, v);
                    minHeap.add(new Min(dest, v));
                }
            }
        }
    }*/
    
    private void calculate(int indexp, int dir) {
        System.out.println(model.getPobName(indexp));
        this.solution.add(indexp);

        if (indexp == dir) {
            return;
        }
        
        double currentV = minimumDistance.get(indexp);
        int nroutes = model.getNRoutes(indexp);
        for (int i = 0; i < nroutes; i++) {
            int dest = model.getDestPoblation(indexp, i);
            double routeV = model.getRouteValue(indexp, i);
            double pobV = minimumDistance.get(dest);
            
            double v = roundDouble(currentV - routeV, 2);
            if (Double.compare(v, pobV) == 0) {
                calculate(dest, dir);
                break;
            }
        }
    }
    
    // Auxiliar method to round doubles by the number of decimals defined by 'places'
    private Double roundDouble(double vb, int places) {
        long factor = (long) Math.pow(10, places);
        vb = vb * factor;
        long tmp = Math.round(vb);
        return (double) tmp / factor;
    }

    @Override
    public void notify(Event e) {
        ControllerEvent event = (ControllerEvent) e;
        
        switch (event.type) {
            case START:
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
