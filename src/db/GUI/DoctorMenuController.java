
	package db.GUI;

	import java.io.IOException;

import db.pojos.Doctor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
	import javafx.scene.control.Label;
	import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

	public class DoctorMenuController {
        Doctor d;
	    public Doctor getD() {
			return d;
		}



		public void setD(Doctor d) {
			this.d = d;
		}
		@FXML
	    public Label DoctorName;

	    public Label getDoctorName() {
			return DoctorName;
		}



		public void setDoctorName(String doctorName) {
			DoctorName.setText(doctorName); 
		}
		@FXML
	    private Button Add_Patient;

	    @FXML
	    private Button SearchModify_Patient;

	    @FXML
	    private Button ModifyData;

	    @FXML
	    private ImageView Doctor_Picture;

	    @FXML
	    void OnAddPatient(ActionEvent event) {
	    	String name= "AddPatientView.fxml";
			AddPatientController controller = null;
			openWindow(name,controller);
	    }

	   

		@FXML
	    void OnModifyData(ActionEvent event) {
			String name= "ModifyDoctorDataView.fxml";
			ModifyDoctorDataController controller = null;
			try {
	    		FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
	    	Parent root;
	    	
	    		root = loader.load();
	    	
	    	  controller = loader.getController();
	    	  controller.setDmodif(d);
	    	  controller.setOldName(d.getName());
	    	  controller.setOldBDate(d.getBirthday().toString());
	    	  controller.setOldColNum(d.getCollegiate_number());
	    	  controller.setOldHospi(d.getHospital());
	    	  controller.setOldSex(d.getSex().toString());
	    	  controller.setOldSpetiality(d.getSpeciality());
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

	    @FXML
	    void OnSearchPatient(ActionEvent event) {
	    	String name= "SearchPatientView.fxml";
			SearchPatientController controller = null;
			openWindow(name,controller);

	    }

	    @FXML
	    void OnSignOutClick(ActionEvent event) {
	    	Stage stage = (Stage) this.DoctorName.getScene().getWindow();
	    	stage.close();
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


