package practica3.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import practica3.model.Model;

/**
 *
 * @author usuario
 */

public class CloudDisplay extends JPanel {
    final private Model model;
    
    public CloudDisplay(Model model) {
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
        g.setColor(new Color(255, 237, 197));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        // Coordinates of the cloud center
        int centerX = this.getWidth() / 2;
        int centerY = this.getHeight() / 2;
        int n = model.getNumberOfPoints();
        // Draw Points
        g.setColor(new Color(0, 0, 0));
        for (int i = 0; i < n; i++) {            
            g.fillOval(
                    centerX + model.getPointX(i)-3, 
                    centerY + model.getPointY(i)-3, 
                    3, 
                    3
            );
        }
        gr.drawImage(bima,0,0,this);
    }
    
    public void refresh() {
        this.repaint();
    }
}
