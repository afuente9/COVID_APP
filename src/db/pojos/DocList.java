package db.pojos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "DocList")
@XmlType(propOrder = { "docs"})
public class DocList {
	@XmlElement
	private List<Doctor> docs;

	
	
	public DocList() {
		super();
	}

	public DocList(List<Doctor> docs) {
		super();
		this.docs = docs;
	}

	public List<Doctor> getDocs() {
		return docs;
	}

	public void setDocs(List<Doctor> docs) {
		this.docs = docs;
	}
	
	

}
