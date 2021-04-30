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
		Day d1= new Day(323,0,Date.valueOf("2021-04-20"));
		
	
		Day d2= new Day(432,0,Date.valueOf("2021-04-21"));
	
		Day d3= new Day(543,0,Date.valueOf("2021-04-22"));
		
		Day d4= new Day(555,0,Date.valueOf("2021-04-23"));
		
		Day d5= new Day(665,0,Date.valueOf("2021-04-24"));
		
		Day d6= new Day(443,0,Date.valueOf("2021-04-25"));
		
		Day d7= new Day(564,0,Date.valueOf("2021-04-26"));
		
		Day d8= new Day(876,0,Date.valueOf("2021-04-27"));
		
		Day d9= new Day(432,0,Date.valueOf("2021-04-28"));
		
		Day d0= new Day(132,0,Date.valueOf("2021-04-29"));
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
*/


		  		
		
		this.labelDate.setText(""+Date.valueOf(LocalDate.now()));
		System.out.println("el day es "+Main.getInter().getLastDay().getDate());
		if ( Main.getInter().getLastDay().getDate().compareTo(Date.valueOf(LocalDate.now()))!=0 ){
			
			
			long daysWithoutChanges = ChronoUnit.DAYS.between(Main.getInter().getLastDay().getDate().toLocalDate(), LocalDate.now());
			if (daysWithoutChanges>1) {
				
			
			for (long i=1; i<=daysWithoutChanges;i++) {

				Day d= new Day(Main.getInter().getLastDay().getDeaths(),Main.getInter().getLastDay().getAverage(),Date.valueOf(Main.getInter().getLastDay().getDate().toLocalDate().plusDays(i)));
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
				  Day newToday = new Day(Main.getInter().getNumberofDeads(), average,Date.valueOf(LocalDate.now()));

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
