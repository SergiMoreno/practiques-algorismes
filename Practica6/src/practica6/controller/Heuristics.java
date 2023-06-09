package practica6.controller;

/**
 *
 * @author usuario
 */
public enum Heuristics {
    N_WRONG_CELLS,
    MANHATTAN,
    EUCLIDEAN,
    LINEAR_CONFLICT,
    MAX_HEURISTIC,
    ID,
    MD_LC,
    MD_ID;
    
    public static Heuristics getByIndex(int index) {
        return Heuristics.values()[index];
    }
    
    static private String getHeuristicName(Heuristics h) {
        switch (h)  {
            case N_WRONG_CELLS -> {
                return "WRONG CELLS";
            }
            case MANHATTAN -> {
                return "MANHATTAN (MD)";
            }
            case EUCLIDEAN -> {
                return "EUCLIDEAN";
            }
            case LINEAR_CONFLICT -> {
                return "LINEAR CONFLICT (LC)";
            }
            case MAX_HEURISTIC -> {
                return "MAX VALUE";
            }
            case ID -> {
                return "INVERSION DISTANCE (ID)";
            }
            case MD_LC -> {
                return "MD + LC";
            }
            case MD_ID -> {
                return "MD + ID";
            }
        }
        
        return "";
    }
    
    // Used for display of heuristics in View
    static public String[] getAllHeuristics() {
        String [] heuristics = new String[Heuristics.values().length];
        
        int i = 0;
        for (Heuristics h : Heuristics.values()) {
            heuristics[i] = getHeuristicName(h);
            i++;
        }
        return heuristics;
    }
}
