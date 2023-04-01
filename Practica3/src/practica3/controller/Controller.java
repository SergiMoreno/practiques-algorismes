package practica3.controller;

import java.util.Arrays;
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
    private Model model;
    private int algorithm;
    private Controller executionThread;
    
    public Controller(Main main) {
        this.main = main;
        this.model = this.main.getModel();
        this.executionThread = new Controller(this.main);
    }
    
    @Override
    public void run () {
        if (this.algorithm == 0) exponentialSearch();
        if (this.algorithm == 1) {
            // Ordering the elements with mergesort
            Arrays.sort(this.model.getPointsRef());
            // Executing D&C approach
            logarithmicSearch(
                    0,
                    0,
                    this.model.RANGE, 
                   this.model.RANGE
            );
        }
    }
    
    // n^2 algorithm
    public void exponentialSearch () {
    }
    
    // nlogn algorithm, D&C solution
    public void logarithmicSearch (int startx, int starty, int finalx, int finaly) {
        
    }
    
    @Override
    public void notify(Event e) {
        ControllerEvent event = (ControllerEvent) e;
        
        switch (event.type) {
            case START:
                if (!this.executionThread.isAlive())
                    this.executionThread.start();
                break;
            case STOP:
                // TODO: Trigger stop condition, to implement
                break;
        }
    }
}
