package db.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import db.pojos.Administration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GovernmentMenuController {
    @FXML
    private Button buttonvaccines;
    private Administration admin;

    public Administration getAdmin() {
		return admin;
	}

	public void setAdmin(Administration admin) {
		this.admin = admin;
	}

	@FXML
    void NotifyVaccines(ActionEvent event) {
    	String name= "NotifyVaccinesUsedView.fxml";
    	NotifyVaccinesUsedController controller = null;
		openWindow(name,controller);
    }

    @FXML
    void OnSignOutClick(ActionEvent event) {
    	Stage stage = (Stage) this.buttonvaccines.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void TriggerSimulation(ActionEvent event) {
    	String name= "TriggerSimulationView.fxml";
    	TriggerSimulationController controller = null;

    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource(name));

    		Parent root= loader.load();
        	  controller = loader.getController();


    		controller.setAdmin(admin);


    	
    	


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
    void ViewTotalNumber(ActionEvent event) {
    	String name= "TotalNumberVaccinesView.fxml";
    	TotalNumberVaccinesController controller = null;
		openWindow(name,controller);

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
