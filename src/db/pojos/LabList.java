package db.pojos;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "LabList")
@XmlType(propOrder = { "labs"})
public class LabList implements Serializable{
	
	@XmlElement
	private List<Lab> labs;

	
	public LabList(List<Lab> labs) {
		super();
		this.labs = labs;
	}

	public LabList() {
		super();
	}

	public List<Lab> getLabs() {
		return labs;
	}

	public void setLabs(List<Lab> labs) {
		this.labs = labs;
	}
	

}
