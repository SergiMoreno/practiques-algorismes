package practica2.view;

import practica2.Event;


/**
 *
 * @author usuario
 */
public class ViewEvent extends Event {
    public EventType type;
    
    public ViewEvent() {
        super(EventType.View);

    }
}
