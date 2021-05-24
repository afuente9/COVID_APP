package db.xml.dtd;

import java.io.*;
import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import db.interfaces.Cov_Manager;
import db.jdbc.JDBCManagment;
import db.pojos.*;

public class JavaToXml {
	
	private static Cov_Manager inter = new JDBCManagment();

	public void getXMLforLab() {
		try {
			inter.connectWithNoPrint();
			JAXBContext jaxbContextLab = JAXBContext.newInstance(Lab.class);
			Marshaller lab_marshaller = jaxbContextLab.createMarshaller();
			lab_marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
			File file = new File("./xmlFileSave/allLabs.xml"); 
			file.createNewFile();
			List<Lab> labos=inter.showLabs();
			lab_marshaller.marshal(labos, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getXMLforAdministration() {
		try {
			inter.connectWithNoPrint();
			JAXBContext jaxbContextAdmin = JAXBContext.newInstance(Administration.class);
			Marshaller admin_marshaller = jaxbContextAdmin.createMarshaller();
			admin_marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			File file = new File("./xmlFileSave/allAdmins.xml"); 
			file.createNewFile();
			for (int id =1; id<inter.getAllAdmins().size();id++) {
				Administration result_admin = inter.getAdministration(id);
				admin_marshaller.marshal(result_admin, file);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getXMLforDoc() {
		try {
			inter.connectWithNoPrint();
			JAXBContext jaxbContextDoc = JAXBContext.newInstance(Doctor.class);
			Marshaller doc_marshaller = jaxbContextDoc.createMarshaller();
			doc_marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			File file = new File("./xmlFileSave/allDocs.xml"); 
			file.createNewFile();
			for (int id =1; id<=inter.getAllDoctors().size();id++) {
				Doctor result_doc = inter.getDoctor(id);
				doc_marshaller.marshal(result_doc, file);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
