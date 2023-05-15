package practica5.view;

import practica5.Event;

/**
 *
 * @author usuario
 */
public class ViewEvent extends Event {

    public ViewEvent() {
        super(EventType.View);
    }
    
    enum ViewEventType {
        SHOW_RESULT
    }
}
