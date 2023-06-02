package practica6.controller;

/**
 *
 * @author usuario
 */
public enum Heuristics {
    N_WRONG_CELLS,
    MANHATTAN;
    
    public static Heuristics getByIndex(int index) {
        return Heuristics.values()[index];
    }
    
    static public String[] getAllHeuristics() {
        String [] heuristics = new String[Heuristics.values().length];
        
        int i = 0;
        for (Heuristics h : Heuristics.values()) {
            heuristics[i] = h.toString();
            i++;
        }
        return heuristics;
    }
}
