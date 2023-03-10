package practica2.controller;

import java.util.List;
import practica2.Event;
import practica2.EventType;

/**
 *
 * @author usuario
 */
public class ControllerEvent extends Event {
    public EventType[] types;
        
    public boolean operationType;   // If true means start executions, if false
                                    // means delete execution
             
    private boolean corruptData;
    
    public ControllerEvent(List<EventType> executiontypes, boolean operationType) {
        super(EventOrigin.Control);
        this.operationType = operationType;
        this.types = new EventType[executiontypes.size()];
        
        try {
            for (int i = 0; i < executiontypes.size(); i++) {
                types[i] = executiontypes.get(i);
            }
            corruptData = false;
        } catch (Exception e) {
            System.out.println("ERROR Vista: No s'ha notificat al controlador de manera correcta, revisi la crida.");
            corruptData = true;
        }
    }
    
    public boolean isCorrupt () {
        return corruptData;
    }
}
