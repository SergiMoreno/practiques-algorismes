package practica6.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import practica6.model.Model;

/**
 *
 * @author usuario
 */
public class PuzzleDisplay extends JPanel {
    private Model model;
    private BufferedImage bima;
    private BufferedImage [] cropes;
    
    public PuzzleDisplay(Model model) {
        this.model = model;
    }
    
    @Override
    public void repaint() {
        if (this.getGraphics() != null) {
            paint(this.getGraphics());
        }
    }

    @Override
    public void paint(Graphics gr) {
        try {
            BufferedImage bima = new BufferedImage(this.getWidth(),
                    this.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics g = bima.getGraphics();
            g.setColor(new Color(255, 237, 197));
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            
            //int dim = model.getBoardSize();
            int dim = 4;
            // calculate cell weight and height
            int ancho = this.getWidth() / dim;
            int alto = this.getHeight() / dim;
            // Get puzzle image
            URL imageURL = getClass().getResource("../../resources/images.jpg");
            BufferedImage image = ImageIO.read(imageURL);
            cropes = new BufferedImage[dim*dim-1];
            int subh = image.getHeight() / dim;
            int subw = image.getWidth() / dim;
            int num = 0;
            for (int i = 0; i < dim; i++) {
                for (int j = 0; j < dim; j++) {
                    if (i == dim-1 && j == dim-1) continue;
                    cropes[num++] = image.getSubimage(j * subw, i * subh, subw, subh);
                }
            }
            //num = 0;
            g.setColor(new Color(0, 0, 0));
            for (int i = 0; i < dim; i++) {
                for (int j = 0; j < dim; j++) {
                    if (i == dim-1 && j == dim-1) continue;
                    //BufferedImage crop = image.getSubimage(j * subw, i * subh, subw, subh);
                    g.drawImage(cropes[--num], j * ancho, i * alto, ancho, alto, this);
                    g.drawRect(j * ancho, i * alto, ancho, alto);
                }
            }
            gr.drawImage(bima,0,0,this);
        } catch (IOException ex) {
            Logger.getLogger(PuzzleDisplay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void reset() {
        this.repaint();
    }
}