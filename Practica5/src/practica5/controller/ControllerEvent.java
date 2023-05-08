package practica5.controller;

import practica5.Event;

/**
 *
 * @author usuario
 */
public class ControllerEvent extends Event {
    public ControllerEventType type;
    public boolean isStart;
 
    // Event to start or stop execution thread based on parameter
    public ControllerEvent(boolean isStart) {
        super(EventType.Controller);
        
        this.isStart = isStart;
        if (isStart) {
            this.type = ControllerEventType.START;
        } else {
            this.type = ControllerEventType.STOP;
        }
    }
    
    enum ControllerEventType {
        START,
        STOP
    }
}
