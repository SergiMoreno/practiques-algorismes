package practica4.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import practica4.Event;
import practica4.EventListener;
import practica4.Main;
import practica4.controller.fibonacci.FibonacciHeap;
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
    
    private AlgorithmTechnique selectedTechnique;
    
    public Controller(Main main) {
        this.main = main;
    }
    
    @Override
    public void run() {
        model = this.main.getModel();
        
        // Dijkstra algorithm
        
        this.solution = new ArrayList<Integer>();
        
        switch (this.selectedTechnique) {
            case QUEUE -> {
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
            }
            case BINARY_HEAP -> {
                /* from destination to middle */
                // Tag the graph by the mid node
                binaryTagging(model.getMiddle());
                // Calculate the minimum route from dest node to mid node
                calculate(model.getDest(), model.getMiddle());

                /* from middle to origin */
                // Tag the graph by the origin node
                binaryTagging(model.getOrigin());
                // Calculate the minimum route from mid node to origin node
                calculate(model.getMiddle(), model.getOrigin());
            }
            case FIBONACCI_HEAP -> {
                /* from destination to middle */
                // Tag the graph by the mid node
                fibonacciTagging(model.getMiddle());
                // Calculate the minimum route from dest node to mid node
                calculate(model.getDest(), model.getMiddle());

                /* from middle to origin */
                // Tag the graph by the origin node
                fibonacciTagging(model.getOrigin());
                // Calculate the minimum route from mid node to origin node
                calculate(model.getMiddle(), model.getOrigin());
            }
        }
        
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
            int nroutes = model.getNExitRoutes(i);
            for (int j = 0; j < nroutes; j++) {
                int dest = model.getDestPoblation(i, j);
                double v = roundDouble(model.getExitRouteValue(i, j) + acc, 2);
                //DecimalFormat df = new DecimalFormat("#.##");
                //double v = Double.parseDouble(df.format(model.getRouteValue(i, j) + acc));
                
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
    
    private void binaryTagging(int origin) {
        minimumDistance = new HashMap<Integer, Double>();
        PriorityQueue<QueueElement> minHeap = new PriorityQueue<>();
        
        minHeap.add(new QueueElement(origin, 0.0));
        minimumDistance.put(origin, 0.0);
        
        while (!minHeap.isEmpty()) {
            // Get & remove from queue
            QueueElement i = minHeap.poll();

            // Get routes de i
            double acc = minimumDistance.get(i.node);
            int nroutes = model.getNExitRoutes(i.node);
            for (int j = 0; j < nroutes; j++) {
                int dest = model.getDestPoblation(i.node, j);
                double v = roundDouble(model.getExitRouteValue(i.node, j) + acc, 2);
  
                // If poblation is already visited, check if the current distance is less than the stored value
                if (minimumDistance.containsKey(dest)) {
                    double hashV = minimumDistance.get(dest);
                    if (hashV > v) {
                        minimumDistance.replace(dest, v);
                        minHeap.add(new QueueElement(dest, v));
                    }
                } else {
                    minimumDistance.put(dest, v);
                    minHeap.add(new QueueElement(dest, v));
                }
            }
        }
    }
    
    private void fibonacciTagging(int origin) {
        minimumDistance = new HashMap<Integer, Double>();
        FibonacciHeap<QueueElement> minHeap = new FibonacciHeap<>();
        
        minHeap.add(new QueueElement(origin, 0.0));
        minimumDistance.put(origin, 0.0);
        
        while (!minHeap.isEmpty()) {
            // Get & remove from queue
            QueueElement i = minHeap.poll();

            // Get routes de i
            double acc = minimumDistance.get(i.node);
            int nroutes = model.getNExitRoutes(i.node);
            for (int j = 0; j < nroutes; j++) {
                int dest = model.getDestPoblation(i.node, j);
                double v = roundDouble(model.getExitRouteValue(i.node, j) + acc, 2);
  
                // If poblation is already visited, check if the current distance is less than the stored value
                if (minimumDistance.containsKey(dest)) {
                    double hashV = minimumDistance.get(dest);
                    if (hashV > v) {
                        minimumDistance.replace(dest, v);
                        minHeap.add(new QueueElement(dest, v));
                    }
                } else {
                    minimumDistance.put(dest, v);
                    minHeap.add(new QueueElement(dest, v));
                }
            }
        }
    }
    
    private void calculate(int indexp, int dir) {
        /*System.out.println(model.getPobName(indexp));
        this.solution.add(indexp);

        if (indexp == dir) {
            return;
        }
        
        double currentV = minimumDistance.get(indexp);
        int nroutes = model.getNEntryRoutes(indexp);
        for (int i = 0; i < nroutes; i++) {
            int dest = model.getOriginPoblation(indexp, i);
            double routeV = model.getEntryRouteValue(indexp, i);
            double pobV = minimumDistance.get(dest);
            
            double v = roundDouble(currentV - routeV, 2);
            if (Double.compare(v, pobV) == 0) {
                calculate(dest, dir);
                break;
            }
        }*/
        while (indexp != dir) {
            System.out.println(model.getPobName(indexp));
            this.solution.add(indexp);
            
            double currentV = minimumDistance.get(indexp);
            int nroutes = model.getNEntryRoutes(indexp);
            for (int i = 0; i < nroutes; i++) {
                int dest = model.getOriginPoblation(indexp, i);
                double routeV = model.getEntryRouteValue(indexp, i);
                double pobV = minimumDistance.get(dest);

                double v = roundDouble(currentV - routeV, 2);
                if (Double.compare(v, pobV) == 0) {
                    indexp = dest;
                    break;
                }
            }
        }
        System.out.println(model.getPobName(indexp));
        this.solution.add(indexp);
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
            case START -> {
                this.selectedTechnique = event.technique;
                // When start event notified, new Thread is initialized
                this.executionThread = new Thread(this);
                this.executionThread.start();
            }
            case STOP -> {
                if (this.executionThread != null) {
                    this.executionThread.interrupt();
                }
            }
        }
    }
    
}
