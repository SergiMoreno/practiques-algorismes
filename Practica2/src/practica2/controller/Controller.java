package practica2.controller;

import practica2.Event;
import practica2.EventListener;
import practica2.Main;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author usuario
 */

public class Controller extends Thread implements EventListener {
    private Main main;
<<<<<<< HEAD
    private Controller game;
    static private AtomicBoolean stop;  // stop condition
    // Atomic: Avoid dirty reads between threads
=======
    static private AtomicBoolean stop;  // Avoid dirty reads between threads
    private int speed;
>>>>>>> b11e592430b2b73218c1a26735d1001a81a13683
    
    public Controller(Main main) {
        this.main = main;
    }
    
    @Override
    public void run () {

        
    }
    
    @Override
    public void notify(Event e) {
        ControllerEvent event = (ControllerEvent) e;
        
        switch (event.type) {
            case SET_SPEED -> {
                this.speed = event.speed;
            }
            case START -> {
                run();
            }
            case STOP -> {
                this.stop.set(true);    // Stops the backtracking algorithm
            }
        }
    }
}
