package practica6.controller;

import practica6.Event;

/**
 *
 * @author usuario
 */
public class ControllerEvent extends Event {
    public ControllerEventType type;
    public Heuristics heuristic;
    public int speed;
    
    // Event to stop execution thread
    public ControllerEvent(int s) {
        super(EventType.Controller);
        
        this.speed = s;
        this.type = ControllerEventType.UPDATE_SPEED;
    }
 
    // Event to start execution thread assigning the heuristic
    public ControllerEvent(Heuristics h) {
        super(EventType.Controller);
        
        this.heuristic = h;
        this.type = ControllerEventType.START;
    }
    
    // Event to stop execution thread
    public ControllerEvent() {
        super(EventType.Controller);
        
        this.type = ControllerEventType.STOP;
    }
    
    enum ControllerEventType {
        UPDATE_SPEED,
        START,
        STOP
    }
}
