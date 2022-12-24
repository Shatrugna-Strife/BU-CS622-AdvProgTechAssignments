package com.met622.singleton;

import com.met622.util.ParserUtil;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Singleton class to avoid recreation of objects
 */
public class Singleton {
    public final static ParserUtil parserUtil;

    static {
        try {
            parserUtil = new ParserUtil();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}
