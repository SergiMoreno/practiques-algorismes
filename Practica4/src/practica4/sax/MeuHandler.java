package practica4.sax;

import practica4.Main;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import practica4.model.Model;

/**
 *
 * @author mascport
 */
public class MeuHandler extends DefaultHandler {

    private Main prog;
    private boolean inType;
    private boolean inMap;
    private boolean inPoblation;
    private boolean inRoute;

    public MeuHandler(Main p) {
        super();
        prog = p;
        inType = inMap = inPoblation = inRoute = false;
    }

    public void startElement(String uri, String localName,
            String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase(Tags.map)) {
            inMap = true;
        } else if (qName.equalsIgnoreCase(Tags.poblation)) {
            inPoblation = true;
        } else if (qName.equalsIgnoreCase(Tags.route)) {
            inRoute = true;
        } else if (qName.equalsIgnoreCase(Tags.type)) {
            inType = true;
        }
    }

    @Override
    public void endElement(String uri, String localName,
            String qName) throws SAXException {
        if (qName.equalsIgnoreCase(Tags.map)) {
            inMap = false;
        } else if (qName.equalsIgnoreCase(Tags.poblation)) {
            inPoblation = false;
        } else if (qName.equalsIgnoreCase(Tags.route)) {
            inRoute = false;
        } else if (qName.equalsIgnoreCase(Tags.type)) {
            inType = false;
        }
    }

    @Override
    public void characters(char ch[], int start, int length)
            throws SAXException {
        Model model = prog.getModel();
        String s[];
        String value = new String(ch, start, length).trim();
        if (inPoblation) {
            s = value.split(":");
            model.addPoblation(s[0]);
        } else if (inRoute) {
            s = value.split(":");
            model.addRoute(s[0], s[1],Double.parseDouble(s[2]));
        } else if (inType) {
            if (value.equalsIgnoreCase("dirigido")) {
                model.setType("dirigido");
            } else if (value.equalsIgnoreCase("nodirigido")) {
                model.setType("nodirigido");
            }
        }
    }
}
