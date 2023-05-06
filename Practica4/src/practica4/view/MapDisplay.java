package practica4.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
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
    private ArrayList<Integer> solution;
    
    public MapDisplay(Model model, String img, int pobDim) {
        this.model = model;
        this.image = img;
        this.pobDimension = pobDim;
        this.solution = new ArrayList<Integer>();
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
                    2 * pobDimension,
                    2 * pobDimension
                );
            } else {
                g.drawOval(
                    model.getPoblationX(i) - pobDimension,
                    model.getPoblationY(i) - pobDimension,
                    2 * pobDimension,
                    2 * pobDimension
                );
            }
        }
        
        if (!this.solution.isEmpty()) {
            Iterator<Integer> iter = this.solution.iterator();
            g.setFont(new Font("Arial", Font.BOLD, 12));
            g.setColor(Color.red); 
            int i = 0;
            while (iter.hasNext()) {
                int index = iter.next();
                int x = model.getPoblationX(index) - 5;
                int y = model.getPoblationY(index) + 5;

                g.drawString(Integer.toString(i), x, y);
                i++;
            }
        }

        gr.drawImage(bima, 0, 0, this);
    }
    
    public void pobSolution(ArrayList<Integer> indexs) {
        this.solution = indexs;
        this.repaint();
    }
    
    public void updateImage(String img) {
        this.image = img;
    }
}
