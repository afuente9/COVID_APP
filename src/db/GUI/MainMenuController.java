package db.GUI;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import db.pojos.Administration;
import db.pojos.Day;
import db.pojos.Doctor;
import db.pojos.Lab;
import db.pojos.Medication;
import db.pojos.Other_Pathologies;
import db.pojos.Patient;
import db.pojos.Sex;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class MainMenuController implements Initializable {
	@FXML
    private PasswordField PasswordTextField;

    @FXML
    private TextField UserTextField;
    @FXML
    private Label labelDate;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/*
		Day d1= new Day(323,232,0,Date.valueOf("2021-04-20"));
		
	
		Day d2= new Day(432,231,0,Date.valueOf("2021-04-21"));
	
		Day d3= new Day(543,432,0,Date.valueOf("2021-04-22"));
		
		Day d4= new Day(555,243,0,Date.valueOf("2021-04-23"));
		
		Day d5= new Day(665,232,0,Date.valueOf("2021-04-24"));
		
		Day d6= new Day(443,123,0,Date.valueOf("2021-04-25"));
		
		Day d7= new Day(564,232,0,Date.valueOf("2021-04-26"));
		
		Day d8= new Day(876,456,0,Date.valueOf("2021-04-27"));
		
		Day d9= new Day(432,435,0,Date.valueOf("2021-04-28"));
		
		Day d0= new Day(132,133,0,Date.valueOf("2021-04-29"));
		System.out.println("denieu");
		
		Main.getInter().addDay(d1);

		Main.getInter().addDay(d2);
		Main.getInter().addDay(d3);
		Main.getInter().addDay(d4);
		Main.getInter().addDay(d5);
		Main.getInter().addDay(d6);
		Main.getInter().addDay(d7);
		Main.getInter().addDay(d8);
		Main.getInter().addDay(d9);
		Main.getInter().addDay(d0);



		  	
      p_new= new Patient(Place_Text,Name_Text,date,SSNum_Text,height,weight,sex,infected,alive,Hospital_Text,vaccinated,Blood_Text,dateIntroduced);
    	Main.getInter().addPatient(p_new);
	*/	
		
		String[] names = new String[]{"Daniel","Antonio","Loreto","Esteban","María","Javier","Lorena","Miriam","Carla","Jose Luís","Eva","Emilia","Josefa","Ofelia","Olga","Lidia","Cesar","Felipe","Lucas","Aitor","Samuel","Felix","Julio","Tomás","Gonzalo","Emilio","Hugo","Marcos","Jaime","Juan","Pablo","Mario","Carolina","Nerea","Sofía","Susana","Sonia","Alicia"," Inés","Rosa","Andrea","Alba","Elena","Sara","Ana María","Laura","Paula","Jose","Diego","Raúl","Leo","Pedro","Alfonso","Miguel"};
	
		String[] surnames = new String[]{"García","Rodríguez","Carmona","Gonzalez","Fernández","López","Martínez","Sánchez","Otero","Pérez","Franco","Martín","Largo Caballero","Jiménez","Messi","Ruíz","Hernández","Díaz","Ayuso","Moreno","Muñoz","Alonso","Gutiérrez","Torres","Ramos","Casillas","Gil","Cabello","Anchuela","Sanchís","Cantarero","Serrano","Blasco","Expósito","Ramírez","Suárez","Morales","Castillo"," Ortega","Castro","Cortés","Núñez","Iglesias","Yáñez","Lozano","Cano","Prieto","Cruz","Aguirre","Calvo","León","Campos","Carrasco","Lucena"};
		
		String[] locations = new String[]{"UCI","UCI","UCI","Hospital floor","Hospital floor","Hospital floor","Hospital floor","Hospital floor","Home","Home","Home","Home","Home","Home","Home","Home","Home","Home"};
		//fecha
		//ssnum
		//hei
		//wei
		String[] sex = new String[]{"Male","Female"};
		String[] hospital = new String[]{"12 Octubre","Puerta de Hierro","Clínic de Barcelona","Vall d'hebron","Teknon","Joan XXIII","Sant Joan de Reus","Infanta Sofía","General de Requena","Pardo de Aravaca","Quirón Toledo","Universitari Sagrat Cor","La Vega","San Jordi de Sant Andreu","La fe","Mediterráneo","El Pilar","San Juan de Dios Tenerife","2 de maig","De la Reina","Sierrallana","La inmaculada","La pedrera","Fundación de Alcorcón","Parque","Hospital de Sevilla"};

        //infected
		String[] vaccinated = new String[]{"true","false","false","false","false","false"};
		String[] bloodType = new String[]{"A+","A-","B+","B-","AB+","AB-","0+","0-"};
		String[] Medication = new String[]{"O2","Ketamine","Lidocaine","Morphine","Iboprufen","Acetylsalicylic acid","Paracetamol","Kodeine","Fentanyl","Methadone","Dexamethasone","Diacepan","Haloperidol","Lactulose","Metoclopramide","Peniciline","Loratadine","Hydrocortisone","Epinephrine","Calcium gluconate","Penicillamine","Amoxicillin","Loracepam"};
		String[] Pats = new String[]{"lung cancer","colon cancer","testicular cancer","liver cancer","arthritis","Asthma","Chronic fatigue sindrom","COPD","Diabetes","Crabs","Celiac disease","Hepatitis","Human papillomavirus","Gonorrhea","Fybromialgia","Lupus","Listediosis","ELA","Meningitis","Polio","Rubella","Tetanus","Tuberculosis","Siphilis","Yellow fiber","Zika"};
		/*for (int i =0; i<Medication.length;i++) {
			Main.getInter().addMedication(new Medication(Medication[i]));
		}
		for (int i =0; i<Pats.length;i++) {
			Main.getInter().addOtherPathologies(new Other_Pathologies(Pats[i]));
		}
		*/
		
		
		int contadoranmo=2006;
		for (int i =0; i<100; i++){
		   /* double ssrandom =  Math.random()*900000+100000;
		    int ssranint = (int) ssrandom;
		    double prefix =  Math.random()*90+10;
		    int prefixint = (int)prefix;
		    String prefixstring = "("+prefixint+")"+ssranint;
		    
		    System.out.println(prefixstring);
		    
Main.getInter().modifyPatient(i, "social_security", prefixstring);
			
			double diarandom = Math.random()*29+1;
			int diarandomint = (int) diarandom;
			String diadef="";
			if (diarandomint<10) {
				diadef="0"+diarandomint;
				
			}else {
				diadef=""+diarandomint;
			}
			double mesran = Math.random()*11+1;
			int mesrandomint = (int) mesran;
			String mesdef="";

			if (mesrandomint<10) {
				mesdef="0"+mesrandomint;
				
			}else {
				mesdef=""+mesrandomint;
			}
			String date =contadoranmo+"-"+mesdef+"-"+diadef;
			System.out.println(date);
			Main.getInter().modifyPatient(i, "birthday", date.valueOf(date));

			contadoranmo--;*/
		}
	
		
		
		
		
		
		
		




		this.labelDate.setText(""+Date.valueOf(LocalDate.now()));
		System.out.println("el day es "+Main.getInter().getLastDay().getDate());
		if ( Main.getInter().getLastDay().getDate().compareTo(Date.valueOf(LocalDate.now()))!=0 ){
			
			
			long daysWithoutChanges = ChronoUnit.DAYS.between(Main.getInter().getLastDay().getDate().toLocalDate(), LocalDate.now());
			if (daysWithoutChanges>1) {
				
			
			for (long i=1; i<=daysWithoutChanges;i++) {

				Day d= new Day(Main.getInter().getLastDay().getDeaths(),Main.getInter().getLastDay().getInfectedPatients(),Main.getInter().getLastDay().getAverage(),Date.valueOf(Main.getInter().getLastDay().getDate().toLocalDate().plusDays(i)));
				Main.getInter().addDay(Main.getInter().getLastDay());
				
			}
			}
			
			
		newDay();		
}
}
    @FXML
    void OnSignUpClick(ActionEvent event) {
		
			String name= "ChooseSignUpView.fxml";
			ChooseSignUpController controller = null;
			openWindow(name,controller);
			
		}
    @FXML
    void onClose(ActionEvent event) {
    	Stage stage = (Stage) this.PasswordTextField.getScene().getWindow();
    	stage.close();
    	
    	Main.getInter().disconnect();
		System.exit(0);
    }

    @FXML
    void OnEnterUser(ActionEvent event) {
    	System.out.println(UserTextField.getText());
		if(UserTextField.getText().equals("doctor")&&PasswordTextField.getText().equals("doctor")) {
			byte[] image=null;
			Doctor d = new Doctor(0,"cardio","Lucas Pérez",Date.valueOf("2000-10-10"),"34234",Sex.valueOf("Male"),"La Paz",image);
			String name= "DoctorMenuView.fxml";
			DoctorMenuController controller = null;
			try {
	    		FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
	    	Parent root;
	    	
	    		root = loader.load();
	    	
	    	  controller = loader.getController();
	    	  controller.setD(d);
	    	  controller.setDoctorName(d.getName());
	    	  //controller.setDoctorPic(Main.getInter().getPicFromDoc(1));
	    	  /*  BufferedImage bImage = ImageIO.read(new File("sample.jpg"));
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      ImageIO.write(bImage, "jpg", bos );
      byte [] data = bos.toByteArray();
      ByteArrayInputStream bis = new ByteArrayInputStream(data);
      BufferedImage bImage2 = ImageIO.read(bis);
      ImageIO.write(bImage2, "jpg", new File("output.jpg") );
      System.out.println("image created");*/
	    	Scene scene = new Scene(root);
	    	Stage stage = new Stage();
	    	
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.setScene(scene);
	        stage.showAndWait();
	    	} catch (IOException e) {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    	}
			
		}
		if(UserTextField.getText().equals("government")&&PasswordTextField.getText().equals("government")) {
			String name= "GovernmentMenuView.fxml";
			GovernmentMenuController controller = null;
			openWindow(name,controller);
		}
		if(UserTextField.getText().equals("")&&PasswordTextField.getText().equals("")) {
			
			String name= "LabMenuView.fxml";
			LabMenuController controller = null;
			byte[] image= null;
			Lab l_new= new Lab(0,0,"28223, Pozuelo","Pfizer","23433453F",image);
		//	Main.getInter().addLab(l_new);
			try {
	    		FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
	    	Parent root;
	    	//Administration admin= new Administration(1,100);
	    	//Main.getInter().addGoverment(admin);
	    	//Main.getInter().addLab(l_new);
	    		root = loader.load();
	    	
	    	  controller = loader.getController();
	    	 controller.setL(Main.getInter().getLab(1));
	    	 controller.setLabName(Main.getInter().getLab(1).getName());
	    	  
	    	Scene scene = new Scene(root);
	    	Stage stage = new Stage();
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.setScene(scene);
	        stage.showAndWait();
	    	} catch (IOException e) {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    	}
		}

		
    }
    
    void openWindow(String name,Object controller) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
    	Parent root;
    	
    		root = loader.load();
    	
    	  controller = loader.getController();
    	Scene scene = new Scene(root);
    	Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}

    }

public void newDay() { 

		
		//TODO  esto pasara if today != ultimo dia metido
		
		//calcular la media de muertos de los ultimos 7 dias
		
				float average=0;
				
				
				  float suma=0;
				  List <Day> last7=new ArrayList();

				  last7= Main.getInter().getLast7Days();

				  for (int i=1; i<=6;i++){
					  //TODO 
				  suma +=(last7.get(i).getDeaths()-last7.get(i-1).getDeaths());

				  }
				  average= suma/7;
				  
				 
				
						//crear un nuevo dia
				  Day newToday = new Day(Main.getInter().getNumberofDeads(),Main.getInter().getNumberofInfecteds(), average,Date.valueOf(LocalDate.now()));

						Main.getInter().addDay(newToday);
						
				//recorrer la lista de pacientes para calcularles el score TODO mirar cual es mas optima para recorrer, arraylist o linkedlist
				/* for(int i=0;i<listadepacientes.size;i++{
				 * 
				 *(paquete estadisticas) calculateScore(listadepacientes.get(i),newtoday,todaslaslistasdeporcentagesobtenidosenstadisticas);
				 * 
				 * }
				 * Score.setFirstTime(false); alomejor hay que cambiar boolean por Bool para que funcione
				 */
				
				
			}
}
