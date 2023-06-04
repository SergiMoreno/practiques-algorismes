package practica7.controller;

import practica7.Event;

/**
 *
 * @author usuario
 */
public class ControllerEvent extends Event {
    public ControllerEventType type;
 
    // Event to start execution thread assigning the heuristic
    public ControllerEvent(int i) {
        super(EventType.Controller);
        
        this.type = ControllerEventType.START;
    }
    
    // Event to stop execution thread
    public ControllerEvent() {
        super(EventType.Controller);
        
        this.type = ControllerEventType.STOP;
    }
    
    enum ControllerEventType {
        START,
        STOP
    }
}
