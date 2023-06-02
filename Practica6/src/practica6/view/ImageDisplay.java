package practica6.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author usuario
 */
public class ImageDisplay extends JPanel {
    private BufferedImage img;
    private int puzzleSize;
    
    public ImageDisplay(BufferedImage image, int size) {
        this.img = image;
        this.puzzleSize = size;
    }
    
    @Override
    public void repaint() {
        if (this.getGraphics() != null) {
            paint(this.getGraphics());
        }
    }
    
    @Override
    public void paint(Graphics gr) {
        BufferedImage bima = new BufferedImage(this.getWidth(),
                this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bima.getGraphics();
        
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        
        // calculate cell weight and height
        int ancho = this.getWidth() / this.puzzleSize;
        int alto = this.getHeight() / this.puzzleSize;
        
        g.setColor(new Color(255, 237, 197));
        g.fillRect(ancho * (this.puzzleSize-1), alto * (this.puzzleSize-1), this.getWidth(), this.getHeight());
        
        gr.drawImage(bima, 0, 0, this);
    }
    
    public void resize(int size) {
        this.puzzleSize = size;
        this.repaint();
    }
    
    public void updateImage(BufferedImage image) {
        this.img = image;
        this.repaint();
    }
}
