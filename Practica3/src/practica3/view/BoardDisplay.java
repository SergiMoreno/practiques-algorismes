package practica3.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import practica3.model.Model;

/**
 *
 * @author usuario
 */

public class BoardDisplay extends JPanel {
    final private Model model;
    
    public BoardDisplay(Model model) {
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
        BufferedImage bima = new BufferedImage(this.getWidth(),
                this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bima.getGraphics();
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        gr.drawImage(bima,0,0,this);
    }
    
    public void refresh() {
        this.repaint();
    }
}
