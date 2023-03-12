package practica2.controller;

import java.util.List;
import practica2.Event;
import practica2.EventType;

public class ControllerEvent extends Event {    
    // Types of operations that can be launched with this event
    static final int START = 0;
    static final int STOP = 1;
    
    public int event;
    
    public ControllerEvent(int eventKind) {
        super(EventOrigin.Control);
        
        this.event = eventKind;
    }
}
