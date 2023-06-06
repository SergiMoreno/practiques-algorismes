package practica6;

/**
 *
 * @author usuario
 */
public enum Puzzles {
    FRANCE,
    SUPER_MARIO,
    UIB;
    
    static public String getImage(Puzzles p) {
        switch (p)  {
            case FRANCE -> {
                return "france.jpg";
            }
            case SUPER_MARIO -> {
                return "super_mario.jpg";
            }
            case UIB -> {
                return "uib.jpg";
            }
        }
        
        return "";
    }
    
    // Used for display of puzzles in View
    static public String[] getAllPuzzles() {
        String [] puzzles = new String[Puzzles.values().length];
        
        int i = 0;
        for (Puzzles h : Puzzles.values()) {
            puzzles[i] = h.toString();
            i++;
        }
        return puzzles;
    }
    
    static public Puzzles getByIndex(int i) {
        return Puzzles.values()[i];
    }
}
