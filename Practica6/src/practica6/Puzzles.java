package practica6;

/**
 *
 * @author usuario
 */
public enum Puzzles {
    SUPER_MARIO,
    FRANCE;
    
    static public String getImage(Puzzles p) {
        switch (p)  {
            case SUPER_MARIO -> {
                return "super_mario.jpg";
            }
            case FRANCE -> {
                return "france.jpg";
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
