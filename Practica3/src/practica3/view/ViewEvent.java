package practica3.view;

import java.util.ArrayList;
import java.util.Arrays;
import practica3.Event;


/**
 *
 * @author usuario
 */
public class ViewEvent extends Event {
    public ViewEventType type;
    public ArrayList<Integer> indexs;
    
    // Event when the controller ends its execution and it can show which points
    // have been found
    public ViewEvent(ArrayList<Integer> indexs) {
        super(EventType.View);
        this.type = ViewEventType.SHOW_RESULT;
        this.indexs = indexs;
    }
    
    enum ViewEventType {
        SHOW_RESULT
    }
}
