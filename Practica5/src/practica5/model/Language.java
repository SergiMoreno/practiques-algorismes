package practica5.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Language {
    private String dicFile;
    private ArrayList<String> words;
    
    public Language(String fileName) {
        this.dicFile = "src/resources/" + fileName;
        this.words = new ArrayList<String>();
        
        try {
            File dic = new File(this.dicFile);
            Scanner reader = new Scanner(dic);
            while (reader.hasNextLine()) {
              String data = reader.nextLine();
              words.add(data);
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Language.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
