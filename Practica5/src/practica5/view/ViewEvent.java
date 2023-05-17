package practica5.view;

import practica5.Event;

/**
 *
 * @author usuario
 */
public class ViewEvent extends Event {
    public ViewEventType type;
    public double [] graphic;
    public double [][] graph;

    public ViewEvent(double [] values) {
        super(EventType.View);
        
        this.graphic = values.clone();
        this.type = ViewEventType.SHOW_GRAPHIC;
    }
    
    public ViewEvent(double [][] values) {
        super(EventType.View);
        
        this.graph = values.clone();
        this.type = ViewEventType.SHOW_GRAPH;
    }
    
    enum ViewEventType {
        SHOW_GRAPHIC,
        SHOW_GRAPH
    }
}
