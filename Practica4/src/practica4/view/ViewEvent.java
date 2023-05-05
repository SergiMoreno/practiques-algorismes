package practica4.view;

import java.util.ArrayList;
import practica4.Event;

/**
 *
 * @author usuario
 */
public class ViewEvent extends Event {
    public ViewEventType type;
    public ArrayList<Integer> indexs;
    
    public ViewEvent(ArrayList<Integer> result) {
        super(EventType.View);
        
        this.indexs = result;
        this.type = ViewEventType.SHOW_RESULT;
    }
    
    enum ViewEventType {
        SHOW_RESULT
    }
}
