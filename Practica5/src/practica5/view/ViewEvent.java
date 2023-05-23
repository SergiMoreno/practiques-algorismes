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
    public int nValues;
    public String languageName;

    public ViewEvent(double [] values) {
        super(EventType.View);
        
        this.graphic = values.clone();
        this.type = ViewEventType.SHOW_GRAPHIC;
    }
    
    public ViewEvent(double [][] values, int num) {
        super(EventType.View);
        
        this.graph = values.clone();
        this.nValues = num;
        this.type = ViewEventType.SHOW_GRAPH;
    }
    
    public ViewEvent(String name) {
        super(EventType.View);
        
        this.languageName = name;
        this.type = ViewEventType.SHOW_PANEL;
    }
    
    enum ViewEventType {
        SHOW_GRAPHIC,
        SHOW_GRAPH,
        SHOW_PANEL
    }
}
