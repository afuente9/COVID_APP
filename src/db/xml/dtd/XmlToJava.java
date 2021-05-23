package db.xml.dtd;

import java.io.File;
import javax.xml.bind.*;

import db.interfaces.Cov_Manager;
import db.jdbc.JDBCManagment;
import db.pojos.*;


public class XmlToJava {
	
	private static Cov_Manager inter = new JDBCManagment();
	public void getLabFromXml(String fileName) {
		try {
			inter.connectWithNoPrint();
		JAXBContext jaxbContext_lab = JAXBContext.newInstance(Lab.class);
		Unmarshaller lab_unmarshaller = jaxbContext_lab.createUnmarshaller();
		File file = new File("./xmlFileSave/"+fileName+".xml");
		Lab labo = (Lab) lab_unmarshaller.unmarshal(file);	
		if(!inter.checkLab(labo)) {
			inter.addLab(labo);
		}
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getAdminFromXml(String fileName) {
		try {
			inter.connectWithNoPrint();
		JAXBContext jaxbContext_admin = JAXBContext.newInstance(Lab.class);
		Unmarshaller admin_unmarshaller = jaxbContext_admin.createUnmarshaller();
		File file = new File("./xmlFileSave/"+fileName+".xml");
		Administration govern = (Administration) admin_unmarshaller.unmarshal(file);
		if(!inter.checkAdmin(govern)) {
			inter.addGoverment(govern);
		}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	}