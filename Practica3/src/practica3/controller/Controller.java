package practica3.controller;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import practica3.Event;
import practica3.EventListener;
import practica3.Main;
import practica3.model.Model;

/**
 *
 * @author usuario
 */

public class Controller extends Thread implements EventListener {
    private Main main;

    // Represents the algorithm to be executed
    private int algorithm;
    // Thread to do the execution of the algorithm, being able to interrupt it
    private Thread executionThread;

    public Controller(Main main) {
        this.main = main;
        this.executionThread = new Thread(this);
    }
    
    @Override
    public void run () {
        Model model = this.main.getModel();
        
        if (this.algorithm == 0) exponentialSearch();
        if (this.algorithm == 1) {
            // Ordering the elements with mergesort
            Arrays.sort(model.getPointsRef());
            // Executing D&C approach
            logarithmicSearch(
                    0,
                    0,
                    model.RANGE, 
                   model.RANGE
            );
        }
    }
    
    // n^2 algorithm
    public void exponentialSearch () {
        try {
            for (int i = 0; i < 100; i++) {
                System.out.println("Iteration : " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException ex) {
            System.out.println(
                  "Thread was interrupted, Failed to complete operation");
        }
    }
    
    // nlogn algorithm, D&C solution
    public void logarithmicSearch (int startx, int starty, int finalx, int finaly) {
        
    }
    
    @Override
    public void notify(Event e) {
        ControllerEvent event = (ControllerEvent) e;
        
        switch (event.type) {
            case START:
                this.algorithm = event.algorithm;
                if (!this.executionThread.isAlive())
                    this.executionThread.start();
                break;
            case STOP:
                this.executionThread.interrupt();
                break;
        }
    }
}
