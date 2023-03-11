package practica2.controller;

import java.util.ArrayList;
import practica2.EventType;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Random;
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
    
    public Controller(Main main) {
        this.main = main;
    }
    
    @Override
    public void notify(Event e) {
        ControllerEvent event = (ControllerEvent) e;
        
        
    }
    
    private void backtracking () {
        
    }
}
