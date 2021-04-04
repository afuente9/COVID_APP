package db.statistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import db.pojos.Day;
import db.pojos.Doctor;
import db.pojos.Patient;

public class Score {
Patient p=null;
public void calculateValues() {
	int score=0;
//TODO traerse todos los porcentages de las estadisticas
	/*float percentage0to15yearsInfected=0;
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
	*/
	 List<Float> percentageAgeInfected= new ArrayList<Float>();
	 List<Float> percentageWeightInfected= new ArrayList<Float>();
	 List<Float> percentageHeightInfected= new ArrayList<Float>();
	 List<Float> percentageSexInfected= new ArrayList<Float>();
	 List<Float> percentageHospitalInfected= new ArrayList<Float>();
	 List<Float> percentageLocationInfected= new ArrayList<Float>();
	 List<Float> percentageMedicationInfected= new ArrayList<Float>();
	 List<Float> percentagePathologiesInfected= new ArrayList<Float>();
	 
	 List<Float> percentageAgeDeath= new ArrayList<Float>();
	 List<Float> percentageWeightDeath= new ArrayList<Float>();
	 List<Float> percentageHeightDeath= new ArrayList<Float>();
	 List<Float> percentageSexDeath= new ArrayList<Float>();
	 List<Float> percentageHospitalDeath= new ArrayList<Float>();
	 List<Float> percentageLocationDeath= new ArrayList<Float>();
	 List<Float> percentageMedicationDeath= new ArrayList<Float>();
	 List<Float> percentagePathologiesDeath= new ArrayList<Float>();
	 
	 
	
	 
	 




	
	
	
}

public int calculateScore(Patient p,List<Float> percentageAgeInfectedList,Day d) {
	//TODO AL FINAL DEL TODo CUANDO SE VERIFIQUE QUE TODo FUNCIONA!! simplificar variables 
	//TODO REPETIR PARA TODOS LOS TIPOS TRAYENDO TODAS LAS LISTAS 
	/* Necesitamos poder controlar que dia es hoy para saber el average. 
	 * Justo antes de que la aplicacion cambie de dia tiene que crear un nuevo Day metiendole el numero de muertos que hay en ese momento y 
	 * calculando el average con el numero de muertos de los pasados 7 dias.
	 * Justo despues se actualiza el score de todos los pacietnes con los nuevos datos
	 * 
	 * No vamos a poder controlar cuando se cambia de dia porque la aplicacion no va a estar conectada 24 horas. Hay que actualizar los datos al
	 * iniciar la aplicacion controlando si ha pasado o no un dia nuevo para ver si hay que actualizar los scores y el getaverage 
	 * 
	 */
	double [] contributions= new double[20];
	int  score=0;
	double exponente = -0.039*d.getAverage()+5;
	double denominadorDeathScore=1+Math.pow(2.71828,exponente);
	double DeadScore=((35/denominadorDeathScore)+65)/100;
	double InfectedScore=1-DeadScore;


	int age=p.getTheAge(p.getBirthday());
	
	float percentageAge= getAgePercentage(age,percentageAgeInfectedList);
	
	float agePoints= ((getMaxNum(percentageAgeInfectedList)-getMinNum(percentageAgeInfectedList))*percentageAge);
	float exponent=1+(percentageAgeInfectedList.size()/10);
	double basicScore= Math.pow(agePoints,exponent);
	double finalAgeScoreInfected=basicScore*InfectedScore;
	
	
	
	score=getFinalScore(contributions);
	return score;
	
}
public float getAgePercentage (int age,List<Float> percentageAgeInfected) {
	
	float percentageAge=0;
	
if (age<15) {
	percentageAge=percentageAgeInfected.get(0);
	
}else if(age<30&&age>15) {
	percentageAge=percentageAgeInfected.get(1);

}else if(age<65&&age>30) {
	percentageAge=percentageAgeInfected.get(2);

}else if(age<90&&age>65) {
	percentageAge=percentageAgeInfected.get(3);

}else if(age>90) {
	percentageAge=percentageAgeInfected.get(4);

}
	
	
	return percentageAge;
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
public int getFinalScore(double [] contributions) {
	double score=0;
	for(int i=0; i<=contributions.length; i++) {
		score+=contributions[i];
	}
	return (int) score;
}

}
