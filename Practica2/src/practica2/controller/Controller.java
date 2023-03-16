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
    private Controller game;
    static private AtomicBoolean stop;  // stop condition
    // Atomic: Avoid dirty reads between threads
    
    public Controller(Main main) {
        this.main = main;
        this.stop = new AtomicBoolean();
        this.stop.set(false);
    }
    
    @Override
    public void run () {
        game();
    }
    
    private void game () {
        // TODO: Write backtracking algorithm here
        // Have in mind that each iteration needs to check the stop condition
    }
    
    @Override
    public void notify(Event e) {
        ControllerEvent event = (ControllerEvent) e;
        if (event.event == ControllerEvent.START) {
            // Starts new thread that executes backtacking
            this.game = new Controller(this.main);
            this.game.start();
        } 
        if (event.event == ControllerEvent.STOP) {
            this.stop.set(true);    // Stops the backtracking algorithm
        }
    }
}
