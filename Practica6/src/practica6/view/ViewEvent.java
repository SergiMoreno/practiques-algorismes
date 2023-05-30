package practica6.view;

import practica6.Event;

/**
 *
 * @author usuario
 */
public class ViewEvent extends Event {
    public ViewEventType type;

    public ViewEvent() {
        super(EventType.View);

    }

    enum ViewEventType {
        
    }
}
