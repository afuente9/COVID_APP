package db.xml.utils;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class CustomErrorHandler implements ErrorHandler {

    Boolean errorProduced = Boolean.FALSE;

    public Boolean isValid() {
        return !this.errorProduced;
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        errorProduced = Boolean.TRUE;
        System.out.println("Validation error:");
        System.out.println("\t" + exception.getSystemId());
        System.out.println("\tLine " + exception.getLineNumber());
        System.out.println("\t" + exception.getMessage());
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        errorProduced = Boolean.TRUE;
        errorProduced = Boolean.TRUE;
        System.out.println("Well-formedness error:");
        System.out.println("\t" + exception.getSystemId());
        System.out.println("\tLine " + exception.getLineNumber());
        System.out.println("\t" + exception.getMessage());
    }

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        errorProduced = Boolean.TRUE;
        errorProduced = Boolean.TRUE;
        System.out.println("Warning:");
        System.out.println("\t" + exception.getSystemId());
        System.out.println("\tLine " + exception.getLineNumber());
        System.out.println("\t" + exception.getMessage());
    }
}
