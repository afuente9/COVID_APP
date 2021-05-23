package db.xml.dtd;

import java.io.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import db.interfaces.Cov_Manager;
import db.jdbc.JDBCManagment;
import db.pojos.*;

public class JavaToXml {
	
	private static Cov_Manager inter = new JDBCManagment();

	public void getXMLforLab(int id) {
		try {
			inter.connectWithNoPrint();
			JAXBContext jaxbContextLab = JAXBContext.newInstance(Lab.class);
			Marshaller lab_marshaller = jaxbContextLab.createMarshaller();
			lab_marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
			
			Lab result_lab= inter.getLab(id);
			File file = new File("./xmlFileSave/"+result_lab.getName()+ ".xml"); //TODO mirar porque no funciona esto
			file.createNewFile();
			lab_marshaller.marshal(result_lab, file);
			lab_marshaller.marshal(result_lab, System.out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getXMLforAdministration(int id) {
		try {
			inter.connectWithNoPrint();
			JAXBContext jaxbContextAdmin = JAXBContext.newInstance(Administration.class);
			Marshaller admin_marshaller = jaxbContextAdmin.createMarshaller();
			admin_marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			Administration result_admin = inter.getAdministration(id);
			File file = new File("./xmlFileSave/" + result_admin.getName() + ".xml"); //TODO mirar porque no funciona esto
			file.createNewFile();
			admin_marshaller.marshal(result_admin, file);
			admin_marshaller.marshal(result_admin, System.out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
