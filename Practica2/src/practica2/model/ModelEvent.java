package practica2.model;

import practica2.Event;

/**
 *
 * @author usuario
 */
public class ModelEvent extends Event {
    public int length;

    public ModelEvent(int n) {
        super(EventOrigin.Model);
        this.length = n;
    }
}
