package practica2.view;

import java.awt.Color;
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
        int dim = model.getSize();
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
            }
        }
        String imageref;
        URL imageURL = null;
        for (int i = 0; i < model.getNumPieces(); i++) {
            for (int j = 0; j < model.getPieceRouteSize(i); j++) {
                int x = model.getPieceRouteNodeX(i, j);
                int y = model.getPieceRouteNodeY(i, j);
                imageref = model.getPieceImage(i);
                if (imageref != null) {
                    imageURL = getClass().getResource("../../resources/"+imageref);
                    g.drawImage((new ImageIcon(imageURL)).getImage(), x * ancho, y * alto, ancho, alto, this);
                }
            }
        }
        gr.drawImage(bima,0,0,this);
    }
    
    public void refresh() {
        this.repaint();
    }
}
