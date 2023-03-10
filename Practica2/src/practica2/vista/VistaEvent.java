package practica2.vista;

import practica2.Event;
import practica2.EventType;


/**
 *
 * @author usuario
 */
public class VistaEvent extends Event {
    public EventType type;
    public long time;
    
    public VistaEvent(long time, EventType typeOp) {
        super(EventOrigin.Vista);
        this.type = typeOp;
        this.time = time;
    }
}
