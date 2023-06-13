package practica7.controller;

import practica7.Operation;
import practica7.Event;

/**
 *
 * @author usuario
 */
public class ControllerEvent extends Event {
    public ControllerEventType type;
    public Operation operation;
 
    // Event to start execution thread assigning the heuristic
    public ControllerEvent(Operation operation) {
        super(EventType.Controller);
        
        this.operation = operation;
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
