package practica4.controller;

/**
 *
 * @author usuario
 */
public enum AlgorithmTechnique {
    QUEUE,
    BINARY_HEAP,
    FIBONACCI_HEAP;
    
    // Method used to get the algorithm type based on the index selected
    public static AlgorithmTechnique getByIndex(int index) {
        return AlgorithmTechnique.values()[index];
    }
}
