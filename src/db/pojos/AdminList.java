package db.pojos;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "AdminList")
@XmlType(propOrder = { "governs"})
public class AdminList {
	@XmlElement
	private List<Administration> governs;
	
	
	
	public AdminList() {
		super();
	}

	public AdminList(List<Administration> governs) {
		super();
		this.governs = governs;
	}

	public List<Administration> getGoverns() {
		return governs;
	}

	public void setGoverns(List<Administration> governs) {
		this.governs = governs;
	}
	
	
	
}
