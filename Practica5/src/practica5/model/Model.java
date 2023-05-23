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
    private Language [] dictionaries;
    // if lanCompare == -1, means all
    // if lanCompareWith == -1, means with all
    private int lanCompare, lanCompareWith;
    // Keep the word to detect the language
    private String [] textWords;
    
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
    
    public int getLanguageCompared() {
        return this.lanCompare;
    }
    
    public int getLanguageToCompare() {
        return this.lanCompareWith;
    }
    
    // If the language indexed is -1, it refers to the text array
    public int getLanguageLength(int i) {
        if (i == -1) return this.textWords.length;
        return this.dictionaries[i].getLength();
    }
    
    // If the language indexed is -1, it refers to the text array
    public String getLanguageWord(int i, int w) {
        if (i == -1) return this.textWords[w];
        return this.dictionaries[i].getWord(w);
    }
    
    public int getNLanguages() {
        return dictionaries.length;
    }
    
    public String getLanguageComparedName() {
        return dictionaries[this.lanCompare].getName();
    }
    
    public String getLanguageToCompareName() {
        return dictionaries[this.lanCompareWith].getName();
    }

    public String getLanguageName(int i) {
        return dictionaries[i].getName();
    }
    
    public boolean isLanguageDetection() {
        return this.textWords != null;
    }
    
    public boolean isSameLanguage(int i) {
        return this.lanCompare == i;
    }
    
    public boolean compareAll() {
        return this.lanCompare == -1;
    }
    
    public boolean compareWithAll() {
        return this.lanCompareWith == -1;
    }

    @Override
    public void notify(Event e) {
        ModelEvent event = (ModelEvent) e;
        
        switch (event.type) {
            case START_DICTIONARIES -> {
                this.textWords = null;
                this.lanCompare = event.lanCompare;
                this.lanCompareWith = event.lanCompareWith;
            }
            case START_TEXT -> {
                this.textWords = event.textWords.clone();
            }
        }
    }
}
