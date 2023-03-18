package practica2.controller;

import practica2.Event;

public class ControllerEvent extends Event {    
    public int speed;
    public ControlEventType type;
    
    public ControllerEvent(boolean start, int speed) {
        super(EventType.Control);
        this.speed = speed;
        if (start) {
            this.type = ControlEventType.START;
        } else {
            this.type = ControlEventType.STOP;
        }
    }
    
    public ControllerEvent(int speed) {
        super(EventType.Control);
        
        this.speed = speed;
        this.type = ControlEventType.SET_SPEED;
    }
    
    enum ControlEventType {
        SET_SPEED,
        START,
        STOP
    }
}
