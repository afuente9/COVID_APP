package db.statistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import db.pojos.Day;
import db.pojos.Doctor;
import db.pojos.Patient;

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
	 
	 List<Float> percentageAgeDeath= new ArrayList<Float>();
	 List<Float> percentageWeightDeath= new ArrayList<Float>();
	 List<Float> percentageHeightDeath= new ArrayList<Float>();
	 List<Float> percentageSexDeath= new ArrayList<Float>();
	 List<Float> percentageHospitalDeath= new ArrayList<Float>();
	 List<Float> percentageLocationDeath= new ArrayList<Float>();
	 List<Float> percentageMedicationDeath= new ArrayList<Float>();
	 List<Float> percentagePathologiesDeath= new ArrayList<Float>();
	 
	 
	
	 
	 




	
	
	
}
*/

public void calculateScore(Patient p,Day d,List<Float> percentageAgeInfectedList) {
	/*
	 * PARA LOS CASOS DONDE HAY INTERVALOS COMO AGE, WEIGHT Y HEIGHT USAREMOS LISTAS DE LA MISMA MANERA QUE YA ESTÁ IMPLEMENTADO EN AGE
	 * PARA LOS DEMÁS CASOS, USAREMOS DICCIONARIOS DONDE LE PASAREMOS EL TIPO QUE TIENE EL PACIENTE COMO KEY, Y NOS DEVOLVERA EL PORCENTAJE
	 * POR EJEMPLO EN SEXO, HOSPITAL, MEDICACION ETC. ASI SOLUCIONAMOS EL TEMA DE NO SABER CUANTOS TIPOS DIFERENTES TENEMOS
	 * EN LOS QUE USAMOS DICCIONARIOS, TENDREMOS QUE COGER TODOS LOS PORCENTAJES PARA MIRAR CUAL ES EL MAXIMO Y EL MINIMO. 
	 * A MALAS, PASAMOS TODOS A UNA LISTA Y LUEGO USAMOS LOS METODOS MIN Y MAX YA CREADOS.
	
	*/
	//TODO AL FINAL DEL TODo CUANDO SE VERIFIQUE QUE TODo FUNCIONA!! simplificar variables 
	
	double DeadScore=0;
	double InfectedScore=0;
	float percentageAgeDifference=0;
	if(firstTime==true) {
		//calcular el infected score y el dead score solo lo tiene que hacer una vez al dia y usarel mismo valor para todos los pacientes
		double exponente = -0.039*d.getAverage()+5;
		double denominadorDeathScore=1+Math.pow(2.71828,exponente);
		 DeadScore=((35/denominadorDeathScore)+65)/100;
		 InfectedScore=1-DeadScore;
		
		 percentageAgeDifference= calculateDiferencesList(percentageAgeInfectedList);
		// diferencialista2= calculateDiferencesList(lista2);...
		//diferenciadiccionario1=calculateDiferencesDictionary(diccionario1)... asi con todos
		firstTime=false;
	}
	
	double [] contributions= new double[20];
	int  score=0;
	


	int age=p.getTheAge(p.getBirthday());//obtenemos la edad del paci
    float percentageAge= getAgePercentage(age,percentageAgeInfectedList);//miramos en que intervalo esta y sacamos de la lista  el porcentage de ese intervalo 
    float agePoints= (percentageAgeDifference*percentageAge);
	float exponent=1+(percentageAgeInfectedList.size()/10);
	double basicScore= Math.pow(agePoints,exponent);
	double finalAgeScoreInfected=basicScore*InfectedScore;
	
	
	//TODO repetir para todas las listas Y DICCIONARIOS

	
	score=getFinalScore(contributions);
    p.setScore(score);	
}
public boolean getFirstTime() {
	return firstTime;
}
public void setFirstTime(boolean firstTime) {
	this.firstTime = firstTime;
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
public float calculateDiferencesList(List<Float> list) {
	float difference=0;
	difference= getMaxNum(list)-getMinNum(list);
	return difference;
}


}
