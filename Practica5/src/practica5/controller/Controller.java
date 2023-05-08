package practica5.controller;

import practica5.Event;
import practica5.EventListener;
import practica5.Main;

/**
 *
 * @author usuario
 */
public class Controller extends Thread implements EventListener {
    private Main main;
    
    private Thread executionThread;
    
    public Controller(Main main) {
        this.main = main;
    }
    
    @Override
    public void run() {
        /* Levenshtein distance */
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
