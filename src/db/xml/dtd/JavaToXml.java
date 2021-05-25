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
			JAXBContext jaxbContextLab = JAXBContext.newInstance(LabList.class);
			Marshaller lab_marshaller = jaxbContextLab.createMarshaller();
			lab_marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
			File file = new File("./xmlFileSave/allLabs.xml"); 
			file.createNewFile();
			LabList labos= new LabList(inter.getAllLabsXML());
			lab_marshaller.marshal(labos, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getXMLforAdministration() {
		try {
			inter.connectWithNoPrint();
			JAXBContext jaxbContextAdmin = JAXBContext.newInstance(AdminList.class);
			Marshaller admin_marshaller = jaxbContextAdmin.createMarshaller();
			admin_marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			File file = new File("./xmlFileSave/allAdmins.xml"); 
			file.createNewFile();
			AdminList governs = new AdminList(inter.getAllGovermentsXML());
			admin_marshaller.marshal(governs, file);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getXMLforDoc() {
		try {
			inter.connectWithNoPrint();
			JAXBContext jaxbContextDoc = JAXBContext.newInstance(DocList.class);
			Marshaller doc_marshaller = jaxbContextDoc.createMarshaller();
			doc_marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			File file = new File("./xmlFileSave/allDocs.xml"); 
			file.createNewFile();
			DocList doctors = new DocList(inter.getAllDoctorsXML());
			doc_marshaller.marshal(doctors, file);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
