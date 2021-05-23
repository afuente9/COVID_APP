package db.xml.dtd;

import java.io.File;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.xml.bind.*;

import db.interfaces.Cov_Manager;
import db.jdbc.JDBCManagment;
import db.pojos.*;


public class XmlToJava {
	
	private static Cov_Manager inter = new JDBCManagment();
	public void getLabFromXml(String fileName) {
		try {
		JAXBContext jaxbContext_lab = JAXBContext.newInstance(Lab.class);
		Unmarshaller lab_unmarshaller = jaxbContext_lab.createUnmarshaller();
		File file = new File("./xmls/"+fileName+".xml");
		Lab labo = (Lab) lab_unmarshaller.unmarshal(file);	
		List<Patient> pats = labo.getPatients();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getAdminFromXml(String fileName) {
		try {
		JAXBContext jaxbContext_admin = JAXBContext.newInstance(Lab.class);
		Unmarshaller admin_unmarshaller = jaxbContext_admin.createUnmarshaller();
		File file = new File("./xmls/"+fileName+".xml");
		Administration govern = (Administration) admin_unmarshaller.unmarshal(file);
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		EntityTransaction tx1 = em.getTransaction();
		tx1.begin();
		if (inter.checkAdmin(govern) == false) {
			em.persist(govern);
		}
		tx1.commit();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	}