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
    GALEGO;
    
    public String getDicFile() {
        switch (this) {
            case ITALIAN -> {
                return "italian.dic";
            }
            case SPANISH -> {
                return "spanish.dic";
            }
            case CATALAN -> {
                return "catalan.dic";
            }
            case ENGLISH -> {
                return "english.dic";
            }
            case PORTUGUESE -> {
                return "portuguese.dic";
            }
            case GERMAN -> {
                return "german.dic";
            }
            case DEUTCH -> {
                return "deutch.dic";
            }
            case FRENCH -> {
                return "french.dic";
            }
            case BASQUE -> {
                return "basque.dic";
            }
            case GALEGO -> {
                return "galego.dic";
            }
        }
        return null;
    }
}
