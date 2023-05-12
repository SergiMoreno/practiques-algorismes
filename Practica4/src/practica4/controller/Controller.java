package practica4.controller;

import java.util.ArrayList;
import java.util.Collections;
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
    // Structure to keep minimum distance to the poblation represented by the position
    private double [] minimumDistance;
    // Structure to keep the solution indexs
    private ArrayList<Integer> solution;
    // It allows to apply one structure technic or another to the algorithm
    private StructureTechnique selectedTechnique;
    
    public Controller(Main main) {
        this.main = main;
    }
    
    @Override
    public void run() {
        model = this.main.getModel();
        
        // Dijkstra algorithm
        
        this.solution = new ArrayList<Integer>();
        
        long time;
        switch (this.selectedTechnique) {
            case QUEUE -> {
                /* from destination to middle */
                // Tag the graph by the mid node
                time = System.nanoTime();
                tagGraph(model.getMiddle());
                time = System.nanoTime() - time;
                System.out.println("Queue 1 : " + time);
                long oldTime = time;
                // Calculate the minimum route from dest node to mid node
                calculate(model.getDest(), model.getMiddle());

                /* from middle to origin */
                // Tag the graph by the origin node
                time = System.nanoTime();
                tagGraph(model.getOrigin());
                time = System.nanoTime() - time;
                System.out.println("Queue 2 : " + time);
                // Calculate the minimum route from mid node to origin node
                calculate(model.getMiddle(), model.getOrigin());
                System.out.println("Queue TOTAL : " + (time + oldTime));
            }
            case BINARY_HEAP -> {
                /* from destination to middle */
                // Tag the graph by the mid node
                time = System.nanoTime();
                binaryTagging(model.getMiddle());
                time = System.nanoTime() - time;
                System.out.println("Binary 1 : " + time);
                long oldTime = time;
                // Calculate the minimum route from dest node to mid node
                calculate(model.getDest(), model.getMiddle());

                /* from middle to origin */
                // Tag the graph by the origin node
                time = System.nanoTime();
                binaryTagging(model.getOrigin());
                time = System.nanoTime() - time;
                System.out.println("Binary 2 : " + time);
                // Calculate the minimum route from mid node to origin node
                calculate(model.getMiddle(), model.getOrigin());
                System.out.println("Binary TOTAL : " + (time + oldTime));
            }
            case FIBONACCI_HEAP -> {
                /* from destination to middle */
                // Tag the graph by the mid node
                time = System.nanoTime();
                fibonacciTagging(model.getMiddle());
                time = System.nanoTime() - time;
                System.out.println("Fibonacci 1 : " + time);
                long oldTime = time;
                // Calculate the minimum route from dest node to mid node
                calculate(model.getDest(), model.getMiddle());

                /* from middle to origin */
                // Tag the graph by the origin node
                time = System.nanoTime();
                fibonacciTagging(model.getOrigin());
                time = System.nanoTime() - time;
                System.out.println("Fibonacci 2 : " + time);
                // Calculate the minimum route from mid node to origin node
                calculate(model.getMiddle(), model.getOrigin());
                System.out.println("Fibonacci TOTAL : " + (time + oldTime));
            }
        }
        
        // Reverse the route flow to be from origin to destination and notify result to view
        Collections.reverse(solution);
        this.main.notify(new ViewEvent(this.solution));
    }
    
    // Method to tag the graph nodes with the minimum distance by the index of the one passed as parameter
    private void tagGraph(int origin) {
        // Visited poblations will be putted in this structure
        initializeMinDistance();
        // Queue to keep the poblations to be visited
        Queue<Integer> visit = new LinkedList<>();
        
        visit.add(origin);
        minimumDistance[origin] = 0.0;
        
        while (!visit.isEmpty()) {
            // Get & remove from queue
            int i = visit.remove();

            // Get routes de i
            double acc = minimumDistance[i];
            int nroutes = model.getNExitRoutes(i);
            for (int j = 0; j < nroutes; j++) {
                int dest = model.getDestPoblation(i, j);
                double v = roundDouble(model.getExitRouteValue(i, j) + acc, 2);
                
                // If poblation is already visited, check if the current distance is less than the stored value
                if (minimumDistance[dest] > v) {
                    minimumDistance[dest] = v;
                    visit.add(dest);
                }
            }
        }
    }
    
    private void binaryTagging(int origin) {
        initializeMinDistance();
        PriorityQueue<QueueElement> minHeap = new PriorityQueue<>();
        
        minHeap.add(new QueueElement(origin, 0.0));
        minimumDistance[origin] = 0.0;
        
        while (!minHeap.isEmpty()) {
            // Get & remove from queue
            QueueElement i = minHeap.poll();

            // Get routes de i
            double acc = minimumDistance[i.node];
            int nroutes = model.getNExitRoutes(i.node);
            for (int j = 0; j < nroutes; j++) {
                int dest = model.getDestPoblation(i.node, j);
                double v = roundDouble(model.getExitRouteValue(i.node, j) + acc, 2);
  
                // If poblation is already visited, check if the current distance is less than the stored value
                if (minimumDistance[dest] > v) {
                    minimumDistance[dest] = v;
                    minHeap.add(new QueueElement(dest, v));
                }
            }
        }
    }
    
    private void fibonacciTagging(int origin) {
        initializeMinDistance();
        FibonacciHeap<QueueElement> minHeap = new FibonacciHeap<>();
        
        minHeap.add(new QueueElement(origin, 0.0));
        minimumDistance[origin] = 0.0;
        
        while (!minHeap.isEmpty()) {
            // Get & remove from queue
            QueueElement i = minHeap.poll();

            // Get routes de i
            double acc = minimumDistance[i.node];
            int nroutes = model.getNExitRoutes(i.node);
            for (int j = 0; j < nroutes; j++) {
                int dest = model.getDestPoblation(i.node, j);
                double v = roundDouble(model.getExitRouteValue(i.node, j) + acc, 2);
  
                // If poblation is already visited, check if the current distance is less than the stored value
                if (minimumDistance[dest] > v) {
                    minimumDistance[dest] = v;
                    minHeap.add(new QueueElement(dest, v));
                }
            }
        }
    }
    
    private void calculate(int indexp, int dir) {
        while (indexp != dir) {
            System.out.println(model.getPobName(indexp));
            this.solution.add(indexp);
            
            double currentV = minimumDistance[indexp];
            int nroutes = model.getNEntryRoutes(indexp);
            for (int i = 0; i < nroutes; i++) {
                int dest = model.getOriginPoblation(indexp, i);
                double routeV = model.getEntryRouteValue(indexp, i);
                double pobV = minimumDistance[dest];

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
    
    // Initialize node structure to their maximum values, it will be usefull to
    // keep track of which ones are visited
    private void initializeMinDistance() {
        this.minimumDistance = new double[model.getNPoblations()];
        for (int i = 0; i < this.minimumDistance.length; i++) {
            this.minimumDistance[i] = Double.MAX_VALUE;
        }
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
