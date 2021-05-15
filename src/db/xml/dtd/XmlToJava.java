package db.xml.dtd;

import java.io.File;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.xml.bind.*;
import db.pojos.*;


public class XmlToJava {
	
	private static final String PERSISTENCE_PROVIDER = "company-provider";
	private static EntityManagerFactory factory;
	public void getLabFromXml(String fileName) {
		try {
		JAXBContext jaxbContext_lab = JAXBContext.newInstance(Lab.class);
		Unmarshaller lab_unmarshaller = jaxbContext_lab.createUnmarshaller();
		File file = new File("./xmls/"+fileName+".xml");
		Lab labo = (Lab) lab_unmarshaller.unmarshal(file);		
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		EntityTransaction tx1 = em.getTransaction();
		tx1.begin();
		em.persist(labo);
		tx1.commit();

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
		em.persist(govern);
		tx1.commit();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*TODO mirar esto
		// Persist
		// We assume the authors are not already in the database
		// In a real world, we should check if they already exist
		// and update them instead of inserting as new
		for (Employee employee : emps) {
			em.persist(employee);
		}
		em.persist(report);
		
		// End transaction
		tx1.commit();*/
	}