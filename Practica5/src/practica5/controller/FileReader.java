package practica5.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class FileReader {
    private Scanner reader;
    private int nWords;
    
    public FileReader(String filePath) {
        try {
            File dic = new File(filePath);
            this.reader = new Scanner(dic);
            this.nWords = 0;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean hasNextLine() {
        return reader.hasNextLine();
    }
    
    public String nextLine() {
        this.nWords++;
        return reader.nextLine();
    }
    
    public int getNWords() {
        return this.nWords;
    }
    
    public void close() {
        this.reader.close();
    }
}
