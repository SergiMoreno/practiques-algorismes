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
    public ArrayList<Integer> index;
    
    public ViewEvent(ArrayList<Integer> index) {
        super(EventType.View);
        this.type = ViewEventType.REFRESH;
        this.index = index;
    }
    
    enum ViewEventType {
        REFRESH
    }
}
