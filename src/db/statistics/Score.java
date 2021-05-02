package db.statistics;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import db.GUI.Main;
import db.pojos.Day;
import db.pojos.Doctor;
import db.pojos.Patient;
import db.pojos.Sex;
import javafx.collections.FXCollections;
import javafx.scene.chart.PieChart;

public class Score {
	public boolean firstTime=true;
	
/* DE MOMENTO NO USO ESTO
public void calculateValues() {
	int score=0;
	float percentage0to15yearsInfected=0;
	float percentage15to30yearsInfected=0;
	float percentage30to60yearsInfected=0;
	float percentage70to99yearsInfected=0;
	float percentageOlderThan99yearsInfected=0;
	
	float percentageLessThan20WeightInfected=0;
	float percentage20to50WeightInfected=0;
	float percentage50to70WeightInfected=0;
	float percentage70to90WeightInfected=0;
	float percentage90to110WeightInfected=0;
	float percentageMoreThan120WeightInfected=0;
	
	float percentageLessThan100HeightInfected=0;
	float percentage100to150HeightInfected=0;
	float percentage150to175HeightInfected=0;
	float percentage175to200HeightInfected=0;
	float percentageMoreThan200HeightInfected=0;
	
	float percentageMenInfected=0;
	float percentageWomenInfected=0;
	
	 List<Float> percentageAgeInfected= new ArrayList<Float>();
	 List<Float> percentageWeightInfected= new ArrayList<Float>();
	 List<Float> percentageHeightInfected= new ArrayList<Float>();
	 List<Float> percentageSexInfected= new ArrayList<Float>();
	 List<Float> percentageHospitalInfected= new ArrayList<Float>();
	 
	 List<Float> percentageLocationInfected= new ArrayList<Float>();
	 List<Float> percentageMedicationInfected= new ArrayList<Float>();
	 List<Float> percentagePathologiesInfected= new ArrayList<Float>();
	 	 List<Float> percentageBloodTypeInfected= new ArrayList<Float>();

	 List<Float> percentageAgeDead= new ArrayList<Float>();
	 List<Float> percentageWeightDead= new ArrayList<Float>();
	 List<Float> percentageHeightDead= new ArrayList<Float>();
	 List<Float> percentageSexDead= new ArrayList<Float>();
	 List<Float> percentageHospitalDead= new ArrayList<Float>();
	 List<Float> percentageLocationDead= new ArrayList<Float>();

	 List<Float> percentageMedicationDead= new ArrayList<Float>();
	 List<Float> percentagePathologiesDead= new ArrayList<Float>();
	 	 	 List<Float> percentageBloodTypeDead= new ArrayList<Float>() ;

	 
	
	 
	 




	
	
	
}
*/

public void calculateScore(Patient p,float DeadImportance) {
	//cojo los numeros de todos
	
	List<Float> contributions = new ArrayList<>();

	List<Float> agePercentagesAlive = getAgesALIVE();
	List<Float> agePercentagesDead = getAgesDEAD();
	
	List<Float> HeighPerAlive = getHeightALIVE();
	List<Float> HeighPerDead = getHeightDEAD();
	
	List<Float> WeightPerAlive = getWeightALIVE();
	List<Float> WeightPerDead = getWeightDEAD();
	List<Float> perHospitalAlive = getHosALIVE();
	List<Float> perHospitalDead = getHosDEAD();	

	List<Float> perLocationAlive = getLocALIVE();
	List<Float> perLocationDead = getLocDEAD();
		
		
	List<Float> perMedAlive = getMedALIVE();
	List<Float> perMedDead = getMedDead();

	List<Float> PerSexAlive = getSexALIVE();
	List<Float> PerSexDead = getSexDead();
	
	List<Float> PerBloodAlive = getBloodALIVE();
	List<Float> PerBloodDead = getBloodDead();
	
	
	List<Float> PerOPATAlive = getOPatALIVE();
	List<Float> PerOPATDead = getOPatDead();

	
	float AGEPercentagePatientAlive =getPercentageAgePatient(p.getTheAge(p.getBirthday()),agePercentagesAlive);
	float patientScoreBasicAgeAlive= patientScore(getMaxNum (agePercentagesAlive),getMinNum (agePercentagesAlive),AGEPercentagePatientAlive,agePercentagesAlive.size());
	float contributionAgeAlive = patientScoreBasicAgeAlive*(1-DeadImportance);
	contributions.add(contributionAgeAlive);
	float AGEPercentagePatientDead =getPercentageAgePatient(p.getTheAge(p.getBirthday()),agePercentagesDead);
	float patientScoreBasicAgeDead= patientScore(getMaxNum (agePercentagesDead),getMinNum (agePercentagesDead),AGEPercentagePatientDead,agePercentagesDead.size());
	float contributionAgeDead = patientScoreBasicAgeAlive*(DeadImportance);
	contributions.add(contributionAgeDead);

	float HeightPercentageAlive = HeightPercentage(p.getHeight(),HeighPerAlive);
	float patientScoreBasicHeightAlive= patientScore(getMaxNum (HeighPerAlive),getMinNum (HeighPerAlive),HeightPercentageAlive,HeighPerAlive.size());
	float contributionHeightAlive = patientScoreBasicHeightAlive*(1-DeadImportance);
	contributions.add(contributionHeightAlive);
	float HeightPercentageDead = HeightPercentage(p.getHeight(),HeighPerDead);
	float patientScoreBasicHeightDead= patientScore(getMaxNum (HeighPerDead),getMinNum (HeighPerDead),HeightPercentageDead,HeighPerDead.size());
	float contributionHeightDead = patientScoreBasicHeightDead*(1-DeadImportance);
	contributions.add(contributionHeightDead);
	
	float WeightPercentageAlive = WeightPercentage(p.getWeight(),WeightPerAlive);
	float patientScoreBasicWeightAlive= patientScore(getMaxNum (WeightPerAlive),getMinNum (WeightPerAlive),WeightPercentageAlive,WeightPerAlive.size());
	float contributionWeightAlive = patientScoreBasicWeightAlive*(1-DeadImportance);
	contributions.add(contributionWeightAlive);
	float WeightPercentageDead = WeightPercentage(p.getWeight(),WeightPerDead);
	float patientScoreBasicWeightDead= patientScore(getMaxNum (WeightPerDead),getMinNum (WeightPerDead),WeightPercentageDead,WeightPerDead.size());
	float contributionWeightDead = patientScoreBasicWeightDead*(DeadImportance);
	contributions.add(contributionWeightDead);
	
	float locationPercentageAlive= LocationPercentage(p.getHos_location(),perLocationAlive);
	float patientScoreBasicLocationAlive= patientScore(getMaxNum (perLocationAlive),getMinNum (perLocationAlive),locationPercentageAlive,perLocationAlive.size());
	float contributionLocationAlive = patientScoreBasicLocationAlive*(1-DeadImportance);
	contributions.add(contributionLocationAlive);
	float locationPercentageDead= LocationPercentage(p.getHos_location(),perLocationDead);
	float patientScoreBasicLocationDead= patientScore(getMaxNum (perLocationDead),getMinNum (perLocationDead),locationPercentageDead,perLocationDead.size());
	float contributionLocationDead = patientScoreBasicLocationDead*(DeadImportance);
	contributions.add(contributionLocationDead);

	float sexPercentageAlive= SexPercentage(p.getSex(),PerSexAlive);
	float patientScoreBasicSexAlive= patientScore(getMaxNum (PerSexAlive),getMinNum (PerSexAlive),sexPercentageAlive,PerSexAlive.size());
	float contributionSexAlive = patientScoreBasicSexAlive*(1-DeadImportance);
	contributions.add(contributionSexAlive);
	float sexPercentageDead= SexPercentage(p.getSex(),PerSexDead);
	float patientScoreBasicSexDead= patientScore(getMaxNum (PerSexDead),getMinNum (PerSexDead),sexPercentageDead,PerSexDead.size());
	float contributionSexDead = patientScoreBasicSexDead*(DeadImportance);
	contributions.add(contributionSexDead);
	
	float bloodPercentageAlive= BloodPercentage(p.getBloodType(),PerBloodAlive);
	float patientScoreBasicBloodAlive= patientScore(getMaxNum (PerBloodAlive),getMinNum (PerBloodAlive),bloodPercentageAlive,PerBloodAlive.size());
	float contributionBloodAlive = patientScoreBasicBloodAlive*(1-DeadImportance);
	contributions.add(contributionBloodAlive);
	float bloodPercentageDead= BloodPercentage(p.getBloodType(),PerBloodDead);
	float patientScoreBasicBloodDead= patientScore(getMaxNum (PerBloodDead),getMinNum (PerBloodDead),bloodPercentageDead,PerBloodDead.size());
	float contributionBloodDead = patientScoreBasicBloodDead*(DeadImportance);
	contributions.add(contributionBloodDead);
	
	float hospitalPercentage= HospitalPercentage(p.getHospital(),perHospitalAlive,true);
   	float patientScoreBasicHospitalAlive= patientScore(getMaxNum (perHospitalAlive),getMinNum (perHospitalAlive),bloodPercentageAlive,perHospitalAlive.size());
	float contributionHospitalAlive = patientScoreBasicHospitalAlive*(1-DeadImportance);
	contributions.add(contributionHospitalAlive);
	float HospitalPercentageDead= HospitalPercentage(p.getHospital(),perHospitalDead, false);
   	float patientScoreBasicHospitalDead= patientScore(getMaxNum (perHospitalDead),getMinNum (perHospitalDead),bloodPercentageDead,perHospitalDead.size());
	float contributionHospitalDead = patientScoreBasicHospitalDead*(DeadImportance);
	contributions.add(contributionHospitalDead);
	
	List<String> Medication_Patient = Main.getInter().getMedicationfromPatientNAME(p.getId());

	for(int i=0;i<Medication_Patient.size();i++) {
		String medication = Medication_Patient.get(i);
	float medicationPercentageAlive= MedicationPercentage(medication,perMedAlive,true);
 	float patientScoreBasicmedicationAlive= patientScore(getMaxNum (perMedAlive),getMinNum (perMedAlive),medicationPercentageAlive,perMedAlive.size());
	float contributionMedlAlive = patientScoreBasicmedicationAlive*(1-DeadImportance);
	contributions.add(contributionMedlAlive);
	float medicationPercentageDead= MedicationPercentage(medication,perMedDead,false);
 	float patientScoreBasicmedicationDead= patientScore(getMaxNum (perMedDead),getMinNum (perMedDead),medicationPercentageDead,perMedDead.size());
	float contributionMedlDead = patientScoreBasicmedicationDead*(DeadImportance);
	contributions.add(contributionMedlDead);
	}
	
	List<String> Patho_Patient = Main.getInter().getMedicationfromPatientNAME(p.getId());

	for(int i=0;i<Patho_Patient.size();i++) {
		String pathology = Patho_Patient.get(i);
	float pathologyPercentageAlive= PathologyPercentage(pathology,PerOPATAlive,true);
 	float patientScoreBasicpathologyAlive= patientScore(getMaxNum (PerOPATAlive),getMinNum (PerOPATAlive),pathologyPercentageAlive,PerOPATAlive.size());
	float contributionMedlAlive = patientScoreBasicpathologyAlive*(1-DeadImportance);
	contributions.add(contributionMedlAlive);
	float pathologyPercentageDead= PathologyPercentage(pathology,PerOPATDead,false);
 	float patientScoreBasicPathologyDead= patientScore(getMaxNum (PerOPATDead),getMinNum (PerOPATDead),pathologyPercentageDead,PerOPATDead.size());
	float contributionMedlDead = patientScoreBasicPathologyDead*(DeadImportance);
	contributions.add(contributionMedlDead);
	}
	float totalContributions=0;
	for(int i = 0;i<contributions.size();i++) {
		totalContributions+=contributions.get(i);
	}
	
	Main.getInter().modifyScore(p.getId(), totalContributions);
	

	
}
public   Float  PathologyPercentage (String pathologyName, List <Float> percentages,boolean alive) {
	 float percentage=0;
		List<String> difPathologiesname = Main.getInter().getdifferentPaths(alive);

for (int i=0;i<percentages.size();i++) {
	if(difPathologiesname.get(i).equals(pathologyName)) {
		percentage = percentages.get(i);
	}
	
}

	 
	 return percentage;
	
}

public   Float  MedicationPercentage (String medicationName, List <Float> percentages,boolean alive) {
	 float percentage=0;
		List<String> difMedicationsname = Main.getInter().getdifferentMeds(alive);

for (int i=0;i<percentages.size();i++) {
	if(difMedicationsname.get(i).equals(medicationName)) {
		percentage = percentages.get(i);
	}
	
}

	 
	 return percentage;
	
}




public   Float   HospitalPercentage (String hospital, List <Float> percentages,boolean alive) {
	 float percentage=0;
		List<String> difHospitalsname = Main.getInter().getdifferentHospitals(alive);

for (int i=0;i<percentages.size();i++) {
	if(difHospitalsname.get(i).equals(hospital)) {
		percentage = percentages.get(i);
	}
	
}

	 
	 return percentage;
}
public   Float   BloodPercentage (String blood, List <Float> percentages) {
	 float percentage=0;
	 if (blood.equals("A+")) {
		 percentage = percentages.get(0);
	 } 
	 else  if (blood.equals("A-")) {
		 percentage = percentages.get(1);

		 
	 } 
	 else if (blood.equals("B+")) {
		 percentage = percentages.get(2);

	 } if (blood.equals("B-")) {
		 percentage = percentages.get(3);
	 } 
	 else  if (blood.equals("0+")) {
		 percentage = percentages.get(4);

		 
	 } 
	 else if (blood.equals("0-")) {
		 percentage = percentages.get(5);

	 } else  if (blood.equals("AB-")) {
		 percentage = percentages.get(6);

		 
	 } 
	 else if (blood.equals("AB+")) {
		 percentage = percentages.get(7);

	 }
	 
	 return percentage;
}
public   Float   SexPercentage (Sex sex, List <Float> percentages) {
	 float percentage=0;
	 if (sex.equals("M")) {
		 percentage = percentages.get(0);
	 } 
	 else  if (sex.equals("F")) {
		 percentage = percentages.get(1);

		 
	 } 
	 
	 return percentage;
}

public   Float   LocationPercentage (String location, List <Float> percentages) {
	 float percentage=0;
	 if (location.equals("Home")) {
		 percentage = percentages.get(0);
	 } 
	 else  if (location.equals("Floor hospital")) {
		 percentage = percentages.get(1);

		 
	 } 
	 else if (location.equals("ICU")) {
		 percentage = percentages.get(2);

	 }
	 
	 return percentage;
}

public   Float   WeightPercentage (float weight, List <Float> percentages) {
	 float percentage=0;
	 if (weight>0 && weight<20) {
		 percentage = percentages.get(0);
	 } 
	 else  if (weight>20  && weight<50) {
		 percentage = percentages.get(1);

		 
	 } 
	 else if (weight>50 &&weight<70) {
		 percentage = percentages.get(2);

	 }
	 else if (weight>70 && weight<90) {
		 percentage = percentages.get(3);

	 } 
	 else  if (weight>90 && weight<110) {
		 percentage = percentages.get(4);

	 } 
	 else  if (weight>110) {
		 percentage = percentages.get(5);

	 }
	 return percentage;
}

public   Float   HeightPercentage (float height, List <Float> percentages) {
	 float percentage=0;
	 if (height>0 && height<1.0) {
		 percentage = percentages.get(0);
	 } 
	 else  if (height>1.0  && height<1.50) {
		 percentage = percentages.get(1);

		 
	 } 
	 else if (height>1.50 &&height<1.750) {
		 percentage = percentages.get(2);

	 }
	 else if (height>1.75 && height<2.0) {
		 percentage = percentages.get(3);

	 } 
	 else  if (height>2.0) {
		 percentage = percentages.get(4);

	 }
	 return percentage;
}



public float patientScore(float MaxPercentage, float MinPercentage, float PatientPercentage,int NumberOptionsFeature) {
	float difference = MaxPercentage-MinPercentage;
	float base = difference*PatientPercentage;
	float exponent = 1+(NumberOptionsFeature/10);
	float score = (float) Math.pow(base, exponent);
	
	
	return score;
}
public   Float   getPercentageAgePatient (int age, List <Float> percentages) {
	 float percentage=0;
	 if (age>0 && age<20) {
		 percentage = percentages.get(0);
	 } 
	 else  if (age>20  && age<40) {
		 percentage = percentages.get(1);

		 
	 } 
	 else if (age>40 &&age<60) {
		 percentage = percentages.get(2);

	 }
	 else if (age>60 && age<80) {
		 percentage = percentages.get(3);

	 } 
	 else  if (age>80) {
		 percentage = percentages.get(4);

	 }
	 return percentage;
}



public  List<Float>   getOPatALIVE () {
	List<String> paths = Main.getInter().getdifferentPaths(true);

	List<Integer> NumsPaths =new ArrayList<>();
	for (int i =0; i<paths.size();i++) {
		NumsPaths.add(Main.getInter().getdifferentPathsCOUNT( paths.get(i),true));
	}
	
	return calculatePercentages(NumsPaths);
	
	
	
}public  List<Float>   getOPatDead () {
	List<String> paths = Main.getInter().getdifferentPaths(false);

	List<Integer> NumsPaths =new ArrayList<>();
	for (int i =0; i<paths.size();i++) {
		NumsPaths.add(Main.getInter().getdifferentPathsCOUNT( paths.get(i),false));
	}
	
	return calculatePercentages(NumsPaths);
	
	
	
}

public  List<Float>   getBloodALIVE () {
List<Integer> bloodsNum = new ArrayList<>();




	
bloodsNum.add( Main.getInter().getNumberPatientsbyanyString("bloodType","A+" ,true));
bloodsNum.add(  Main.getInter().getNumberPatientsbyanyString("bloodType", "A-",true));
bloodsNum.add(  Main.getInter().getNumberPatientsbyanyString("bloodType","B+",true));
bloodsNum.add(  Main.getInter().getNumberPatientsbyanyString("bloodType", "B-",true));
bloodsNum.add( Main.getInter().getNumberPatientsbyanyString("bloodType", "0+",true));
bloodsNum.add( Main.getInter().getNumberPatientsbyanyString("bloodType", "0-",true));
bloodsNum.add( Main.getInter().getNumberPatientsbyanyString("bloodType", "AB-",true));
bloodsNum.add( Main.getInter().getNumberPatientsbyanyString("bloodType", "AB+",true));
return calculatePercentages(bloodsNum);
	
}public  List<Float>   getBloodDead () {
	List<Integer> bloodsNum = new ArrayList<>();




	
	bloodsNum.add( Main.getInter().getNumberPatientsbyanyString("bloodType","A+" ,false));
	bloodsNum.add(  Main.getInter().getNumberPatientsbyanyString("bloodType", "A-",false));
	bloodsNum.add(  Main.getInter().getNumberPatientsbyanyString("bloodType","B+",false));
	bloodsNum.add(  Main.getInter().getNumberPatientsbyanyString("bloodType", "B-",false));
	bloodsNum.add( Main.getInter().getNumberPatientsbyanyString("bloodType", "0+",false));
	bloodsNum.add( Main.getInter().getNumberPatientsbyanyString("bloodType", "0-",false));
	bloodsNum.add( Main.getInter().getNumberPatientsbyanyString("bloodType", "AB-",false));
	bloodsNum.add( Main.getInter().getNumberPatientsbyanyString("bloodType", "AB+",false));
	return calculatePercentages(bloodsNum);
		
	}

	



public  List<Float>   getSexALIVE () {
	List<Integer> NumsSex =new ArrayList<>();
	NumsSex.add(Main.getInter().searchPatientGenericCOUNT("sex", "M",true));
	NumsSex.add(Main.getInter().searchPatientGenericCOUNT("sex","F",true));
	return calculatePercentages(NumsSex);

	

	
}

public  List<Float>   getSexDead() {
	List<Integer> NumsSex =new ArrayList<>();
	NumsSex.add(Main.getInter().searchPatientGenericCOUNT("sex", "M",false));
	NumsSex.add(Main.getInter().searchPatientGenericCOUNT("sex","F",false));
	return calculatePercentages(NumsSex);

	
}

public  List<Float>   getMedALIVE () {
	List<String> Medics = Main.getInter().getdifferentMeds(true);
	List<Integer> NumsMeds =new ArrayList<>();
	for (int i =0; i<Medics.size();i++) {
		NumsMeds.add(Main.getInter().getdifferentMedsCOUNT( Medics.get(i),true));
	}
	
	return calculatePercentages(NumsMeds);

}
public  List<Float>   getMedDead () {
	List<String> Medics = Main.getInter().getdifferentMeds(false);
	List<Integer> NumsMeds =new ArrayList<>();
	for (int i =0; i<Medics.size();i++) {
		NumsMeds.add(Main.getInter().getdifferentMedsCOUNT( Medics.get(i),false));
	}
	
	return calculatePercentages(NumsMeds);

}






public  List<Float>   getLocALIVE () {
List<Integer> locationsNumb = new ArrayList<>();


 
locationsNumb.add(Main.getInter().getNumberPatientsbyanyString("hos_location","Home" ,true));
locationsNumb.add( Main.getInter().getNumberPatientsbyanyString("hos_location", "Floor hospital",true));
locationsNumb.add(Main.getInter().getNumberPatientsbyanyString("hos_location", "ICU",true));

return calculatePercentages(locationsNumb);
	
}
public  List<Float>   getLocDEAD () {
	List<Integer> locationsNumb = new ArrayList<>();


	 
	locationsNumb.add(Main.getInter().getNumberPatientsbyanyString("hos_location","Home" ,false));
	locationsNumb.add( Main.getInter().getNumberPatientsbyanyString("hos_location", "Floor hospital",false));
	locationsNumb.add(Main.getInter().getNumberPatientsbyanyString("hos_location", "ICU",false));

	return calculatePercentages(locationsNumb);
		
	}

	


public  List<Float>   getHosALIVE () {
	List<Integer> Hospitals = new ArrayList<>();
	List<String> difHospitalsname = Main.getInter().getdifferentHospitals(true);

	for (int i =0; i<Hospitals.size();i++) {
		Hospitals.add(Main.getInter().getNumberPatientsbyanyString("hospital",""+ difHospitalsname.get(i),true));
	}
	
	return calculatePercentages(Hospitals);

}
public  List<Float>   getHosDEAD() {
	List<Integer> Hospitals = new ArrayList<>();
	List<String> difHospitalsname = Main.getInter().getdifferentHospitals(false);

	for (int i =0; i<Hospitals.size();i++) {
		Hospitals.add(Main.getInter().getNumberPatientsbyanyString("hospital",""+ difHospitalsname.get(i),false));
	}
	
	return calculatePercentages(Hospitals);

}



public  List<Float>   getWeightALIVE () {
	List<Integer> Weights = new ArrayList<>();
	
	Weights.add( Main.getInter().getNumberPatientsbyRangeofFeature("weight", 20, 0,true));
	Weights.add( Main.getInter().getNumberPatientsbyRangeofFeature("weight", 50, 20,true));
	Weights.add( Main.getInter().getNumberPatientsbyRangeofFeature("weight",  70, 50,true));
	Weights.add( Main.getInter().getNumberPatientsbyRangeofFeature("weight", 90, 70,true));
	Weights.add( Main.getInter().getNumberPatientsbyRangeofFeature("weight",  110, 90,true));
	Weights.add( Main.getInter().getNumberPatientsbyRangeofFeature("weight",900, 110,true));
	
	return calculatePercentages(Weights);

	
}
public  List<Float>   getWeightDEAD () {
	List<Integer> Weights = new ArrayList<>();
	
	Weights.add( Main.getInter().getNumberPatientsbyRangeofFeature("weight", 20, 0,false));
	Weights.add( Main.getInter().getNumberPatientsbyRangeofFeature("weight", 50, 20,false));
	Weights.add( Main.getInter().getNumberPatientsbyRangeofFeature("weight",  70, 50,false));
	Weights.add( Main.getInter().getNumberPatientsbyRangeofFeature("weight", 90, 70,false));
	Weights.add( Main.getInter().getNumberPatientsbyRangeofFeature("weight",  110, 90,false));
	Weights.add( Main.getInter().getNumberPatientsbyRangeofFeature("weight",900, 110,false));
	
	return calculatePercentages(Weights);

	
}


public  List<Float>   getHeightALIVE () {
	List<Integer> heights = new ArrayList<>();
	heights.add(Main.getInter().getNumberPatientsbyRangeofFeature("height", (float)1.0, 0,true));
	heights.add(Main.getInter().getNumberPatientsbyRangeofFeature("height", (float)1.50, (float)1.0,true));
    heights.add(Main.getInter().getNumberPatientsbyRangeofFeature("height", (float)1.75, (float)1.50,true));
    heights.add(Main.getInter().getNumberPatientsbyRangeofFeature("height", (float)2.00, (float)1.75,true));
	heights.add(Main.getInter().getNumberPatientsbyRangeofFeature("height", (float)5.0, (float)2.0,true));
	
	return calculatePercentages(heights);
}public  List<Float>   getHeightDEAD () {
	List<Integer> heights = new ArrayList<>();
	heights.add(Main.getInter().getNumberPatientsbyRangeofFeature("height", (float)1.0, 0,false));
	heights.add(Main.getInter().getNumberPatientsbyRangeofFeature("height", (float)1.50, (float)1.0,false));
    heights.add(Main.getInter().getNumberPatientsbyRangeofFeature("height", (float)1.75, (float)1.50,false));
    heights.add(Main.getInter().getNumberPatientsbyRangeofFeature("height", (float)2.00, (float)1.75,false));
	heights.add(Main.getInter().getNumberPatientsbyRangeofFeature("height", (float)5.0, (float)2.0,false));
	
	return calculatePercentages(heights);
}

public  List<Float>   calculatePercentages (List<Integer> heights) {
	 List<Float> listofpercentages = new ArrayList<>();
	 int add=0;

		for (int i=0;i<heights.size() ; i ++) {
			 add+=heights.get(i);
		}
		
		 
		 for (int i=0;i<heights.size();i++) {
			 float percentage= (heights.get(i)/add);
			 listofpercentages.add(percentage);
		 }
		
		return listofpercentages;
	
}



public  List<Float>   getAgesALIVE () {
	
	List<Date> dates = Main.getInter().getDates(true);
	List<Integer> ages = new ArrayList<>();
	 LocalDate today = LocalDate.now();   
	 for(int i =0;i<dates.size();i++) {
		 System.out.println(i);

		 LocalDate fHoy= LocalDate.now();
	     LocalDate cumple= dates.get(i).toLocalDate();
	     long age= ChronoUnit.YEARS.between(cumple, fHoy); 
		 
    		
    		ages.add((int)age);
    	}
	 int timer1=0;
	 int timer2=0;
	 int timer3=0;
	 int timer4=0;
	 int timer5=0;

	 for(int i =0;i<ages.size();i++) {
		 
		 if (ages.get(i)>0 && ages.get(i)<20) {
			 timer1++;
		 } 
		 else if (ages.get(i)>20  && ages.get(i)<40) {
			 timer2++;
		 } 
		 else if (ages.get(i)>40 && ages.get(i)<60) {
			 timer3++;
		 }
		 else if (ages.get(i)>60 && ages.get(i)<80) {
			 timer4++;
		 } 
		 else  if (ages.get(i)>80) {
			 timer5++;
		 }
		 
    		
    	}
	 List<Integer> agePercentages = new ArrayList<>();
	
	 
	 	return calculatePercentages(agePercentages);

}
public  List<Float>   getAgesDEAD () {
	
	List<Date> dates = Main.getInter().getDates(false);
	List<Integer> ages = new ArrayList<>();
	 LocalDate today = LocalDate.now();   
	 for(int i =0;i<dates.size();i++) {
		 System.out.println(i);

		 LocalDate fHoy= LocalDate.now();
	     LocalDate cumple= dates.get(i).toLocalDate();
	     long age= ChronoUnit.YEARS.between(cumple, fHoy); 
		 
    		
    		ages.add((int)age);
    	}
	 int timer1=0;
	 int timer2=0;
	 int timer3=0;
	 int timer4=0;
	 int timer5=0;

	 for(int i =0;i<ages.size();i++) {
		 
		 if (ages.get(i)>0 && ages.get(i)<20) {
			 timer1++;
		 } 
		 else if (ages.get(i)>20  && ages.get(i)<40) {
			 timer2++;
		 } 
		 else  if (ages.get(i)>40 && ages.get(i)<60) {
			 timer3++;
		 }
		 else  if (ages.get(i)>60 && ages.get(i)<80) {
			 timer4++;
		 } 
		 else if (ages.get(i)>80) {
			 timer5++;
		 }
		 
    		
    	}
	 List<Integer> agePercentages = new ArrayList<>();
	 
	 agePercentages.add(timer1);
	 agePercentages.add(timer2);
	 agePercentages.add(timer3);
	 agePercentages.add(timer4);
	 agePercentages.add(timer5);
	 
	 //saco porcentaje de cada timer
	 
	
	 
	 	return calculatePercentages(agePercentages);
}
public boolean getFirstTime() {
	return firstTime;
}
public void setFirstTime(boolean firstTime) {
	this.firstTime = firstTime;
}

public float getMinNum(List<Float> list) {
	float min=999;
	
     for (int i = 0; i < list.size(); i++) {
         if (list.get(i) < min) {
             min = list.get(i);
         }
     }
	
	return min;
}public float getMaxNum(List<Float> list) {

	
	 float max = 0;
     for (int i = 0; i < list.size(); i++) {
         if (list.get(i) > max) {
             max = list.get(i);
         }
     }
	
	return max;
}




}
