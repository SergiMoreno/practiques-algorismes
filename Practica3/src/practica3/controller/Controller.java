package practica3.controller;

import practica3.Event;
import practica3.EventListener;
import practica3.Main;

/**
 *
 * @author usuario
 */

public class Controller extends Thread implements EventListener {
    private Main main;
    private int algorithm;
    private Controller executionThread;
    
    public Controller(Main main) {
        this.main = main;
        this.executionThread = new Controller(this.main);
    }
    
    @Override
    public void run () {
        if (this.algorithm == 0) exponentialSearch();
        if (this.algorithm == 1) logarithmicSearch();
    }
    
    // n^2 algorithm
    public void exponentialSearch () {
    }
    
    // nlogn algorithm, D&C solution
    public void logarithmicSearch () {
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
