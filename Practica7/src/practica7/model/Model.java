package practica7.model;

import java.math.BigInteger;
import java.util.ArrayList;
import practica7.Event;
import practica7.EventListener;
import practica7.Main;

/**
 *
 * @author usuario
 */
public class Model implements EventListener {
    private Main main;
    private BigInteger number;
    
    private ArrayList<Long> timeList;
    
    public Model(Main main) {
        this.main = main;
        this.timeList = new ArrayList<Long>();
    }
    
    public BigInteger getNumber() {
        return this.number;
    }
    
    public int getTimeListSize() {
        return this.timeList.size();
    }
    
    public long getTime(int i) {
        return this.timeList.get(i);
    }

    @Override
    public void notify(Event e) {
        ModelEvent event = (ModelEvent) e;
        
        switch (event.type) {
            case UPDATE_NUMBER -> {
                this.number = new BigInteger(event.number);
            }
            case ADD_TIME -> {
                this.timeList.add(event.time);
            }
        }
    }
}
