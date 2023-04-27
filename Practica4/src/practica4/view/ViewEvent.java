package practica4.view;

import practica4.Event;

/**
 *
 * @author usuario
 */
public class ViewEvent extends Event {
    public ViewEventType type;
    
    public ViewEvent() {
        super(EventType.View);
        this.type = ViewEventType.SHOW_RESULT;
    }
    
    enum ViewEventType {
        SHOW_RESULT
    }
}
