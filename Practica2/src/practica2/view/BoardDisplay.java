package practica2.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import practica2.model.Model;

/**
 *
 * @author usuario
 */

public class BoardDisplay extends JPanel {
    final private Model model;
    final private View view;
    
    public BoardDisplay(Model model, View view) {
        this.model = model;
        this.selectedPieces = new ArrayList<PieceDisplay>();
    }
    
    public void addPieceDisplay(String imageRef, int x, int y) {
        this.selectedPieces.add(new PieceDisplay(imageRef, x, y));
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
        //colocamos las piezas
        for (int i = 0; i < this.selectedPieces.size(); i++) {
            PieceDisplay pieced = this.selectedPieces.get(i);
            URL imageURL = getClass().getResource("../../resources/"+pieced.name);
            g.drawImage((new ImageIcon(imageURL)).getImage(), pieced.x * ancho, pieced.y * alto, ancho, alto, this);
        }
        gr.drawImage(bima,0,0,this);
    }
    
    public void refresh() {
        this.repaint();
    }
}
