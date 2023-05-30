package practica6.controller;

import practica6.Event;
import practica6.EventListener;
import practica6.Main;
import practica6.model.Model;
import practica6.view.ViewEvent;

/**
 *
 * @author usuario
 */
public class Controller extends Thread implements EventListener {
    private Main main;
    private Model model;
    private Thread executionThread;
    
    public Controller(Main main) {
        this.main = main;
    }
    
    @Override
    public void run() {
        this.model = this.main.getModel();
        
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
