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
            g.setColor(Color.gray);
            int w = this.getWidth();
            int h = this.getHeight();
            int cell = (h - 60) / 11;
            double percentatge = 0.0;
            for (int i = 1; i < 12; i++) {
                g.drawString(Double.toString(percentatge), 65, h - (cell * i) + 5);
                g.drawLine(100, h - (cell * i), w-100, h - (cell * i));
                percentatge = roundDouble(percentatge + 0.1, 1);
            }
            
            String name = model.getLanguageComparedName();
            g.drawString(name, w/2, h - (cell * 11) - (cell/2));
            int numToCompare = this.graphic.length;
            if (numToCompare == 1) {
                String nameToCompare = model.getLanguageToCompareName();
                g.drawString(nameToCompare, w/2, h - cell + 20);
            } else {
                int range = (w - 200)/10;
                for (int i = 0; i < numToCompare; i++) {
                    String nameToCompare = Model.getLanguageName(i);
                    g.drawString(nameToCompare, 100 + 20 + range * i, h - cell + 20);
                }
            }            
        } else if (showGraph) {
            
        } /*else {
            g.setColor(Color.black);
            g.drawRect(0, 0, this.getWidth(), this.getHeight());
        }*/

        gr.drawImage(bima, 0, 0, this);
    }
    
    public void printGraphic(double [] graphic) {
        this.showGraphic = true;
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
        this.repaint();
    }
    
    // Auxiliar method to round doubles by the number of decimals defined by 'places'
    private Double roundDouble(double vb, int places) {
        long factor = (long) Math.pow(10, places);
        vb = vb * factor;
        long tmp = Math.round(vb);
        return (double) tmp / factor;
    }
}
