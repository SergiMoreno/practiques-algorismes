package practica3.controller;

import practica3.Event;

public class ControllerEvent extends Event {    
    public ControlEventType type;
    
    // Start algorithm execution / Update algorithm speed
    public ControllerEvent(boolean start, int speed) {
        super(EventType.Controller);

    }
    
    enum ControlEventType {

    }
}
