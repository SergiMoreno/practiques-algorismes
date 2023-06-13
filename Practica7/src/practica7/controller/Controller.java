package practica7.controller;

import java.math.BigInteger;
import practica7.Operation;
import java.time.Duration;
import java.util.Random;
import practica7.Event;
import practica7.EventListener;
import practica7.Main;
import practica7.model.Model;

/**
 *
 * @author usuario
 */
public class Controller extends Thread implements EventListener {
    private Main main;
    private Model model;
    private Thread executionThread;
    
    private Operation operation;
    
    public Controller(Main main) {
        this.main = main;
    }
    
    @Override
    public void run() {
        model = this.main.getModel();

        switch (this.operation) {
            case CHECK_PRIMALITY -> {
                this.checkPrimality(model.getNumber());
            }
            case CALCULATE_FACTOR -> {
                this.calculateFactor(model.getNumber());
            }
        }
        
        /*  */
        try {
            Thread.sleep(Duration.ZERO);

        } catch (InterruptedException ex) {
            System.out.println("Execution Stopped.");
        }
    }
    
    private void checkPrimality(BigInteger n) {
        Primality primprob = new Primality(100);
        System.out.println("El número " + n + " es primo es una aseveración "
                + primprob.esPrimo(n));
    }
    
    private void calculateFactor(BigInteger n) {
        Factor f = new Factor();
        f.factorizar(n);
    }
    
    @Override
    public void notify(Event e) {
        ControllerEvent event = (ControllerEvent) e;
        
        switch (event.type) {
            case START -> {
                this.operation = event.operation;
                // When start event notified, new Thread is initialized
                this.executionThread = new Thread(this);
                this.executionThread.start();
            }
            case STOP -> {
                if (this.executionThread != null) {
                    this.executionThread.interrupt();
                }
            }
        }
    }    
}
