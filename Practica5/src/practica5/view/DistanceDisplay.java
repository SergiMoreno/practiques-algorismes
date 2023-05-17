package practica5.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import practica5.model.Model;

/**
 *
 * @author usuario
 */
public class DistanceDisplay extends JPanel {
    private Model model;
    private double [] graphic;
    private double [][] graph;
    private boolean showGraph, showGraphic;
    
    public DistanceDisplay(Model model) {
        this.model = model;
        this.showGraph = false;
        this.showGraphic = false;
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
        
        if (showGraphic) {
            
        } else if (showGraph) {
            
        }

        gr.drawImage(bima, 0, 0, this);
    }
    
    public void printGraphic(double [] graphic) {
        this.graphic = graphic.clone();
        this.repaint();
    }
    
    public void printGraph(double [][] graph) {
        this.showGraph = true;
        this.graph = graph.clone();
        this.repaint();
    }
    
    public void reset() {
        this.showGraph = false;
        this.showGraphic = false;
    }
}
