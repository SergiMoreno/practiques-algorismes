package practica2.view;

import practica2.Event;
import practica2.EventType;


/**
 *
 * @author usuario
 */
public class ViewEvent extends Event {
    public EventType type;
    public long time;
    
    public ViewEvent(long time, EventType typeOp) {
        super(EventOrigin.Vista);
        this.type = typeOp;
        this.time = time;
    }
}
