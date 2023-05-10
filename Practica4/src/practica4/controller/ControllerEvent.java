package practica4.controller;

import practica4.Event;

/**
 *
 * @author usuario
 */
public class ControllerEvent extends Event {
    public ControllerEventType type;
    public StructureTechnique technique;
 
    // Event to start the thread executing the specified algorithm
    public ControllerEvent(int i) {
        super(EventType.Controller);
        
        this.technique = StructureTechnique.getByIndex(i);
        this.type = ControllerEventType.START;
    }
    
    // Event to stop the current thread
    public ControllerEvent() {
        super(EventType.Controller);
        
        this.type = ControllerEventType.STOP;
    }
    
    enum ControllerEventType {
        START,
        STOP
    }
}
