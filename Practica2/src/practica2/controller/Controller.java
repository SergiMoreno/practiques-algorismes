package practica2.controller;

import java.util.ArrayList;
import practica2.EventType;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import practica2.Event;
import practica2.EventListener;
import static practica2.EventType.*;
import practica2.Main;
import practica2.model.Model;
import practica2.view.ViewEvent;

/**
 *
 * @author usuario
 */

public class Controller extends Thread implements EventListener {
    private Main main;
    private Controller game;
    
    public Controller(Main main) {
        this.main = main;
    }
    
    @Override
    public void run () {
        
    }
    
    @Override
    public void notify(Event e) {
        ControllerEvent event = (ControllerEvent) e;
        if (event.event == ControllerEvent.START) {
            game = new Controller(this.main);
            game.start();
        } 
        if (event.event == ControllerEvent.STOP) {
            
        }
    }
    
    private void game () {
        
    }
}
