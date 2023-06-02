package practica6.view;

import practica6.Event;

/**
 *
 * @author usuario
 */
public class ViewEvent extends Event {
    public ViewEventType type;
    public int cost;

    // Event to notify the result
    public ViewEvent(int c) {
        super(EventType.View);
        
        this.cost = c;
        this.type = ViewEventType.SHOW_RESULT;
    }

    enum ViewEventType {
        SHOW_RESULT
    }
}
