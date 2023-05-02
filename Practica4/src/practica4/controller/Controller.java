package practica4.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import practica4.Event;
import practica4.EventListener;
import practica4.Main;
import practica4.model.Model;

/**
 *
 * @author usuario
 */
public class Controller extends Thread implements EventListener {
    private Main main;
    private Model model;
    
    // Thread to do the execution of the algorithm, being able to interrupt it
    private Thread executionThread;
    
    Queue<Integer> visited;
    HashMap<Integer, Double> minimumDistance;
    
    public Controller(Main main) {
        this.main = main;
    }
    
    @Override
    public void run() {
        model = this.main.getModel();
        
        tagGraph(model.getOrigin());

        // Recursive Dijkstra algorithm
        calculate(model.getDest(), model.getOrigin());
        
        // Per passar per mid
        /*
        tagGraph(model.getMid());
        
        // Recursive Dijkstra algorithm
        calculate(model.getDest(), model.getMid());
        
        tagGraph(model.getOrigin());
        calculate(model.getMid(), model.getOrigin());*/
    }
    
    private void tagGraph(int origin) {
        visited = new LinkedList<>();
        minimumDistance = new HashMap<Integer, Double>();
        
        visited.add(origin);
        minimumDistance.put(origin, 0.0);
        
        while (!visited.isEmpty()) {
            // get & remove from queue
            int i = visited.remove();
            // get routes de i
            double acc = minimumDistance.get(i);
            int nroutes = model.getNRoutes(i);
            for (int j = 0; j < nroutes; j++) {
                int dest = model.getDestPoblation(i, j);
                double v = roundDouble(model.getRouteValue(i, j) + acc, 2);
  
                // las routes, si no están, las añadimos en la cola y en el hash
                if (minimumDistance.containsKey(dest)) {
                    double hashV = minimumDistance.get(dest);
                    if (hashV > v) {
                        minimumDistance.replace(dest, v);
                    }
                } else {
                    minimumDistance.put(dest, v);
                    visited.add(dest);
                }
            }
        }
    }
    
    private void calculate(int indexp, int dir) {
        // ArrayList permanentes
        // arrayList int
        //ArrayList<Integer> visited;
        
        // HashMap para guardar las etiquetas de las poblaciones (keys del hash mediante su índice)
        // hashmap (int index, double pesoRuta)
        
        // Array
        System.out.println(model.getPobName(indexp));
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
