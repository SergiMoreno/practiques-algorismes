package practica4.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;
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
    private HashMap<Integer, Boolean> pobSelected;
    
    public MapDisplay(Model model, String img) {
        this.model = model;
        this.image = img;
        this.pobSelected = new HashMap<Integer, Boolean>();
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
        
        int pobDimension = 16;
        int n = model.getNPoblations();

        for (int i = 0; i < n; i++) {
            if (this.pobSelected.containsKey(i)) {
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
    
    public void addPoblationSelected(int index) {
        this.pobSelected.put(index, Boolean.TRUE);
    }
    
    public void removePoblationSelected(int index) {
        this.pobSelected.remove(index);
    }
    
    public void updateImage(String img) {
        this.image = img;
    }
}
