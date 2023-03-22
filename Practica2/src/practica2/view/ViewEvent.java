package practica2.view;

import practica2.Event;


/**
 *
 * @author usuario
 */
public class ViewEvent extends Event {
    public boolean exit;
    public ViewEventType type;
    
    public ViewEvent() {
        super(EventType.View);
        this.type = ViewEventType.REFRESH_BOARD;
    }
    
    public ViewEvent(boolean exit) {
        super(EventType.View);
        this.exit = exit;
        this.type = ViewEventType.END;
    }
    
    enum ViewEventType {
        REFRESH_BOARD,
        END
    }
}
