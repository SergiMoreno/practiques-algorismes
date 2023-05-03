package practica4;

/**
 *
 * @author usuario
 */
public enum Map {
    PITIUSES;
    
    static public String getImage(Map m) {
        switch (m)  {
            case PITIUSES -> {
                return "pitiuses.png";
            }
        }
        
        return "";
    }
    
    static public String getFile(Map  m) {
        switch (m)  {
            case PITIUSES -> {
                return "pitiuses.ltim";
            }
        }
        
        return "";
    }
}
