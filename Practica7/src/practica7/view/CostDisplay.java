package practica7.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import practica7.model.Model;
/**
 *
 * @author usuario
 */
public class CostDisplay extends JPanel {
    private Model model;
    
    public CostDisplay(Model model) {
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
        int height = this.getHeight();
        int width = this.getWidth();
        
        BufferedImage bima = new BufferedImage(this.getWidth(),
                this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bima.getGraphics();
        g.setColor(new Color(255, 237, 197));
        g.fillRect(0, 0, width, height);
        gr.drawImage(bima,0,0,this);
        
        g.setColor(Color.BLACK);
        // horizontal line
        gr.drawLine(60, height - 40, width - 20, height - 40);
        // vertical line
        gr.drawLine(60, 20, 60, height - 40);
    }
}