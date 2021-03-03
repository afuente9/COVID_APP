package db.pojos;

public enum Sex {
	Male, Female;
	
	public static Sex parse(String dato) {
		if(dato.equalsIgnoreCase("male")) {
			return Sex.Male;
		}
		else {
			return Sex.Female;
		}
	}
}