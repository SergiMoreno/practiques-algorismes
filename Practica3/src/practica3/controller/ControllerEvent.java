package practica3.controller;

import practica3.AlgorithmType;
import practica3.Event;

public class ControllerEvent extends Event {    
    public ControllerEventType type;
    public AlgorithmType algorithm;
    public int nPairs;
 
    // Event to start execution thread
    public ControllerEvent(AlgorithmType algorithm, int n) {
        super(EventType.Controller);
        this.type = ControllerEventType.START;
        this.algorithm = algorithm;
        this.nPairs = n;
    }
    
    // Event to stop controller thread
    public ControllerEvent() {
        super(EventType.Controller);
        this.type = ControllerEventType.STOP;
    }
    
    enum ControllerEventType {
        START,
        STOP
    }
}
