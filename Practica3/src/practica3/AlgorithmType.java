package practica3;

/**
 *
 * @author usuario
 */
// Enum class to represent the types of algorithm that can be performed
public enum AlgorithmType {
    BRUTE,
    DIVIDE_AND_CONQUER;
    
    // Method used to get the String cost representation of the algorithm
    public String getCost() {
        switch (this) {
            case BRUTE -> {
                return "Exponential (n2)";
            }
            case DIVIDE_AND_CONQUER -> {
                return "Logarithmic (nlogn)";
            }
        }
        return null;
    }
    
    // Method used to get the algorithm type based on the index selected
    public static AlgorithmType getByIndex(int index) {
        return AlgorithmType.values()[index];
    }
}
