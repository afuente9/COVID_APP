package db.xml.dtd;

import java.io.File;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XmlToHtml {
	
	public static void createHTML(String filePath, String stylePath,String htmlSavePath) {
		TransformerFactory changeFact = TransformerFactory.newInstance();
		try {
			Transformer changer = changeFact.newTransformer(new StreamSource(new File(stylePath)));
			changer.transform(new StreamSource(new File(filePath)),new StreamResult(new File(htmlSavePath)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
