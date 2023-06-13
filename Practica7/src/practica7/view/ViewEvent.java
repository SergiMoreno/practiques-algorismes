package practica7.view;

import practica7.Event;

/**
 *
 * @author usuario
 */
public class ViewEvent extends Event {
    public ViewEventType type;

    // Event to notify the result
    public ViewEvent(int c) {
        super(EventType.View);
        
        this.type = ViewEventType.SHOW_RESULT;
    }
    
    // Event to notify
    public ViewEvent() {
        super(EventType.View);
        
        this.type = ViewEventType.UPDATE_GRAPHIC;
    }

    enum ViewEventType {
        SHOW_RESULT,
        UPDATE_GRAPHIC
    }
}
