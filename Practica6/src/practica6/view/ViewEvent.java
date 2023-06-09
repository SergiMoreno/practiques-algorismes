package practica6.view;

import practica6.Event;

/**
 *
 * @author usuario
 */
public class ViewEvent extends Event {
    public ViewEventType type;
    public int cost;

    // Event to notify the result
    public ViewEvent(int c) {
        super(EventType.View);
        
        this.cost = c;
        this.type = ViewEventType.SHOW_COST;
    }
    
    // Event to notify that none solution was reached
    public ViewEvent(boolean isStep) {
        super(EventType.View);
        
        if (isStep) this.type = ViewEventType.REPAINT;
        else this.type = ViewEventType.NO_SOLUTION;
    }

    enum ViewEventType {
        REPAINT,
        SHOW_COST,
        NO_SOLUTION
    }
}
