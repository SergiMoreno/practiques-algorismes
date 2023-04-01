package practica3.view;

import practica3.Event;


/**
 *
 * @author usuario
 */
public class ViewEvent extends Event {
    public ViewEventType type;
    
    public ViewEvent() {
        super(EventType.View);
        this.type = ViewEventType.REFRESH;
    }
    
    enum ViewEventType {
        REFRESH
    }
}
