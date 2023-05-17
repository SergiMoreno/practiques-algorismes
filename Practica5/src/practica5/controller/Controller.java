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
    
    private Thread executionThread;
    
    public Controller(Main main) {
        this.main = main;
    }
    
    @Override
    public void run() {
        /* Levenshtein distance */
        Model model = this.main.getModel();
        
        if (model.compareAll() && model.compareWithAll()) {
            int nFiles = Model.getNLanguages();
            double [][] results = new double[nFiles][nFiles];
            for (int i = 0; i < nFiles-1; i++) {
                for (int j = i+1; j < nFiles; j++) {
                    double result1 = calculateDistance(model.getLanguageCompared(i), model.getLanguageToCompare(j));
                    //System.out.println("RESULT 1 : " + result1);
                    double result2 = calculateDistance(model.getLanguageToCompare(j), model.getLanguageCompared(i));
                    //System.out.println("RESULT 2 : " + result2);

                    double val = Math.sqrt(result1 * result1 + result2 * result2);
                    results[i][j] = val;
                    results[j][i] = val;
                    System.out.println(Model.getLanguageName(i) + " : " + Model.getLanguageName(j) + " = " + results[i][j]);
                }
            }
            this.main.notify(new ViewEvent(results));
        } else if (model.compareWithAll()) {
            int nFiles = Model.getNLanguages();
            double [] results = new double[nFiles-1];
            for (int i = 0; i < nFiles; i++) {
                if (model.isSameLanguage(i)) continue;
                double result1 = calculateDistance(model.getLanguageCompared(), model.getLanguageToCompare(i));
                //System.out.println("RESULT 1 : " + result1);
                double result2 = calculateDistance(model.getLanguageToCompare(i), model.getLanguageCompared());
                //System.out.println("RESULT 2 : " + result2);
                
                results[i] = Math.sqrt(result1 * result1 + result2 * result2);
                System.out.println(model.getLanguageCompared() + " : " + Model.getLanguageName(i) + " = " + results[i]);
                this.main.notify(new ViewEvent(results));
            }
        } else {
            double [] results = new double[1];
            double result1 = calculateDistance(model.getLanguageCompared(), model.getLanguageToCompare());
            //System.out.println("RESULT 1 : " + result1);
            double result2 = calculateDistance(model.getLanguageToCompare(), model.getLanguageCompared());
            //System.out.println("RESULT 2 : " + result2);
            results[0] = Math.sqrt(result1 * result1 + result2 * result2);
            System.out.println(model.getLanguageCompared() + " : " + model.getLanguageToCompare() + " = " + results[0]);
            this.main.notify(new ViewEvent(results));
        }
    }
    
    private double calculateDistance(String f1, String f2) {
        double acc1 = 0;
        FileReader dic1 = new FileReader(f1);
        while (dic1.hasNextLine()) {
            String word1 = dic1.nextLine();
            int minDistance = Integer.MAX_VALUE;
            FileReader dic2 = new FileReader(f2);
            while (dic2.hasNextLine()) {
                String word2 = dic2.nextLine();
                int d = levenshteinDistance(word1, word2);
                if (d < minDistance) {
                    minDistance = d;
                }
            }
            dic2.close();
            double div = (double) minDistance / word1.length();
            acc1 += div;
        }
        dic1.close();
        return (double) acc1 / dic1.getNWords();
    }
    
    private int levenshteinDistance(String w1, String w2) {
        int [][] solMatrix = new int[w1.length()+1][w2.length()+1];
        
        for (int j = 0; j < solMatrix[0].length; j++) {
            solMatrix[0][j] = j;
        }
        
        for (int i = 0; i < solMatrix.length; i++) {
            solMatrix[i][0] = i;
        }
        
        /*  */
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
