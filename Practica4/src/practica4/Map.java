package practica4;

/**
 *
 * @author usuario
 */
public enum Map {
    PITIUSES,
    MENORCA,
    MALLORCA;
    
    static public String getImage(Map m) {
        switch (m)  {
            case PITIUSES -> {
                return "pitiuses.png";
            }
            case MENORCA -> {
                return "menorca.png";
            }
            case MALLORCA -> {
                return "mallorca.png";
            }
        }
        
        return "";
    }
    
    static public String getFile(Map  m) {
        switch (m)  {
            case PITIUSES -> {
                return "pitiuses.ltim";
            }
            case MENORCA -> {
                return "menorca.ltim";
            }
            case MALLORCA -> {
                return "mallorca.ltim";
            }
        }
        
        return "";
    }
    
    static public Map getByIndex(int i) {
        return Map.values()[i];
    }
}
