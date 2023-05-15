package practica5.model;

/**
 *
 * @author usuario
 */
public enum Languages {
    ITALIAN,
    SPANISH,
    CATALAN,
    ENGLISH,
    PORTUGUESE,
    GERMAN,
    DEUTCH,
    FRENCH,
    BASQUE,
    GALICIAN;
    
    public String getDicFile() {
        switch (this) {
            case ITALIAN -> {
                return "it.dic";
            }
            case SPANISH -> {
                return "esp.dic";
            }
            case CATALAN -> {
                return "cat.dic";
            }
            case ENGLISH -> {
                return "eng.dic";
            }
            case PORTUGUESE -> {
                return "por.dic";
            }
            case GERMAN -> {
                return "ger.dic";
            }
            case DEUTCH -> {
                return "deu.dic";
            }
            case FRENCH -> {
                return "fr.dic";
            }
            case BASQUE -> {
                return "bas.dic";
            }
            case GALICIAN -> {
                return "gal.dic";
            }
        }
        return null;
    }
    
    static public String[] getAllFiles() {
        String [] languages = new String[Languages.values().length];
        
        int i = 0;
        for (Languages l : Languages.values()) {
            languages[i] = l.toString();
            i++;
        }
        return languages;
    }
}
