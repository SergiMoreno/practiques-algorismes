package practica5.model;

/**
 *
 * @author usuario
 */
public class Language {
    private String dicFile;
    private String currentWord;
    
    public Language(String fileName) {
        this.dicFile = "src/resources/" + fileName;
        this.currentWord = null;
    }
    
    public void setWord(String w) {
        this.currentWord = w;
    }
    
    public String getCurrentWord() {
        return this.currentWord;
    }
    
    public String getPath() {
        return this.dicFile;
    }
}
