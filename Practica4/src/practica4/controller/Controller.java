package practica4.controller;

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
    
    public Controller(Main main) {
        this.main = main;
    }
    
    @Override
    public void run() {
        model = this.main.getModel();
        
        // Recursive Dijkstra algorithm
        
        // ArrayList permanentes
        // arrayList int
        
        // HashMap para guardar las etiquetas de las poblaciones (keys del hash mediante su índice)
        // hashmap (int index, double pesoRuta)
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
