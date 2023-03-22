package practica2.pieces;

/**
 *
 * @author mascport
 */

public abstract class Piece {
    protected int movx[];
    protected int movy[];
    protected String name;
    protected String image;
    protected boolean affectsdimension = false;
    // piece position on board
    protected int x, y;
    
    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getPosX() {
        return x;
    }
    
    public int getPosY() {
        return y;
    }

    public boolean afectaDimension() {
        return affectsdimension;
    }
    
    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
    
    public int getNumMovs() {
        return movx.length;
    }
    
    public int getMovX(int i) {
        return movx[i];
    }
    
    public int getMovY(int i) {
        return movy[i];
    }
    
    // static method to compose the list of pieces shown in View
    public static String [] getPiecesTypes() {
        String [] result = new String[PieceTypes.values().length];
        int i = 0;
        for (PieceTypes p : PieceTypes.values()) {
            result[i] = p.name();
            i++;
        }
        return result;
    }
    
    public enum PieceTypes {
        BicMac,
        Bishop,
        Horse,
        King,
        Queen,
        Tower
    }
}
