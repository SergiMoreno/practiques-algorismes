package practica2.view;

import practica2.Event;


/**
 *
 * @author usuario
 */
public class ViewEvent extends Event {
    public boolean success;
    public ViewEventType type;
    
    // Refresh board display
    public ViewEvent() {
        super(EventType.View);
        this.type = ViewEventType.REFRESH_BOARD;
    }
    
    // Message end dialog
    public ViewEvent(boolean success) {
        super(EventType.View);
        this.success = success;
        this.type = ViewEventType.ALGORITHM_END;
    }
    
    enum ViewEventType {
        REFRESH_BOARD,
        ALGORITHM_END
    }
}
