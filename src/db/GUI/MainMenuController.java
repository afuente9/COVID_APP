package db.GUI;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import db.pojos.Administration;
import db.pojos.Day;
import db.pojos.Doctor;
import db.pojos.Lab;
import db.pojos.Medication;
import db.pojos.Other_Pathologies;
import db.pojos.Patient;
import db.pojos.Sex;
import db.pojos.users.User;
import db.xml.dtd.JavaToXml;
import db.xml.dtd.XmlToHtml;
import db.xml.dtd.XmlToJava;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
		 * Day d1= new Day(323,232,0,Date.valueOf("2021-04-20"));
		 * 
		 * 
		 * Day d2= new Day(432,231,0,Date.valueOf("2021-04-21"));
		 * 
		 * Day d3= new Day(543,432,0,Date.valueOf("2021-04-22"));
		 * 
		 * Day d4= new Day(555,243,0,Date.valueOf("2021-04-23"));
		 * 
		 * Day d5= new Day(665,232,0,Date.valueOf("2021-04-24"));
		 * 
		 * Day d6= new Day(443,123,0,Date.valueOf("2021-04-25"));
		 * 
		 * Day d7= new Day(564,232,0,Date.valueOf("2021-04-26"));
		 * 
		 * Day d8= new Day(876,456,0,Date.valueOf("2021-04-27"));
		 * 
		 * Day d9= new Day(432,435,0,Date.valueOf("2021-04-28"));
		 * 
		 * Day d0= new Day(132,133,0,Date.valueOf("2021-04-29"));
		 * System.out.println("denieu");
		 * 
		 * Main.getInter().addDay(d1);
		 * 
		 * Main.getInter().addDay(d2); Main.getInter().addDay(d3);
		 * Main.getInter().addDay(d4); Main.getInter().addDay(d5);
		 * Main.getInter().addDay(d6); Main.getInter().addDay(d7);
		 * Main.getInter().addDay(d8); Main.getInter().addDay(d9);
		 * Main.getInter().addDay(d0);
		 * 
		 * 
		 * 
		 * 
		 * p_new= new
		 * Patient(Place_Text,Name_Text,date,SSNum_Text,height,weight,sex,infected,alive
		 * ,Hospital_Text,vaccinated,Blood_Text,dateIntroduced);
		 * Main.getInter().addPatient(p_new);
		 */
		/*
		 * 
		 * String[] names = new
		 * String[]{"Daniel","Antonio","Loreto","Esteban","María","Javier","Lorena",
		 * "Miriam","Carla","Jose Luís","Eva","Emilia","Josefa","Ofelia","Olga","Lidia",
		 * "Cesar","Felipe","Lucas","Aitor","Samuel","Felix","Julio","Tomás","Gonzalo",
		 * "Emilio","Hugo","Marcos","Jaime","Juan","Pablo","Mario","Carolina","Nerea",
		 * "Sofía","Susana","Sonia","Alicia"," Inés","Rosa","Andrea","Alba","Elena",
		 * "Sara","Ana María","Laura","Paula","Jose","Diego","Raúl","Leo","Pedro",
		 * "Alfonso","Miguel"};
		 * 
		 * String[] surnames = new
		 * String[]{"García","Rodríguez","Carmona","Gonzalez","Fernández","López",
		 * "Martínez","Sánchez","Otero","Pérez","Franco","Martín","Largo Caballero"
		 * ,"Jiménez","Messi","Ruíz","Hernández","Díaz","Ayuso","Moreno","Muñoz",
		 * "Alonso","Gutiérrez","Torres","Ramos","Casillas","Gil","Cabello","Anchuela",
		 * "Sanchís","Cantarero","Serrano","Blasco","Expósito","Ramírez","Suárez",
		 * "Morales","Castillo"," Ortega","Castro","Cortés","Núñez","Iglesias","Yáñez",
		 * "Lozano","Cano","Prieto","Cruz","Aguirre","Calvo","León","Campos","Carrasco",
		 * "Lucena"};
		 * 
		 * String[] locations = new
		 * String[]{"UCI","UCI","UCI","Hospital floor","Hospital floor","Hospital floor"
		 * ,"Hospital floor","Hospital floor","Home","Home","Home","Home","Home","Home",
		 * "Home","Home","Home","Home"}; //fecha //ssnum //hei //wei String[] sex = new
		 * String[]{"Male","Female"}; String[] hospital = new
		 * String[]{"12 Octubre","Puerta de Hierro","Clínic de Barcelona"
		 * ,"Vall d'hebron","Teknon","Joan XXIII","Sant Joan de Reus","Infanta Sofía"
		 * ,"General de Requena","Pardo de Aravaca","Quirón Toledo"
		 * ,"Universitari Sagrat Cor","La Vega","San Jordi de Sant Andreu","La fe"
		 * ,"Mediterráneo","El Pilar","San Juan de Dios Tenerife","2 de maig"
		 * ,"De la Reina","Sierrallana","La inmaculada","La pedrera"
		 * ,"Fundación de Alcorcón","Parque","Hospital de Sevilla"};
		 *  */
		  //infected String[] vaccinated = new
		/*String[]  Medication = new String[]{"O2","Ketamine","Lidocaine","Morphine",
		  "Iboprufen","Acetylsalicylic acid","Paracetamol","Kodeine","Fentanyl",
		  "Methadone","Dexamethasone","Diacepan","Haloperidol","Lactulose",
		  "Metoclopramide","Peniciline","Loratadine","Hydrocortisone",
		  "Epinephrine","Calcium gluconate","Penicillamine","Amoxicillin","Loracepam"};
		  String[] Pats = new String[]{"lung cancer","colon cancer","testicular cancer","liver cancer"
		  ,"arthritis","Asthma","Chronic fatigue sindrom","COPD","Diabetes",
		  "Crabs","Celiac disease","Hepatitis","Human papillomavirus","Gonorrhea",
		  "Fybromialgia","Lupus","Listediosis","ELA","Meningitis","Polio","Rubella",
		  "Tetanus","Tuberculosis","Siphilis","Yellow fiber","Zika"}; 
		  for (int i =0; i<Medication.length;i++) {
			  Main.getInter().addMedication(new  Medication(Medication[i])); }
		  for (int i =0; i<Pats.length;i++) {
			  Main.getInter().addOtherPathologies(new Other_Pathologies(Pats[i])); 
			  }
		*/

		int contadoranmo = 2006;
		for (int i = 0; i < 100; i++) {
			/*
			 * double ssrandom = Math.random()*900000+100000; int ssranint = (int) ssrandom;
			 * double prefix = Math.random()*90+10; int prefixint = (int)prefix; String
			 * prefixstring = "("+prefixint+")"+ssranint;
			 * 
			 * System.out.println(prefixstring);
			 * 
			 * Main.getInter().modifyPatient(i, "social_security", prefixstring);
			 * 
			 * double diarandom = Math.random()*29+1; int diarandomint = (int) diarandom;
			 * String diadef=""; if (diarandomint<10) { diadef="0"+diarandomint;
			 * 
			 * }else { diadef=""+diarandomint; } double mesran = Math.random()*11+1; int
			 * mesrandomint = (int) mesran; String mesdef="";
			 * 
			 * if (mesrandomint<10) { mesdef="0"+mesrandomint;
			 * 
			 * }else { mesdef=""+mesrandomint; } String date
			 * =contadoranmo+"-"+mesdef+"-"+diadef; System.out.println(date);
			 * Main.getInter().modifyPatient(i, "birthday", date.valueOf(date));
			 * 
			 * contadoranmo--;
			 */
		}

		this.labelDate.setText("" + Date.valueOf(LocalDate.now()));
		if (Main.getInter().getNumberofDays() < 7 && Main.getInter().getNumberofDays() < 0) {
			int daystoadd = 7 - Main.getInter().getNumberofDays();
			for (int i = 0; i < daystoadd; i++) {
				Day d = new Day(Main.getInter().getLastDay().getDeaths(),
						Main.getInter().getLastDay().getInfectedPatients(), 0,
						Date.valueOf(Main.getInter().getLastDay().getDate().toLocalDate().plusDays(1)));
				Main.getInter().addDay(Main.getInter().getLastDay());
			}

		} else if (Main.getInter().getNumberofDays() == 0) {

			for (int i = 0; i < 7; i++) {
				Day newToday = new Day(Main.getInter().getNumberofDeads(), Main.getInter().getNumberofInfecteds(), 0,
						Date.valueOf(LocalDate.now()));

				Main.getInter().addDay(newToday);
			}
		}
		if (Main.getInter().getLastDay().getDate().compareTo(Date.valueOf(LocalDate.now())) != 0) {

			long daysWithoutChanges = ChronoUnit.DAYS.between(Main.getInter().getLastDay().getDate().toLocalDate(),
					LocalDate.now());
			if (daysWithoutChanges > 1) {

				for (long i = 1; i <= daysWithoutChanges; i++) {
					float average = 0;

					float suma = 0;
					List<Day> last7 = new ArrayList();

					last7 = Main.getInter().getLast7Days();

					for (int j = 1; j <= 6; j++) {
						suma += (last7.get(j).getDeaths() - last7.get(j - 1).getDeaths());

					}
					average = suma / 7;

					Day d = new Day(Main.getInter().getLastDay().getDeaths(),
							Main.getInter().getLastDay().getInfectedPatients(), average,
							Date.valueOf(Main.getInter().getLastDay().getDate().toLocalDate().plusDays(1)));
					Main.getInter().addDay(Main.getInter().getLastDay());

				}
			}

			newDay();
		}
	}

	@FXML
	void OnSignUpClick(ActionEvent event) {

		String name = "ChooseSignUpView.fxml";
		ChooseSignUpController controller = null;
		openWindow(name, controller,"Choose user");

	}
    @FXML
    void opengit(MouseEvent event) {
    	try {

            Desktop.getDesktop().browse(new URI("https://github.com/afuente9/COVID_APP"));

        } catch (URISyntaxException ex) {


        }catch(IOException e){


        }
    }
    @FXML
    void openyt(MouseEvent event) {
    	try {

            Desktop.getDesktop().browse(new URI("https://www.youtube.com/channel/UCBQV-dq1lpqNpiz5kmO7V8Q"));

        } catch (URISyntaxException ex) {


            
        }catch(IOException e){


        }}
	 @FXML
	    void openurl(MouseEvent event) {
		 try {

	            Desktop.getDesktop().browse(new URI("https://alvarodelafuentebo.wixsite.com/covidist"));

	        } catch (URISyntaxException ex) {


	        }catch(IOException e){


	        }
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
		Main.getUserman().connect();
		String user = UserTextField.getText();
		String password = PasswordTextField.getText();
		User u = Main.getUserman().checkPassword(user, password);

		if (u == null) {
			JOptionPane.showMessageDialog(null, "Wrong user name or password.");


			return;
		} else if (u.getRole().getName().equalsIgnoreCase("administration")) {

			String name = "GovernmentMenuView.fxml";
			GovernmentMenuController controller = null;
			try {
				Pane root0 = (Pane) this.PasswordTextField.getScene().getRoot();

				 ColorAdjust adj = new ColorAdjust(0, -0.9, -0.5, 0);

				 GaussianBlur blur = new GaussianBlur(10); 
				    adj.setInput(blur);
				 root0.setEffect(adj);
				FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
				Parent root;

				root = loader.load();
				controller = loader.getController();
				controller.setAdmin(Main.getInter().getAdministrationbyUser(u));

				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.setResizable(false);
				stage.setTitle("Covidist");
stage.setTitle("Administration menu");				
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setScene(scene);
				stage.getIcons().add(new Image("https://image.flaticon.com/icons/png/512/2833/2833315.png"));

				stage.showAndWait();
				root0.setEffect(null);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (u.getRole().getName().equalsIgnoreCase("doctor")) {
			String name = "DoctorMenuView.fxml";
			DoctorMenuController controller = null;
			try { 
				Pane root0 = (Pane) this.PasswordTextField.getScene().getRoot();

				 ColorAdjust adj = new ColorAdjust(0, -0.9, -0.5, 0);

				 GaussianBlur blur = new GaussianBlur(10); 
				    adj.setInput(blur);
				 root0.setEffect(adj);

				FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
				Doctor d = Main.getInter().getDoctorbyUser(u);

				Parent root;

				root = loader.load();
        
				controller = loader.getController();
				controller.setD(d);
				
				controller.setDoctorName(d.getName());
				/*
				
				 try{
	                    byte[] bi = Main.getInter().getPicFromDoc(d.getId());
	                    BufferedImage image = null;
	                    InputStream in = new ByteArrayInputStream(bi);
	                    image = ImageIO.read(in);
	                    ImageIcon imgi = new ImageIcon(image.getScaledInstance(60, 60, 0));
	                    System.out.println("hola");
	    				controller.setDoctorPic(imgi);


	                }catch(Exception ex){
                      ex.printStackTrace();

	                }*/
				
				
				
				
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.setResizable(false);
stage.setTitle("Doctor menu");
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setScene(scene);
				stage.setResizable(false);
                //stage.setTitle(name);	
				stage.getIcons().add(new Image("https://image.flaticon.com/icons/png/512/2833/2833315.png"));

				stage.showAndWait();
				root0.setEffect(null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (u.getRole().getName().equalsIgnoreCase("laboratory")) {

			String name = "LabMenuView.fxml";
			LabMenuController controller = null;
			byte[] image = null;
			try {
				Pane root0 = (Pane) this.PasswordTextField.getScene().getRoot();

				 ColorAdjust adj = new ColorAdjust(0, -0.9, -0.5, 0);

				 GaussianBlur blur = new GaussianBlur(10); 
				    adj.setInput(blur);
				 root0.setEffect(adj);
				FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
				Parent root;

				root = loader.load();

				controller = loader.getController();
				controller.setL(Main.getInter().getLabByUser(u));
				controller.setLabName(Main.getInter().getLabByUser(u).getName());

				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.setResizable(false);
				stage.setTitle("Laboratory menu");

				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setScene(scene);
				stage.getIcons().add(new Image("https://image.flaticon.com/icons/png/512/2833/2833315.png"));

				stage.showAndWait();
				root0.setEffect(null);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/*
		 * if (UserTextField.getText().equals("doctor") &&
		 * PasswordTextField.getText().equals("doctor")) { byte[] image = null; Doctor d
		 * = new Doctor(0, "cardio", "Lucas Pérez", Date.valueOf("2000-10-10"), "34234",
		 * Sex.valueOf("Male"), "La Paz", image); String name = "DoctorMenuView.fxml";
		 * DoctorMenuController controller = null; try { FXMLLoader loader = new
		 * FXMLLoader(getClass().getResource(name)); Parent root;
		 * 
		 * root = loader.load();
		 * 
		 * controller = loader.getController(); controller.setD(d);
		 * controller.setDoctorName(d.getName()); //
		 * controller.setDoctorPic(Main.getInter().getPicFromDoc(1)); /* BufferedImage
		 * bImage = ImageIO.read(new File("sample.jpg")); ByteArrayOutputStream bos =
		 * new ByteArrayOutputStream(); ImageIO.write(bImage, "jpg", bos ); byte [] data
		 * = bos.toByteArray(); ByteArrayInputStream bis = new
		 * ByteArrayInputStream(data); BufferedImage bImage2 = ImageIO.read(bis);
		 * ImageIO.write(bImage2, "jpg", new File("output.jpg") );
		 * System.out.println("image created");
		 * 
		 * Scene scene = new Scene(root); Stage stage = new Stage();
		 * 
		 * stage.initModality(Modality.APPLICATION_MODAL); stage.setScene(scene);
		 * stage.showAndWait(); } catch (IOException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 * 
		 * } if (UserTextField.getText().equals("government") &&
		 * PasswordTextField.getText().equals("government")) { String name =
		 * "GovernmentMenuView.fxml"; GovernmentMenuController controller = null; try {
		 * FXMLLoader loader = new FXMLLoader(getClass().getResource(name)); Parent
		 * root;
		 * 
		 * root = loader.load();
		 * 
		 * controller = loader.getController();
		 * controller.setAdmin(Main.getInter().getAdministration(1));
		 * System.out.println("la admin es "+Main.getInter().getAdministration(1));
		 * 
		 * Scene scene = new Scene(root); Stage stage = new Stage();
		 * stage.initModality(Modality.APPLICATION_MODAL); stage.setScene(scene);
		 * stage.showAndWait(); } catch (IOException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); } } if (UserTextField.getText().equals("") &&
		 * PasswordTextField.getText().equals("")) {
		 * 
		 * String name = "LabMenuView.fxml"; LabMenuController controller = null; byte[]
		 * image = null; Lab l_new = new Lab(0, 0, "28223, Pozuelo", "Pfizer",
		 * "23433453F", image); // Main.getInter().addLab(l_new); try { FXMLLoader
		 * loader = new FXMLLoader(getClass().getResource(name)); Parent root;
		 * //Administration admin= new Administration(1,100); //
		 * Main.getInter().addGoverment(admin); Main.getInter().addLab(l_new); root =
		 * loader.load();
		 * 
		 * controller = loader.getController();
		 * controller.setL(Main.getInter().getLab(1));
		 * controller.setLabName(Main.getInter().getLab(1).getName());
		 * 
		 * Scene scene = new Scene(root); Stage stage = new Stage();
		 * stage.initModality(Modality.APPLICATION_MODAL); stage.setScene(scene);
		 * stage.showAndWait(); } catch (IOException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); } }
		 */

	}

	void openWindow(String name, Object controller, String title) {
		try {
			Pane root0 = (Pane) this.PasswordTextField.getScene().getRoot();

			 ColorAdjust adj = new ColorAdjust(0, -0.9, -0.5, 0);

			 GaussianBlur blur = new GaussianBlur(10); 
			    adj.setInput(blur);
			 root0.setEffect(adj);
			FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
			Parent root;

			root = loader.load();

			controller = loader.getController();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setResizable(false);
stage.setTitle(title);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.getIcons().add(new Image("https://image.flaticon.com/icons/png/512/2833/2833315.png"));

			stage.showAndWait();
			
			root0.setEffect(null);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void newDay() {

		// TODO esto pasara if today != ultimo dia metido

		// calcular la media de muertos de los ultimos 7 dias

		float average = 0;

		float suma = 0;
		List<Day> last7 = new ArrayList();

		last7 = Main.getInter().getLast7Days();

		for (int i = 1; i <= 6; i++) {
			// TODO
			suma += (last7.get(i).getDeaths() - last7.get(i - 1).getDeaths());

		}

		average = suma / 7;

		// crear un nuevo dia
		Day newToday = new Day(Main.getInter().getNumberofDeads(), Main.getInter().getNumberofInfecteds(), average,
				Date.valueOf(LocalDate.now()));

		Main.getInter().addDay(newToday);

		double exponent = Math.pow(Math.E, (-0.039 * average) + 5);
		float deadImportance = (float) ((35 / (1 + exponent)) + 65) / 100;

		// recorrer la lista de pacientes para calcularles el score TODO mirar cual es
		// mas optima para recorrer, arraylist o linkedlist
		for (int i = 1; i <= Main.getInter().getNumberofPatients(); i++) {

			float score = calculateScore(Main.getInter().getPatient(i), deadImportance);
			Main.getInter().modifyScore(i, (int) score);

		}

	}

	public float calculateScore(Patient p, float DeadImportance) {
		// cojo los numeros de todos

		List<Float> contributions = new ArrayList<>();
		List<Float> agePercentagesAlive = getAgesALIVE();
		List<Float> agePercentagesDead = getAgesDEAD();
		/*
		 * List<Float> HeighPerAlive = getHeightALIVE(); List<Float> HeighPerDead =
		 * getHeightDEAD();
		 */
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

		float AGEPercentagePatientAlive = getPercentageAgePatient(p.getTheAge(p.getBirthday()), agePercentagesAlive);

		float patientScoreBasicAgeAlive = patientScore(getMaxNum(agePercentagesAlive), getMinNum(agePercentagesAlive),
				AGEPercentagePatientAlive, agePercentagesAlive.size());
		float contributionAgeAlive = patientScoreBasicAgeAlive * (1 - DeadImportance);
		contributions.add(contributionAgeAlive);
		// System.out.println(p.getId() + " contribucion edad vivo" +
		// contributionAgeAlive);
		float AGEPercentagePatientDead = getPercentageAgePatient(p.getTheAge(p.getBirthday()), agePercentagesDead);
		float patientScoreBasicAgeDead = patientScore(getMaxNum(agePercentagesDead), getMinNum(agePercentagesDead),
				AGEPercentagePatientDead, agePercentagesDead.size());
		float contributionAgeDead = patientScoreBasicAgeAlive * (DeadImportance);
		contributions.add(contributionAgeDead);
		// System.out.println(p.getId() + " contribucion edad muerto" +
		// contributionAgeDead);
		/*
		 * float HeightPercentageAlive = HeightPercentage(p.getHeight(), HeighPerAlive);
		 * float patientScoreBasicHeightAlive = patientScore(getMaxNum(HeighPerAlive),
		 * getMinNum(HeighPerAlive), HeightPercentageAlive, HeighPerAlive.size()); float
		 * contributionHeightAlive = patientScoreBasicHeightAlive * (1 -
		 * DeadImportance); contributions.add(contributionHeightAlive);
		 * System.out.println(p.getId() + " contribucion altura vivo" +
		 * contributionHeightAlive);
		 * 
		 * float HeightPercentageDead = HeightPercentage(p.getHeight(), HeighPerDead);
		 * float patientScoreBasicHeightDead = patientScore(getMaxNum(HeighPerDead),
		 * getMinNum(HeighPerDead), HeightPercentageDead, HeighPerDead.size()); float
		 * contributionHeightDead = patientScoreBasicHeightDead * (1 - DeadImportance);
		 * contributions.add(contributionHeightDead); System.out.println(p.getId() +
		 * " contribucion altura muerto" + contributionHeightDead);
		 */
		float WeightPercentageAlive = WeightPercentage(p.getWeight(), WeightPerAlive);
		float patientScoreBasicWeightAlive = patientScore(getMaxNum(WeightPerAlive), getMinNum(WeightPerAlive),
				WeightPercentageAlive, WeightPerAlive.size());
		float contributionWeightAlive = patientScoreBasicWeightAlive * (1 - DeadImportance);
		contributions.add(contributionWeightAlive);
		// System.out.println(p.getId() + " contribucion peso vivo" +
		// contributionWeightAlive);

		float WeightPercentageDead = WeightPercentage(p.getWeight(), WeightPerDead);
		float patientScoreBasicWeightDead = patientScore(getMaxNum(WeightPerDead), getMinNum(WeightPerDead),
				WeightPercentageDead, WeightPerDead.size());
		float contributionWeightDead = patientScoreBasicWeightDead * (DeadImportance);
		contributions.add(contributionWeightDead);
		// System.out.println(p.getId() + " contribucion peso muerto" +
		// contributionWeightDead);

		float locationPercentageAlive = LocationPercentage(p.getHos_location(), perLocationAlive);
		float patientScoreBasicLocationAlive = patientScore(getMaxNum(perLocationAlive), getMinNum(perLocationAlive),
				locationPercentageAlive, perLocationAlive.size());
		float contributionLocationAlive = patientScoreBasicLocationAlive * (1 - DeadImportance);
		contributions.add(contributionLocationAlive);
		// System.out.println(p.getId() + " contribucion location vivo" +
		// contributionLocationAlive);

		float locationPercentageDead = LocationPercentage(p.getHos_location(), perLocationDead);
		float patientScoreBasicLocationDead = patientScore(getMaxNum(perLocationDead), getMinNum(perLocationDead),
				locationPercentageDead, perLocationDead.size());
		float contributionLocationDead = patientScoreBasicLocationDead * (DeadImportance);
		contributions.add(contributionLocationDead);
		// System.out.println(p.getId() + " contribucion location muerto" +
		// contributionLocationDead);

		float sexPercentageAlive = SexPercentage(p.getSex(), PerSexAlive);
		float patientScoreBasicSexAlive = patientScore(getMaxNum(PerSexAlive), getMinNum(PerSexAlive),
				sexPercentageAlive, PerSexAlive.size());
		float contributionSexAlive = patientScoreBasicSexAlive * (1 - DeadImportance);
		contributions.add(contributionSexAlive);
		// System.out.println(p.getId() + " contribucion sexo vivo" +
		// contributionSexAlive);

		float sexPercentageDead = SexPercentage(p.getSex(), PerSexDead);
		float patientScoreBasicSexDead = patientScore(getMaxNum(PerSexDead), getMinNum(PerSexDead), sexPercentageDead,
				PerSexDead.size());
		float contributionSexDead = patientScoreBasicSexDead * (DeadImportance);
		contributions.add(contributionSexDead);
		// System.out.println(p.getId() + " contribucion sexo muerto" +
		// contributionSexDead);

		float bloodPercentageAlive = BloodPercentage(p.getBloodType(), PerBloodAlive);
		float patientScoreBasicBloodAlive = patientScore(getMaxNum(PerBloodAlive), getMinNum(PerBloodAlive),
				bloodPercentageAlive, PerBloodAlive.size());
		// System.out.println("pppppppppppppppppppppppppppppppppppppppppppppppppp"+patientScoreBasicBloodAlive);

		float contributionBloodAlive = patientScoreBasicBloodAlive * (1 - DeadImportance);
		contributions.add(contributionBloodAlive);
		// System.out.println(p.getId() + " contribucion sangre vivo" +
		// contributionBloodAlive);

		float bloodPercentageDead = BloodPercentage(p.getBloodType(), PerBloodDead);
		float patientScoreBasicBloodDead = patientScore(getMaxNum(PerBloodDead), getMinNum(PerBloodDead),
				bloodPercentageDead, PerBloodDead.size());
		float contributionBloodDead = patientScoreBasicBloodDead * (DeadImportance);
		contributions.add(contributionBloodDead);
		// System.out.println(p.getId() + " contribucion sangre muerto" +
		// contributionBloodDead);

		float hospitalPercentage = HospitalPercentage(p.getHospital(), perHospitalAlive, true);
		float patientScoreBasicHospitalAlive = patientScore(getMaxNum(perHospitalAlive), getMinNum(perHospitalAlive),
				bloodPercentageAlive, perHospitalAlive.size());
		float contributionHospitalAlive = patientScoreBasicHospitalAlive * (1 - DeadImportance);
		contributions.add(contributionHospitalAlive);
		// System.out.println(p.getId() + " contribucion hos vivo" +
		// contributionHospitalAlive);

		float HospitalPercentageDead = HospitalPercentage(p.getHospital(), perHospitalDead, false);
		float patientScoreBasicHospitalDead = patientScore(getMaxNum(perHospitalDead), getMinNum(perHospitalDead),
				bloodPercentageDead, perHospitalDead.size());
		float contributionHospitalDead = patientScoreBasicHospitalDead * (DeadImportance);
		contributions.add(contributionHospitalDead);
		// System.out.println(p.getId() + " contribucionhopital muerto" +
		// contributionHospitalDead);

		List<String> Medication_Patient = Main.getInter().getMedicationfromPatientNAME(p.getId());

		for (int i = 0; i < Medication_Patient.size(); i++) {
			String medication = Medication_Patient.get(i);
			float medicationPercentageAlive = MedicationPercentage(medication, perMedAlive, true);
			float patientScoreBasicmedicationAlive = patientScore(getMaxNum(perMedAlive), getMinNum(perMedAlive),
					medicationPercentageAlive, perMedAlive.size());

			float contributionMedlAlive = patientScoreBasicmedicationAlive * (1 - DeadImportance);
			contributions.add(contributionMedlAlive);
			// System.out.println(p.getId() + " contribucion medication vivo" +
			// contributionMedlAlive);

			float medicationPercentageDead = MedicationPercentage(medication, perMedDead, false);
			float patientScoreBasicmedicationDead = patientScore(getMaxNum(perMedDead), getMinNum(perMedDead),
					medicationPercentageDead, perMedDead.size());
			float contributionMedlDead = patientScoreBasicmedicationDead * (DeadImportance);
			contributions.add(contributionMedlDead);
			// System.out.println(p.getId() + " contribucion medication muerto" +
			// contributionMedlDead);

		}

		List<String> Patho_Patient = Main.getInter().getPathofromPatientNAME(p.getId());

		for (int i = 0; i < Patho_Patient.size(); i++) {
			String pathology = Patho_Patient.get(i);
			float pathologyPercentageAlive = PathologyPercentage(pathology, PerOPATAlive, true);
			float patientScoreBasicpathologyAlive = patientScore(getMaxNum(PerOPATAlive), getMinNum(PerOPATAlive),
					pathologyPercentageAlive, PerOPATAlive.size());
			float contributionPatAlive = patientScoreBasicpathologyAlive * (1 - DeadImportance);
			contributions.add(contributionPatAlive);
			// System.out.println(p.getId() + " contribucion pat vivo" +
			// contributionPatAlive);

			float pathologyPercentageDead = PathologyPercentage(pathology, PerOPATDead, false);
			float patientScoreBasicPathologyDead = patientScore(getMaxNum(PerOPATDead), getMinNum(PerOPATDead),
					pathologyPercentageDead, PerOPATDead.size());
			float contributionPatDead = patientScoreBasicPathologyDead * (DeadImportance);
			contributions.add(contributionPatDead);
			// System.out.println(p.getId() + " contribucion pat muerto" +
			// contributionPatDead);

		}
		float totalContributions = 0;
		for (int i = 0; i < contributions.size(); i++) {
			totalContributions += contributions.get(i);
		}

		Main.getInter().modifyScore(p.getId(), totalContributions);
		return totalContributions;

	}

	public Float PathologyPercentage(String pathologyName, List<Float> percentages, boolean alive) {
		float percentage = 0;

		List<String> difPathologiesname = Main.getInter().getdifferentPaths(alive);
		for (int i = 0; i < percentages.size(); i++) {

			if (difPathologiesname.get(i).equals(pathologyName)) {
				percentage = percentages.get(i);
			}

		}

		return percentage;

	}

	public Float MedicationPercentage(String medicationName, List<Float> percentages, boolean alive) {
		float percentage = 0;
		List<String> difMedicationsname = Main.getInter().getdifferentMeds(alive);

		for (int i = 0; i < percentages.size(); i++) {
			if (difMedicationsname.get(i).equals(medicationName)) {

				percentage = percentages.get(i);
			}

		}

		return percentage;

	}

	public Float HospitalPercentage(String hospital, List<Float> percentages, boolean alive) {
		float percentage = 0;
		List<String> difHospitalsname = Main.getInter().getdifferentHospitals(alive);

		for (int i = 0; i < percentages.size(); i++) {
			if (difHospitalsname.get(i).equals(hospital)) {
				percentage = percentages.get(i);
			}

		}

		return percentage;
	}

	public Float BloodPercentage(String blood, List<Float> percentages) {
		float percentage = 0;
		if (blood.equals("A+")) {
			percentage = percentages.get(0);
		} else if (blood.equals("A-")) {
			percentage = percentages.get(1);

		} else if (blood.equals("B+")) {
			percentage = percentages.get(2);

		}
		if (blood.equals("B-")) {
			percentage = percentages.get(3);
		} else if (blood.equals("0+")) {
			percentage = percentages.get(4);

		} else if (blood.equals("0-")) {
			percentage = percentages.get(5);

		} else if (blood.equals("AB-")) {
			percentage = percentages.get(6);

		} else if (blood.equals("AB+")) {
			percentage = percentages.get(7);

		}

		return percentage;
	}

	public Float SexPercentage(Sex sex, List<Float> percentages) {
		float percentage = 0;
		if (sex.equals(sex.valueOf("Male"))) {
			percentage = percentages.get(0);

		} else if (sex.equals(sex.valueOf("Female"))) {
			percentage = percentages.get(1);

		}

		return percentage;
	}

	public Float LocationPercentage(String location, List<Float> percentages) {
		float percentage = 0;
		if (location.equals("Home")) {
			percentage = percentages.get(0);
		} else if (location.equals("Floor hospital")) {
			percentage = percentages.get(1);

		} else if (location.equals("ICU")) {
			percentage = percentages.get(2);

		}

		return percentage;
	}

	public Float WeightPercentage(float weight, List<Float> percentages) {
		float percentage = 0;
		if (weight > 0 && weight < 20) {
			percentage = percentages.get(0);
		} else if (weight > 20 && weight < 50) {
			percentage = percentages.get(1);

		} else if (weight > 50 && weight < 70) {
			percentage = percentages.get(2);

		} else if (weight > 70 && weight < 90) {
			percentage = percentages.get(3);

		} else if (weight > 90 && weight < 110) {
			percentage = percentages.get(4);

		} else if (weight > 110) {
			percentage = percentages.get(5);

		}
		return percentage;
	}

	public Float HeightPercentage(float height, List<Float> percentages) {
		float percentage = 0;
		if (height > 0 && height < 1.0) {
			percentage = percentages.get(0);
		} else if (height > 1.0 && height < 1.50) {
			percentage = percentages.get(1);

		} else if (height > 1.50 && height < 1.750) {
			percentage = percentages.get(2);

		} else if (height > 1.75 && height < 2.0) {
			percentage = percentages.get(3);

		} else if (height > 2.0) {
			percentage = percentages.get(4);

		}
		return percentage;
	}

	public float patientScore(float MaxPercentage, float MinPercentage, float PatientPercentage,
			int NumberOptionsFeature) {

		float difference = MaxPercentage - MinPercentage;

		float base = difference * PatientPercentage;

		float exponent = 1 + ((float) NumberOptionsFeature / (float) 10);

		float score = (float) Math.pow(base, exponent);

		return score;
	}

	public Float getPercentageAgePatient(int age, List<Float> percentages) {
		float percentage = 0;
		if (age > 0 && age < 20) {

			percentage = percentages.get(0);

		} else if (age > 20 && age < 40) {
			percentage = percentages.get(1);

		} else if (age > 40 && age < 60) {
			percentage = percentages.get(2);

		} else if (age > 60 && age < 80) {
			percentage = percentages.get(3);

		} else if (age > 80) {
			percentage = percentages.get(4);

		}

		return percentage;

	}

	public List<Float> getOPatALIVE() {
		List<String> paths = Main.getInter().getdifferentPaths(true);

		List<Integer> NumsPaths = new ArrayList<>();
		for (int i = 0; i < paths.size(); i++) {
			NumsPaths.add(Main.getInter().getdifferentPathsCOUNT(paths.get(i), true));
		}

		return calculatePercentages(NumsPaths);

	}

	public List<Float> getOPatDead() {
		List<String> paths = Main.getInter().getdifferentPaths(false);

		List<Integer> NumsPaths = new ArrayList<>();
		for (int i = 0; i < paths.size(); i++) {
			NumsPaths.add(Main.getInter().getdifferentPathsCOUNT(paths.get(i), false));
		}

		return calculatePercentages(NumsPaths);

	}

	public List<Float> getBloodALIVE() {
		List<Integer> bloodsNum = new ArrayList<>();

		bloodsNum.add(Main.getInter().getNumberPatientsbyanyString("bloodType", "A+", true));
		bloodsNum.add(Main.getInter().getNumberPatientsbyanyString("bloodType", "A-", true));
		bloodsNum.add(Main.getInter().getNumberPatientsbyanyString("bloodType", "B+", true));
		bloodsNum.add(Main.getInter().getNumberPatientsbyanyString("bloodType", "B-", true));
		bloodsNum.add(Main.getInter().getNumberPatientsbyanyString("bloodType", "0+", true));
		bloodsNum.add(Main.getInter().getNumberPatientsbyanyString("bloodType", "0-", true));
		bloodsNum.add(Main.getInter().getNumberPatientsbyanyString("bloodType", "AB-", true));
		bloodsNum.add(Main.getInter().getNumberPatientsbyanyString("bloodType", "AB+", true));
		return calculatePercentages(bloodsNum);

	}

	public List<Float> getBloodDead() {
		List<Integer> bloodsNum = new ArrayList<>();

		bloodsNum.add(Main.getInter().getNumberPatientsbyanyString("bloodType", "A+", false));
		bloodsNum.add(Main.getInter().getNumberPatientsbyanyString("bloodType", "A-", false));
		bloodsNum.add(Main.getInter().getNumberPatientsbyanyString("bloodType", "B+", false));
		bloodsNum.add(Main.getInter().getNumberPatientsbyanyString("bloodType", "B-", false));
		bloodsNum.add(Main.getInter().getNumberPatientsbyanyString("bloodType", "0+", false));
		bloodsNum.add(Main.getInter().getNumberPatientsbyanyString("bloodType", "0-", false));
		bloodsNum.add(Main.getInter().getNumberPatientsbyanyString("bloodType", "AB-", false));
		bloodsNum.add(Main.getInter().getNumberPatientsbyanyString("bloodType", "AB+", false));
		return calculatePercentages(bloodsNum);

	}

	public List<Float> getSexALIVE() {
		List<Integer> NumsSex = new ArrayList<>();
		NumsSex.add(Main.getInter().searchPatientGenericCOUNT("sex", "M", true));
		NumsSex.add(Main.getInter().searchPatientGenericCOUNT("sex", "F", true));

		return calculatePercentages(NumsSex);

	}

	public List<Float> getSexDead() {
		List<Integer> NumsSex = new ArrayList<>();
		NumsSex.add(Main.getInter().searchPatientGenericCOUNT("sex", "M", false));
		NumsSex.add(Main.getInter().searchPatientGenericCOUNT("sex", "F", false));
		return calculatePercentages(NumsSex);

	}

	public List<Float> getMedALIVE() {
		List<String> Medics = Main.getInter().getdifferentMeds(true);
		List<Integer> NumsMeds = new ArrayList<>();
		for (int i = 0; i < Medics.size(); i++) {
			NumsMeds.add(Main.getInter().getdifferentMedsCOUNT(Medics.get(i), true));
		}

		return calculatePercentagesMedication(NumsMeds);

	}

	public List<Float> getMedDead() {
		List<String> Medics = Main.getInter().getdifferentMeds(false);
		List<Integer> NumsMeds = new ArrayList<>();
		for (int i = 0; i < Medics.size(); i++) {
			NumsMeds.add(Main.getInter().getdifferentMedsCOUNT(Medics.get(i), false));
		}

		return calculatePercentages(NumsMeds);

	}

	public List<Float> getLocALIVE() {
		List<Integer> locationsNumb = new ArrayList<>();

		locationsNumb.add(Main.getInter().getNumberPatientsbyanyString("hos_location", "Home", true));
		locationsNumb.add(Main.getInter().getNumberPatientsbyanyString("hos_location", "Floor hospital", true));
		locationsNumb.add(Main.getInter().getNumberPatientsbyanyString("hos_location", "ICU", true));

		return calculatePercentages(locationsNumb);

	}

	public List<Float> getLocDEAD() {
		List<Integer> locationsNumb = new ArrayList<>();

		locationsNumb.add(Main.getInter().getNumberPatientsbyanyString("hos_location", "Home", false));
		locationsNumb.add(Main.getInter().getNumberPatientsbyanyString("hos_location", "Floor hospital", false));
		locationsNumb.add(Main.getInter().getNumberPatientsbyanyString("hos_location", "ICU", false));

		return calculatePercentages(locationsNumb);

	}

	public List<Float> getHosALIVE() {
		List<Integer> Hospitals = new ArrayList<>();
		List<String> difHospitalsname = Main.getInter().getdifferentHospitals(true);

		for (int i = 0; i < difHospitalsname.size(); i++) {
			Hospitals.add(Main.getInter().getNumberPatientsbyanyString("hospital", "" + difHospitalsname.get(i), true));

		}

		return calculatePercentages(Hospitals);

	}

	public List<Float> getHosDEAD() {
		List<Integer> Hospitals = new ArrayList<>();
		List<String> difHospitalsname = Main.getInter().getdifferentHospitals(false);

		for (int i = 0; i < difHospitalsname.size(); i++) {
			Hospitals
					.add(Main.getInter().getNumberPatientsbyanyString("hospital", "" + difHospitalsname.get(i), false));
		}

		return calculatePercentages(Hospitals);

	}

	public List<Float> getWeightALIVE() {
		List<Integer> Weights = new ArrayList<>();

		Weights.add(Main.getInter().getNumberPatientsbyRangeofFeature("weight", 20, 0, true));
		Weights.add(Main.getInter().getNumberPatientsbyRangeofFeature("weight", 50, 20, true));
		Weights.add(Main.getInter().getNumberPatientsbyRangeofFeature("weight", 70, 50, true));
		Weights.add(Main.getInter().getNumberPatientsbyRangeofFeature("weight", 90, 70, true));
		Weights.add(Main.getInter().getNumberPatientsbyRangeofFeature("weight", 110, 90, true));
		Weights.add(Main.getInter().getNumberPatientsbyRangeofFeature("weight", 900, 110, true));

		return calculatePercentages(Weights);

	}

	public List<Float> getWeightDEAD() {
		List<Integer> Weights = new ArrayList<>();

		Weights.add(Main.getInter().getNumberPatientsbyRangeofFeature("weight", 20, 0, false));
		Weights.add(Main.getInter().getNumberPatientsbyRangeofFeature("weight", 50, 20, false));
		Weights.add(Main.getInter().getNumberPatientsbyRangeofFeature("weight", 70, 50, false));
		Weights.add(Main.getInter().getNumberPatientsbyRangeofFeature("weight", 90, 70, false));
		Weights.add(Main.getInter().getNumberPatientsbyRangeofFeature("weight", 110, 90, false));
		Weights.add(Main.getInter().getNumberPatientsbyRangeofFeature("weight", 900, 110, false));

		return calculatePercentages(Weights);

	}

	public List<Float> getHeightALIVE() {
		List<Integer> heights = new ArrayList<>();
		heights.add(Main.getInter().getNumberPatientsbyRangeofFeature("height", (float) 1.0, 0, true));
		heights.add(Main.getInter().getNumberPatientsbyRangeofFeature("height", (float) 1.50, (float) 1.0, true));
		heights.add(Main.getInter().getNumberPatientsbyRangeofFeature("height", (float) 1.75, (float) 1.50, true));
		heights.add(Main.getInter().getNumberPatientsbyRangeofFeature("height", (float) 2.00, (float) 1.75, true));
		heights.add(Main.getInter().getNumberPatientsbyRangeofFeature("height", (float) 5.0, (float) 2.0, true));

		return calculatePercentages(heights);
	}

	public List<Float> getHeightDEAD() {
		List<Integer> heights = new ArrayList<>();
		heights.add(Main.getInter().getNumberPatientsbyRangeofFeature("height", (float) 1.0, 0, false));
		heights.add(Main.getInter().getNumberPatientsbyRangeofFeature("height", (float) 1.50, (float) 1.0, false));
		heights.add(Main.getInter().getNumberPatientsbyRangeofFeature("height", (float) 1.75, (float) 1.50, false));
		heights.add(Main.getInter().getNumberPatientsbyRangeofFeature("height", (float) 2.00, (float) 1.75, false));
		heights.add(Main.getInter().getNumberPatientsbyRangeofFeature("height", (float) 5.0, (float) 2.0, false));

		return calculatePercentages(heights);
	}

	public List<Float> calculatePercentages(List<Integer> heights) {
		List<Float> listofpercentages = new ArrayList<>();
		int add = 0;

		for (int i = 0; i < heights.size(); i++) {
			add += heights.get(i);

		}

		for (int i = 0; i < heights.size(); i++) {

			float percentage = 100 * ((float) heights.get(i)) / ((float) add);

			listofpercentages.add(percentage);

		}

		return listofpercentages;

	}

	public List<Float> calculatePercentagesMedication(List<Integer> heights) {
		List<Float> listofpercentages = new ArrayList<>();
		int add = 0;

		for (int i = 0; i < heights.size(); i++) {
			add += heights.get(i);

		}

		for (int i = 0; i < heights.size(); i++) {

			float percentage = 100 * ((float) heights.get(i)) / ((float) add);

			listofpercentages.add(percentage);

		}
		return listofpercentages;

	}

	public List<Float> getAgesALIVE() {

		List<Date> dates = Main.getInter().getDates(true);
		List<Integer> ages = new ArrayList<>();
		LocalDate today = LocalDate.now();
		for (int i = 0; i < dates.size(); i++) {

			LocalDate fHoy = LocalDate.now();
			LocalDate cumple = dates.get(i).toLocalDate();
			long age = ChronoUnit.YEARS.between(cumple, fHoy);

			ages.add((int) age);
		}
		int timer1 = 0;
		int timer2 = 0;
		int timer3 = 0;
		int timer4 = 0;
		int timer5 = 0;

		for (int i = 0; i < ages.size(); i++) {

			if (ages.get(i) > 0 && ages.get(i) < 20) {
				timer1++;

			} else if (ages.get(i) > 20 && ages.get(i) < 40) {
				timer2++;
			} else if (ages.get(i) > 40 && ages.get(i) < 60) {
				timer3++;
			} else if (ages.get(i) > 60 && ages.get(i) < 80) {
				timer4++;
			} else if (ages.get(i) > 80) {
				timer5++;
			}

		}

		List<Integer> agePercentages = new ArrayList<>();
		agePercentages.add(timer1);
		agePercentages.add(timer2);
		agePercentages.add(timer3);
		agePercentages.add(timer4);
		agePercentages.add(timer5);

		return calculatePercentages(agePercentages);

	}

	public List<Float> getAgesDEAD() {

		List<Date> dates = Main.getInter().getDates(false);
		List<Integer> ages = new ArrayList<>();
		LocalDate today = LocalDate.now();
		for (int i = 0; i < dates.size(); i++) {

			LocalDate fHoy = LocalDate.now();
			LocalDate cumple = dates.get(i).toLocalDate();
			long age = ChronoUnit.YEARS.between(cumple, fHoy);

			ages.add((int) age);
		}
		int timer1 = 0;
		int timer2 = 0;
		int timer3 = 0;
		int timer4 = 0;
		int timer5 = 0;

		for (int i = 0; i < ages.size(); i++) {

			if (ages.get(i) > 0 && ages.get(i) < 20) {
				timer1++;
			} else if (ages.get(i) > 20 && ages.get(i) < 40) {
				timer2++;
			} else if (ages.get(i) > 40 && ages.get(i) < 60) {
				timer3++;
			} else if (ages.get(i) > 60 && ages.get(i) < 80) {
				timer4++;
			} else if (ages.get(i) > 80) {
				timer5++;
			}

		}
		List<Integer> agePercentages = new ArrayList<>();

		agePercentages.add(timer1);
		agePercentages.add(timer2);
		agePercentages.add(timer3);
		agePercentages.add(timer4);
		agePercentages.add(timer5);

		// saco porcentaje de cada timer

		return calculatePercentages(agePercentages);
	}

	public float getMinNum(List<Float> list) {
		float min = 999;

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) < min) {
				min = list.get(i);
			}
		}

		return min;
	}

	public float getMaxNum(List<Float> list) {

		float max = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) > max) {
				max = list.get(i);
			}
		}

		return max;
	}
	@FXML
    void ondeleteaccount(MouseEvent event) {
		String name = "deleteacView.fxml";
		deleteacController controller = null;
		try {
			Pane root0 = (Pane) this.PasswordTextField.getScene().getRoot();

			 ColorAdjust adj = new ColorAdjust(0, -0.9, -0.5, 0);

			 GaussianBlur blur = new GaussianBlur(10); 
			    adj.setInput(blur);
			 root0.setEffect(adj);
			FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
			Parent root;

			root = loader.load();
			controller = loader.getController();

			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setResizable(false);
			stage.setTitle("Covidist");
stage.setTitle("Delete account");				
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.getIcons().add(new Image("https://image.flaticon.com/icons/png/512/2833/2833315.png"));

			stage.showAndWait();
			root0.setEffect(null);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
    }
	
}
}
