package practica6.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import practica6.model.Model;
/**
 *
 * @author usuario
 */
public class PuzzleDisplay extends JPanel {
    private Model model;
    private BufferedImage img;
    private BufferedImage [] cropes;
    private int dim;
    
    public PuzzleDisplay(Model model, BufferedImage image) {
        this.model = model;
        this.dim = model.getPuzzleSize();
        this.img = image;
        this.setImage();
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
        g.setColor(new Color(255, 237, 197));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        // calculate cell weight and height
        int ancho = this.getWidth() / dim;
        int alto = this.getHeight() / dim;

        g.setColor(new Color(0, 0, 0));
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                int index = model.getCellIndex(i, j);
                if (index == -1) {
                    continue;
                }
                g.drawImage(cropes[index], j * ancho, i * alto, ancho, alto, this);
                g.drawRect(j * ancho, i * alto, ancho, alto);
            }
        }
        
        gr.drawImage(bima,0,0,this);
    }
    
    public void resize() {
        this.dim = model.getPuzzleSize();
        this.setImage();
        this.repaint();
    }
    
    public void updateImage(BufferedImage image) {
        this.img = image;
        this.setImage();
        this.repaint();
    }
    
    public void setImage() {
        cropes = new BufferedImage[dim * dim];
        int subh = img.getHeight() / dim;
        int subw = img.getWidth() / dim;
        int num = 0;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                cropes[num++] = img.getSubimage(j * subw, i * subh, subw, subh);
            }
        }
    }
}