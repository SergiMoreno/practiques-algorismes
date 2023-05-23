package practica5.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Language {
    private String name, dicFile;
    private String [] words;
    
    public Language(String name, String fileName) {
        try {
            this.name = name;
            this.dicFile = "src/resources/" + fileName;
            File dic = new File(this.dicFile);
            Scanner reader = new Scanner(dic);
            
            ArrayList<String> initWords = new ArrayList<String>();
            while (reader.hasNextLine()) {
                initWords.add(reader.nextLine());
            }
            reader.close();
            
            this.words = new String[initWords.size()];
            this.words = initWords.toArray(this.words);
            //Arrays.sort(words);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Language.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public String getName() {
        return this.name;
    }
    
    public String getPath() {
        return this.dicFile;
    }
    
    public int getLength() {
        return this.words.length;
    }
    
    public String getWord(int i) {
        return this.words[i];
    }
}
