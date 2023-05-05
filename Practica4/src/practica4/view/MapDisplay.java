package practica4.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import practica4.model.Model;

/**
 *
 * @author usuario
 */
public class MapDisplay extends JPanel {
    private Model model;
    private String image;
    private int pobDimension;
    
    public MapDisplay(Model model, String img, int pobDim) {
        this.model = model;
        this.image = img;
        this.pobDimension = pobDim;
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
        URL imageURL = getClass().getResource("../../resources/" + image);
        g.drawImage((new ImageIcon(imageURL)).getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
        
        g.setColor(Color.BLACK);
        
        int n = model.getNPoblations();

        for (int i = 0; i < n; i++) {
            if (model.isSelected(i)) {
                g.fillOval(
                    model.getPoblationX(i) - pobDimension,
                    model.getPoblationY(i) - pobDimension,
                    pobDimension,
                    pobDimension
                );
            } else {
                g.drawOval(
                    model.getPoblationX(i) - pobDimension,
                    model.getPoblationY(i) - pobDimension,
                    pobDimension,
                    pobDimension
                );
            }
        }

        gr.drawImage(bima, 0, 0, this);
    }
    
    public void updateImage(String img) {
        this.image = img;
    }
}
