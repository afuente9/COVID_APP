package db.GUI;
import java.io.IOException;
import java.sql.Date;

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
public class MainMenuController {
	@FXML
    private PasswordField PasswordTextField;

    @FXML
    private TextField UserTextField;

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
		if(UserTextField.getText().equals("")&&PasswordTextField.getText().equals("")) {
			byte[] image=null;
			Doctor d = new Doctor(0,"cardio","Lucas P�rez",Date.valueOf("2000-10-10"),"34234",Sex.valueOf("Male"),"La Paz",image);
			String name= "DoctorMenuView.fxml";
			DoctorMenuController controller = null;
			try {
	    		FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
	    	Parent root;
	    	
	    		root = loader.load();
	    	
	    	  controller = loader.getController();
	    	  controller.setD(d);
	    	  controller.setDoctorName(d.getName());
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
		if(UserTextField.getText().equals("lab")&&PasswordTextField.getText().equals("lab")) {
			String name= "LabMenuView.fxml";
			LabMenuController controller = null;
			byte[] image= null;
			Lab l_new= new Lab(0,0,"28223, Pozuelo","Pfizer","23433453F",image);
			try {
	    		FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
	    	Parent root;
	    	
	    		root = loader.load();
	    	
	    	  controller = loader.getController();
	    	 controller.setL(l_new);
	    	 controller.setLabName(l_new.getName());
	    	  
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
}
