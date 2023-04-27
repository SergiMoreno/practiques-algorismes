package practica4.sax;

import practica4.Main;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 *
 * @author mascport
 */
public class MeuSax {

    private String fic;
    private Main prog;

    public MeuSax(String f, Main p) {
        fic = f;
        prog = p;
    }

    public void llegir() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            InputStream xmlInput
                    = new FileInputStream(fic);
            SAXParser saxParser = factory.newSAXParser();
            MeuHandler handler = new MeuHandler(prog);
            saxParser.parse(xmlInput, handler);
        } catch (Exception e) {
            //Errores.informaError(e);
        }
    }
}
