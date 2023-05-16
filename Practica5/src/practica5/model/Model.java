package practica5.model;

import practica5.Event;
import practica5.EventListener;
import practica5.Main;

/**
 *
 * @author usuario
 */
public class Model implements EventListener {
    private Main main;
    private static Language [] dictionaries;
    private double [][] distanceMatrix;
    private int lanCompare, lanCompareWith; // if lanCompareWith == -1, means with all
    
    public Model(Main main) {
        this.main = main;
            
        /* Iterate though the number of languages, initializing one by one */
        dictionaries = new Language[Languages.values().length];
        int i = 0;
        for (Languages l : Languages.values()) {
            dictionaries[i] = new Language(l.getDicFile());
            i++;
        }

        /* Distance matrix */
        this.distanceMatrix = new double[10][10];
        for (int j = 0; j < this.distanceMatrix.length;j++) {
            this.distanceMatrix[j][j] = 0.0;
        }
    }
    
    public String getLanguageCompared() {
        return dictionaries[this.lanCompare].getPath();
    }
    
    public String getLanguageToCompare() {
        return dictionaries[this.lanCompareWith].getPath();
    }
    
    public String getLanguageToCompare(int i) {
        return dictionaries[i].getPath();
    }
    
    public boolean compareWithAll() {
        return this.lanCompareWith == -1;
    }
    
    public static int getNLanguages() {
        return dictionaries.length;
    }

    @Override
    public void notify(Event e) {
        ModelEvent event = (ModelEvent) e;
        
        switch (event.type) {
            case START -> {
                this.lanCompare = event.lanCompare;
                this.lanCompareWith = event.lanCompareWith;
            }
        }
    }
}
