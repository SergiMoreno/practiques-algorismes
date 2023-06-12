package practica7;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import mesurament.Mesurament;
import practica7.controller.Controller;
import practica7.model.Model;
import practica7.view.View;

/**
 *
 * @author usuario
 */
/*
* Video :
*/
public class Main implements EventListener {
    // components MVC centralitzat
    private Model model;
    private View view;
    private Controller controller;
    
    public static String mesuramentRatio;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream originalOut = System.out;
        System.setOut(ps);
        
        Mesurament.mesura();
        
        String consoleOutput = baos.toString();
        //mesuramentRatio = consoleOutput.replaceAll("[A-Z:* \r\n]", "");
        mesuramentRatio = consoleOutput.replaceAll("[^0-9+.0-9+]", "");           

        System.setOut(originalOut);

        (new Main()).init();
    }
    
    private void init() {
        this.model = new Model(this);
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
    
    public Model getModel() {
        return this.model;
    }
}
