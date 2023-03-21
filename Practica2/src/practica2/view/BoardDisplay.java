package practica2.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import practica2.model.Model;
import practica2.pieces.Piece;

/**
 *
 * @author usuario
 */

public class BoardDisplay extends JPanel {
    final private Model model;
    
    public BoardDisplay(Model model) {
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
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        //int dim = dat.getDimension();
        int dim = model.getBoardSize();
        //calculamos el ancho y alto de la casilla
        int ancho = this.getWidth() / dim;
        int alto = this.getHeight() / dim;
        //Pintamos el tablero
        g.setColor(new Color(0, 0, 0));
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if ((i % 2) == 0) { //la fila empieza por blanco
                    if ((j % 2) == 1) {
                        g.fillRect(j * ancho, i * alto, ancho, alto);
                    }
                } else // la fila empieza por negro
                {
                    if ((j % 2) == 0) {
                        g.fillRect(j * ancho, i * alto, ancho, alto);
                    }
                }
                
                // Paint piece and movement
                URL imageURL = null;
                String pieceImage = model.getCellImage(j, i);
                if (pieceImage != null) {
                    imageURL = getClass().getResource("../../resources/"+pieceImage);
                    g.drawImage((new ImageIcon(imageURL)).getImage(), j * ancho, i * alto, ancho, alto, this);
                }
                
                int movement = model.getCellMovement(j, i);
                if (movement != -1) {
                    int x = j * ancho + (ancho - g.getFontMetrics().stringWidth(Integer.toString(movement))) / 2;
                    int y = i * ancho + ((ancho - g.getFontMetrics().getHeight()) / 2) + g.getFontMetrics().getAscent();
                    /**/
                    int pieceIndex = model.getCellPiece(j, i);
                    if (pieceIndex == 0) g.setColor(new Color(255, 0, 236));
                    else g.setColor(new Color(12, 255, 0));
                    /**/
                    g.setFont(new Font("Arial", Font.BOLD, 20));
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
