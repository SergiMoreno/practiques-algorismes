package practica4.controller;

/**
 *
 * @author usuario
 */
public enum StructureTechnique {
    QUEUE,
    BINARY_HEAP,
    FIBONACCI_HEAP;
    
    // Method used to get the algorithm type based on the index selected
    public static StructureTechnique getByIndex(int index) {
        return StructureTechnique.values()[index];
    }
}
