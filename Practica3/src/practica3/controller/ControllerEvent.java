package practica3.controller;

import practica3.Event;

public class ControllerEvent extends Event {    
    public ControlEventType type;
    public final int algorithm;
 
    public ControllerEvent(int algorithm) {
        super(EventType.Controller);
        this.type = ControlEventType.START;
        this.algorithm = algorithm;
    }
    
    public ControllerEvent() {
        super(EventType.Controller);
        this.type = ControlEventType.STOP;
        this.algorithm = -1;
    }
    
    enum ControlEventType {
        START,
        STOP
    }
}
