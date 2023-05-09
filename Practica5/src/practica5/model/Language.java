package practica5.model;

/**
 *
 * @author usuario
 */
public class Language {
    private String dicFile;
    //private ArrayList<String> words;
    private String currentWord;
    
    public Language(String fileName) {
        this.dicFile = "src/resources/" + fileName;
        this.currentWord = null;
    }
    
    public void setWord(String w) {
        this.currentWord = w;
    }
    
    public String getCurrentWord() {
        return this.currentWord;
    }
    /*public Language(String fileName) {
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
    }*/
}
