package practica5.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JList;
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
    private BufferedImage bima;
    // Circle nodes to draw the distance graph
    private Node [] circles;
    private JList<String> list;
    private int nValues;
    
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
        int h = this.getHeight();
        int w = this.getWidth();
        if (bima == null) {
            if (this.getWidth() > 0) {
                bima = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
                Graphics2D  bima_graphics = bima.createGraphics();
                bima_graphics.setColor(new java.awt.Color(255, 255, 255));
                bima_graphics.fillRect(0, 0, bima.getWidth(), bima.getHeight());
            }
        }
        gr.drawImage(bima, 0, 0, this);
        
        if (showGraphic) {
            // Show graphic lines and percentage
            gr.setColor(Color.gray);
            int cell = (h - 60) / 11;
            double percentage = 0.0;
            int xLeftVertical = 100, xRightVertical = w - 100, nameWidth = 65;
            for (int i = 1; i < 12; i++) {
                gr.drawString(Double.toString(percentage), nameWidth, h - (cell * i) + 5);
                gr.drawLine(xLeftVertical, h - (cell * i), xRightVertical, h - (cell * i));
                percentage = roundDouble(percentage + 0.1, 1);
            }
            
            // Show distance columns
            String name = model.getLanguageComparedName();
            gr.drawString(name, w/2, h - (cell * 11) - (cell/2));
            int numToCompare = this.graphic.length;
            if (numToCompare == 1) {
                // Show 1 column
                String nameToCompare = model.getLanguageToCompareName();
                int xColumn = w/2, columnWidth = 50;
                gr.drawString(nameToCompare, xColumn, h - cell + 20);
                double v = this.graphic[0] * 10 + 1;
                int columnHeight = h - (int)(cell * v);
                gr.setColor(Color.BLUE);
                gr.fillRect(xColumn, columnHeight, columnWidth, (h - cell) - columnHeight);
            } else {
                // Show columns
                int xColumn = 120, columnWidth = 30;
                int range = (w - 200)/ numToCompare;
                boolean repeated = false;
                for (int i = 0; i < numToCompare+1; i++) {
                    String nameToCompare = Model.getLanguageName(i);
                    // Do not show the same language
                    if (nameToCompare.equals(model.getLanguageComparedName())) {
                        repeated = true;
                        continue;
                    }
                    
                    int xPos;
                    double value;
                    if (repeated) {
                        xPos = xColumn + range * (i-1);
                        value = this.graphic[i-1] * 10 + 1;
                    } else {
                        xPos = xColumn + range * i;
                        value = this.graphic[i] * 10 + 1;
                    }
    
                    gr.setColor(Color.gray);
                    
                    gr.drawString(nameToCompare.substring(0, 3), xPos + 4, h - cell + 20);
                    int columnHeight = h - (int)(cell * value);
                    gr.setColor(Color.BLUE);
                    gr.fillRect(xPos, columnHeight, columnWidth, (h - cell) - columnHeight);
                }
            }            
        } else if (showGraph) {
            // Show circles and language names
            int nFiles = Model.getNLanguages(), circleWidth = 75;
            int xCenter = 350, yCenter = 240;
            double degree = Math.PI + Math.PI / 2;
            double gradoCirculo = (Math.PI * 2)/nFiles;
            int radi = 230;
            this.circles = new Node[nFiles];
            // Create circles nodes with shorten string inside them
            for (int i = 0; i < nFiles; i++) {
                int cos = (int) (radi * Math.cos(degree + (i+1) * gradoCirculo));
                int sin = (int) (radi * Math.sin(degree + (i+1) * gradoCirculo));
                this.circles[i] = new Node(xCenter + cos, yCenter + sin);
                gr.setColor(Color.red);
                gr.fillOval(this.circles[i].x, 
                        this.circles[i].y, 
                        circleWidth, circleWidth);
                gr.setColor(Color.blue);
                
                int xString = this.circles[i].x, yString = this.circles[i].y;
                if (this.circles[i].x > xCenter) xString += circleWidth - 10;
                if (this.circles[i].y > yCenter) yString += circleWidth + 10;
                gr.drawString(Model.getLanguageName(i).substring(0, 3), 
                        xString, 
                        yString);
            }

            // Show lines distance between nodes
            int xFrom, yFrom, xTo, yTo;
            int num = 0;
            String [] listArray = new String[this.nValues];
            for (int i = 0; i < nFiles-1; i++) {
                xFrom = this.circles[i].x + circleWidth/2;
                yFrom = this.circles[i].y + circleWidth/2;
                for (int j = i+1; j < nFiles; j++) {
                    gr.setColor(Color.black);
                    xTo = this.circles[j].x + circleWidth/2;
                    yTo = this.circles[j].y + circleWidth/2;
                    
                    int xHalf = (xFrom + xTo) / 2;
                    int yHalf = (yFrom + yTo) / 2;
                    int x = (xFrom + xHalf) / 2;
                    int y = (yFrom + yHalf) / 2;
                    gr.drawLine(xFrom, yFrom, xTo, yTo);
                    gr.setColor(Color.blue);
                    gr.drawString("" + num, x-5, y+5);
                    listArray[num] = num
                            + ") "
                            + Model.getLanguageName(i).substring(0, 3) 
                            + " - " 
                            + Model.getLanguageName(j).substring(0, 3)
                            + " : "
                            + roundDouble(this.graph[i][j], 2);
                    num++;
                }
            }
            this.list.setListData(listArray);
            this.list.setVisible(true);
        }
    }
    
    public void setList(JList<String> list) {
        this.list = list;
        this.list.setVisible(false);
    }
    
    public void printGraphic(double [] graphic) {
        this.showGraphic = true;
        this.graphic = graphic.clone();
        this.repaint();
    }
    
    public void printGraph(double [][] graph, int nValues) {
        this.showGraph = true;
        this.nValues = nValues;
        this.graph = graph.clone();
        this.repaint();
    }
    
    public void reset() {
        this.showGraph = false;
        this.showGraphic = false;
        //this.list.removeAll();
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

class Node {
    public int x,y;
    
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
