package practica5.model;

/**
 *
 * @author usuario
 */
public class Language {
    private String name, dicFile;
    
    public Language(String name, String fileName) {
        this.name = name;
        this.dicFile = "src/resources/" + fileName;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getPath() {
        return this.dicFile;
    }
}
