package practica4;

import mesurament.Mesurament;
import practica4.controller.Controller;
import practica4.model.Model;
import practica4.sax.MeuSax;
import practica4.view.View;

/**
 *
 * @author usuario
 */
/*
* Video : https://www.youtube.com/watch?v=teEllaK_EnQ
*/
public class Main implements EventListener {
    // components MVC centralitzat
    private Model model;
    private View view;
    private Controller controller;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Mesurament.mesura();
        (new Main()).init();
    }
    
    private void init() {
        this.model = new Model(this);
        MeuSax sax = new MeuSax(Map.getFile(Map.PITIUSES), this);
        sax.llegir();
        this.controller = new Controller(this);
        this.view = new View(this);
    }

    @Override
    public void notify(Event e) {
        switch (e.getEventType()){
            case Model -> {
                model.notify(e);
            }
            case View -> {
                view.notify(e);
            }
            case Controller -> {
                controller.notify(e);
            }
        }
    }
    
    public void updateMap(String m) {
        this.model.resetMap();
        MeuSax sax = new MeuSax(m, this);
        sax.llegir();
    }
    
    public Model getModel() {
        return this.model;
    }
}
