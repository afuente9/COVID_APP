package db.xml.dtd;

import java.io.*;
import javax.persistence.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import db.pojos.*;

public class JavaToXml {
	private static EntityManager em;
	
	/*TODO check for removing
	 * private static void printAdministrations() {
		Query q1 = em.createNativeQuery("SELECT * FROM administration", Administration.class);
		List<Administration> global = (List<Administration>) q1.getResultList();
		for (Administration admin : global) {
			System.out.println(admin);
		}
	}
	
	private static void printLabs() {
		Query q1 = em.createNativeQuery("SELECT * FROM lab", Lab.class);
		List<Lab> general = (List<Lab>) q1.getResultList();
		for (Lab labo : general) {
			System.out.println(labo);
		}
	}*/
	
	private static void generateEntityManager() {
		em = Persistence.createEntityManagerFactory("user-login").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
	}
	
	public void getXMLforLab(int id) {
		generateEntityManager();
		try {
			JAXBContext jaxbContextLab = JAXBContext.newInstance(Lab.class);
			Marshaller lab_marshaller = jaxbContextLab.createMarshaller();
			lab_marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
			Query q2 = em.createNativeQuery("SELECT * FROM lab WHERE id = ?", Lab.class);
			q2.setParameter(1, id);
			Lab result_lab= (Lab) q2.getSingleResult();
			File file = new File("./xmls/"+result_lab.getName()+ ".xml");
			lab_marshaller.marshal(result_lab, file);
			lab_marshaller.marshal(result_lab, System.out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getXMLforAdministration(int id) {
		generateEntityManager();
		try {
		JAXBContext jaxbContextAdmin = JAXBContext.newInstance(Administration.class);	
		Marshaller admin_marshaller = jaxbContextAdmin.createMarshaller();
		admin_marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		Query q2 = em.createNativeQuery("SELECT * FROM administration WHERE id = ?", Lab.class);
		q2.setParameter(1, id);
		Lab result_admin= (Lab) q2.getSingleResult();
		File file = new File("./xmls/"+result_admin.getName()+ ".xml");
		admin_marshaller.marshal(result_admin, file);
		admin_marshaller.marshal(result_admin, System.out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
