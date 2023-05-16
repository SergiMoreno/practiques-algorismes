package practica5.view;

import practica5.Event;

/**
 *
 * @author usuario
 */
public class ViewEvent extends Event {
    public ViewEventType type;
    public double [] results;

    public ViewEvent() {
        super(EventType.View);
        
        this.type = ViewEventType.SHOW_RESULT;
    }
    
    enum ViewEventType {
        SHOW_RESULT
    }
}
