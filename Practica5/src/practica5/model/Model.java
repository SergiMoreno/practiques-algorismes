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
    // if lanCompare == -1, means all
    // if lanCompareWith == -1, means with all
    private int lanCompare, lanCompareWith;
    
    public Model(Main main) {
        this.main = main;
            
        /* Iterate though the number of languages, initializing one by one */
        dictionaries = new Language[Languages.values().length];
        int i = 0;
        for (Languages l : Languages.values()) {
            dictionaries[i] = new Language(l.toString(), l.getDicFile());
            i++;
        }
    }
    
    public String getLanguageCompared() {
        return dictionaries[this.lanCompare].getPath();
    }
    
    public String getLanguageCompared(int i) {
        return dictionaries[i].getPath();
    }
    
    public String getLanguageComparedName() {
        return dictionaries[this.lanCompare].getName();
    }
    
    public String getLanguageToCompare() {
        return dictionaries[this.lanCompareWith].getPath();
    }
    
    public String getLanguageToCompare(int i) {
        return dictionaries[i].getPath();
    }
    
    public String getLanguageToCompareName() {
        return dictionaries[this.lanCompareWith].getName();
    }
    
    public boolean compareAll() {
        return this.lanCompare == -1;
    }
    
    public boolean compareWithAll() {
        return this.lanCompareWith == -1;
    }
    
    public static int getNLanguages() {
        return dictionaries.length;
    }
    
    public static String getLanguageName(int i) {
        return dictionaries[i].getName();
    }
    
    public boolean isSameLanguage(int i) {
        return this.lanCompare == i;
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
