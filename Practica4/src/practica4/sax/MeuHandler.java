/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    private boolean enTipo;
    private boolean enGrafo;
    private boolean enNodo;
    private boolean enArista;

    public MeuHandler(Main p) {
        super();
        prog = p;
        enTipo = enGrafo = enNodo = enArista = false;
    }

    public void startElement(String uri, String localName,
            String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase(Tags.grafo)) {
            enGrafo = true;
        } else if (qName.equalsIgnoreCase(Tags.nodo)) {
            enNodo = true;
        } else if (qName.equalsIgnoreCase(Tags.arista)) {
            enArista = true;
        } else if (qName.equalsIgnoreCase(Tags.tipo)) {
            enTipo = true;
        }
    }

    @Override
    public void endElement(String uri, String localName,
            String qName) throws SAXException {
        if (qName.equalsIgnoreCase(Tags.grafo)) {
            enGrafo = false;
        } else if (qName.equalsIgnoreCase(Tags.nodo)) {
            enNodo = false;
        } else if (qName.equalsIgnoreCase(Tags.arista)) {
            enArista = false;
        } else if (qName.equalsIgnoreCase(Tags.tipo)) {
            enTipo = false;
        }
    }

    @Override
    public void characters(char ch[], int start, int length)
            throws SAXException {
        Model dat = prog.getModel();
        String s[];
        String value = new String(ch, start, length).trim();
        if (enNodo) {
            s = value.split(":");
            //dat.ponNodo(s[0], Integer.parseInt(s[1]), Integer.parseInt(s[2]));
        } else if (enArista) {
            s = value.split(":");
            //dat.ponArista(s[0], s[1], Double.parseDouble(s[2]));
        } else if (enTipo) {
            if (value.equalsIgnoreCase("dirigido")) {
                //dat.ponTipo("dirigido");
            } else if (value.equalsIgnoreCase("nodirigido")) {
                //dat.ponTipo("nodirigido");
            }
        }
    }
}
