package practica7.controller;

import java.time.Duration;
import practica7.Event;
import practica7.EventListener;
import practica7.Main;
import practica7.model.Model;

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
        model = this.main.getModel();

        /*  */
        try {
            Thread.sleep(Duration.ZERO);

        } catch (InterruptedException ex) {
            System.out.println("Execution Stopped.");
        }
    }
    
    @Override
    public void notify(Event e) {
        ControllerEvent event = (ControllerEvent) e;
        
        switch (event.type) {
            case START -> {
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
