package practica4.controller;

import practica4.Event;

/**
 *
 * @author usuario
 */
public class ControllerEvent extends Event {
    public ControllerEventType type;
 
    // Event to start execution thread
    public ControllerEvent() {
        super(EventType.Controller);
        this.type = ControllerEventType.START;
    }
    
    // Event to stop controller thread
    /*public ControllerEvent() {
        super(EventType.Controller);
        this.type = ControllerEventType.STOP;
    }*/
    
    enum ControllerEventType {
        START,
        STOP
    }
}
