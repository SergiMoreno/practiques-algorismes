package practica2.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import practica2.model.Model;

/**
 *
 * @author usuario
 */

public class BoardDisplay extends JPanel {
    final private Model model;
    
    HashMap<Integer, Color> pieceColor;
    
    public BoardDisplay(Model model) {
        this.model = model;
        this.pieceColor = new HashMap<Integer, Color>();
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
        int dim = model.getBoardSize();
        // calculate cell weight and height
        int ancho = this.getWidth() / dim;
        int alto = this.getHeight() / dim;
        // paint board
        g.setColor(new Color(0, 0, 0));
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if ((i % 2) == 0) {
                    if ((j % 2) == 1) {
                        g.fillRect(j * ancho, i * alto, ancho, alto);
                    }
                } else
                {
                    if ((j % 2) == 0) {
                        g.fillRect(j * ancho, i * alto, ancho, alto);
                    }
                }
                
                // Paint piece and movement
                if (this.model.isOccupied(j, i)) {
                    String pieceImage = model.getCellImage(j, i);
                    URL imageURL = getClass().getResource("../../resources/"+pieceImage);
                    g.drawImage((new ImageIcon(imageURL)).getImage(), j * ancho, i * alto, ancho, alto, this);
                    int movement = model.getCellMovement(j, i);
                    int x = j * ancho + (ancho - g.getFontMetrics().stringWidth(Integer.toString(movement))) / 2;
                    int y = i * ancho + ((ancho - g.getFontMetrics().getHeight()) / 2) + g.getFontMetrics().getAscent();
                    
                
                    int pieceIndex = model.getCellPiece(j, i);
                    
                    if (this.pieceColor.containsKey(pieceIndex)) {
                        g.setColor(this.pieceColor.get(pieceIndex));
                    } else {
                        Random rnd = new Random();
                        int r = rnd.nextInt(256);
                        int gColor = rnd.nextInt(256);
                        int b = rnd.nextInt(256);
                        Color color = new Color(r, gColor, b);
                        this.pieceColor.put(pieceIndex, color);
                        g.setColor(color);
                    } 
                                   
                    int fontSize = (20 * 8) / model.getBoardSize();
                    g.setFont(new Font("Arial", Font.BOLD, fontSize));
                    g.drawString(Integer.toString(movement), x, y);
                    g.setColor(new Color(0, 0, 0));         
                }
            }
        }
        gr.drawImage(bima,0,0,this);
    }
    
    public void refresh() {
        this.repaint();
    }
}
