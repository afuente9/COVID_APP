package db.xml.utils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import db.pojos.*;

public class SexAdapter  extends XmlAdapter<String, Sex>{

	@Override
	public String marshal(Sex arg0) throws Exception {
		String sexo;
		if (arg0.equals(Sex.Male)) {
			sexo = "M";
		} else {
			sexo = "F";
		}
		return sexo;
	}

	@Override
	public Sex unmarshal(String arg0) throws Exception {
		Sex sexo;
		if (arg0.equalsIgnoreCase("m")) {
			sexo = Sex.Male;
		} else {
			sexo = Sex.Female;
		}
		return sexo;
	}

}
