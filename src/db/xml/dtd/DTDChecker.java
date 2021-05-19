package db.xml.dtd;

import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import db.xml.utils.CustomErrorHandler;

public class DTDChecker {
	
	public static boolean checkDTDdoc(String fileName) {
		 File file = new File("./xmls/"+fileName+".xml"); 
	        try {
	            DocumentBuilderFactory docBuild = DocumentBuilderFactory.newInstance();
	            // Set it up so it validates XML documents
	            docBuild.setValidating(true);
	            // Create a DocumentBuilder and an ErrorHandler (to check validity)
	            DocumentBuilder builder = docBuild.newDocumentBuilder();
	            CustomErrorHandler customErrorHandler = new CustomErrorHandler();
	            builder.setErrorHandler(customErrorHandler);
	            // Parse the XML file and print out the result
	            Document doc = builder.parse(file);
	            if (customErrorHandler.isValid()) {
	                System.out.println(file + " was valid!");
	            }
	        } catch (ParserConfigurationException ex) {
	            System.out.println(file + " error while parsing!");
	        } catch (SAXException ex) {
	            System.out.println(file + " was not well-formed!");
	        } catch (IOException ex) {
	            System.out.println(file + " was not accesible!");
	        }
		return false;
		
	}

}
