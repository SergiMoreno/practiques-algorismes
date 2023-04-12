package practica3.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;
import practica3.model.Model;

/**
 *
 * @author usuario
 */
public class CloudDisplay extends JPanel {

    final private Model model;
    private ArrayList<Integer> resultIndex;

    public CloudDisplay(Model model) {
        this.model = model;
        this.resultIndex = new ArrayList<Integer>();
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
            // Check if the point is one of the possible result
            boolean isResult = this.resultIndex.contains(i);
            if (isResult) {
                g.setColor(Color.RED);
            }

            g.fillOval(
                    centerX + (int)model.getPointX(i) - 5,
                    centerY + (int)model.getPointY(i) - 5,
                    5,
                    5
            );
            
            if (isResult) {
                g.setColor(new Color(0, 0, 0));
            }
        }
        gr.drawImage(bima, 0, 0, this);
    }

    public void showResult(ArrayList<Integer> indexs) {
        this.resultIndex = indexs;
        this.repaint();
    }
    
    public void reset(){
        this.resultIndex = new ArrayList<Integer>();
        this.repaint();
    }

    public void refresh() {
        this.repaint();
    }
}
