package practica5.controller;

import practica5.Event;
import practica5.EventListener;
import practica5.Main;
import practica5.model.Model;
import practica5.view.ViewEvent;

/**
 *
 * @author usuario
 */
public class Controller extends Thread implements EventListener {
    private Main main;
    private Model model;
    private Thread executionThread;
    
    public Controller(Main main) {
        this.main = main;
    }
    
    @Override
    public void run() {
        /* Levenshtein distance */
        this.model = this.main.getModel();
        
        if (model.compareAll() && model.compareWithAll()) {
            int nFiles = model.getNLanguages(), nValues = 0;
            double [][] results = new double[nFiles][nFiles];
            long time = System.nanoTime();
            for (int i = 0; i < nFiles-1; i++) {
                for (int j = i+1; j < nFiles; j++) {
                    double result1 = calculateDistance(i, j);
                    double result2 = calculateDistance(j, i);
                    double val = Math.sqrt(result1 * result1 + result2 * result2);
                    results[i][j] = val;
                    results[j][i] = val;
                    nValues++;
                }
            }
            time = System.nanoTime() - time;
            System.out.println("Time : " + time);
            this.main.notify(new ViewEvent(results, nValues));
        } else if (model.compareWithAll()) {
            int nFiles = model.getNLanguages();
            // Keep -1 values
            double [] results = new double[nFiles-1];
            boolean repeated = false;
            for (int i = 0; i < nFiles; i++) {
                if (model.isSameLanguage(i)) {
                    repeated = true;
                    continue;
                }
                double result1 = calculateDistance(model.getLanguageCompared(), i);
                double result2 = calculateDistance(i, model.getLanguageCompared());
                if (repeated) {
                    results[i-1] = Math.sqrt(result1 * result1 + result2 * result2);
                } else {
                    results[i] = Math.sqrt(result1 * result1 + result2 * result2);
                }
            }
            this.main.notify(new ViewEvent(results));
        } else {
            double [] results = new double[1];
            double result1 = calculateDistance(model.getLanguageCompared(), model.getLanguageToCompare());
            double result2 = calculateDistance(model.getLanguageToCompare(), model.getLanguageCompared());
            results[0] = Math.sqrt(result1 * result1 + result2 * result2);
            this.main.notify(new ViewEvent(results));
        }
    }
    
    private double calculateDistance(int d1, int d2) {
        double acc1 = 0;
        int lengthD1 = this.model.getLanguageLength(d1);
        int lengthD2 = this.model.getLanguageLength(d2);
        for (int i = 0; i < lengthD1; i++) {
            String word1 = this.model.getLanguageWord(d1, i);
            int minDistance = Integer.MAX_VALUE;
            for (int j = 0; j < lengthD2; j++) {
                String word2 = this.model.getLanguageWord(d2, j);
                int d = levenshteinDistance(word1, word2);
                if (d < minDistance) {
                    minDistance = d;
                    if (minDistance == 0) break;
                }
            }
            double div = (double) minDistance / word1.length();
            acc1 += div;
        }
        return (double) acc1 / lengthD1;
    }
    
    private int levenshteinDistance(String w1, String w2) {
        int [][] solMatrix = new int[w1.length()+1][w2.length()+1];
        
        for (int j = 0; j < solMatrix[0].length; j++) {
            solMatrix[0][j] = j;
        }
        
        for (int i = 0; i < solMatrix.length; i++) {
            solMatrix[i][0] = i;
        }
        
        /* Calculate the Levenshtein distance, it uses the operation with less cost */
        for (int i = 1; i < solMatrix.length; i++) {
            for (int j = 1; j < solMatrix[i].length; j++) {
                char lastChar1 = w1.charAt(i-1);
                char lastChar2 = w2.charAt(j-1);
                int cost, delete, insert, replace;
                if (lastChar1 == lastChar2) cost = 0;
                else cost = 1;
                // delete
                delete = solMatrix[i-1][j] + 1;
                // insert
                insert = solMatrix[i][j-1] + 1;
                // replace
                replace = solMatrix[i-1][j-1] + cost;
                // d[i,j] 
                int a = Math.min(delete, insert);
                solMatrix[i][j] = Math.min(a, replace);
            }
        }
        
        return solMatrix[w1.length()][w2.length()];
    }

    @Override
    public void notify(Event e) {
        ControllerEvent event = (ControllerEvent) e;
        
        switch (event.type) {
            case START:
                // When start event notified, new Thread is initialized
                this.executionThread = new Thread(this);
                this.executionThread.start();
                break;
            case STOP:
                if (this.executionThread != null) {
                    this.executionThread.interrupt();
                }
                break;
        }
    }    
}
