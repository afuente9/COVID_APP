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
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
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
	    void modifyuserdata(ActionEvent event) {
		 	String name= "ModifyGovUserView.fxml";
		 	
		 	ModifyGovUserController controller = null;
		 	try {
		 		Pane root0 = (Pane) this.buttonvaccines.getScene().getRoot();

				 ColorAdjust adj = new ColorAdjust(0, -0.9, -0.5, 0);

				 GaussianBlur blur = new GaussianBlur(10); 
				    adj.setInput(blur);
				 root0.setEffect(adj);
	    		FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
	    	Parent root;
	    	
	    		root = loader.load();
	    	
	    	  controller = loader.getController();
	    	  controller.setOldusername(Main.getInter().getUserMailbyadmin(admin));
	    	Scene scene = new Scene(root);
	    	Stage stage = new Stage();
			stage.setResizable(false);

	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.setScene(scene);
			stage.getIcons().add(new Image("https://image.flaticon.com/icons/png/512/2833/2833315.png"));
			stage.setTitle("Modify user data of administration");

	        stage.showAndWait();

			root0.setEffect(null);

	    	} catch (IOException e) {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    	}	    }
	@FXML
    void NotifyVaccines(ActionEvent event) {
    	String name= "NotifyVaccinesUsedView.fxml";
    	NotifyVaccinesUsedController controller = null;
    	try {
    		Pane root0 = (Pane) this.buttonvaccines.getScene().getRoot();

			 ColorAdjust adj = new ColorAdjust(0, -0.9, -0.5, 0);

			 GaussianBlur blur = new GaussianBlur(10); 
			    adj.setInput(blur);
			 root0.setEffect(adj);
    		FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
    	Parent root;
    	
    		root = loader.load();
    	
    	  controller = loader.getController();
    	  controller.setAdmin(admin);
    	Scene scene = new Scene(root);
    	Stage stage = new Stage();
		stage.setTitle("Notify vaccines");

		stage.setResizable(false);

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
    		Pane root0 = (Pane) this.buttonvaccines.getScene().getRoot();

			 ColorAdjust adj = new ColorAdjust(0, -0.9, -0.5, 0);

			 GaussianBlur blur = new GaussianBlur(10); 
			    adj.setInput(blur);
			 root0.setEffect(adj);
    		FXMLLoader loader = new FXMLLoader(getClass().getResource(name));

    		Parent root= loader.load();
        	  controller = loader.getController();


    		controller.setAdmin(admin);


    	
    	


    	Scene scene = new Scene(root);
    	Stage stage = new Stage();
    	stage.setTitle("Trigger simulation");
		stage.setResizable(false);

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

    @FXML
    void ViewTotalNumber(ActionEvent event) {
    	String name= "TotalNumberVaccinesView.fxml";
    	TotalNumberVaccinesController controller = null;
    	try {
    		Pane root0 = (Pane) this.buttonvaccines.getScene().getRoot();

			 ColorAdjust adj = new ColorAdjust(0, -0.9, -0.5, 0);

			 GaussianBlur blur = new GaussianBlur(10); 
			    adj.setInput(blur);
			 root0.setEffect(adj);
    		FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
    	Parent root;
    	
    		root = loader.load();
    	
    	  controller = loader.getController();
  		controller.setAdmin(admin);

    	Scene scene = new Scene(root);
    	Stage stage = new Stage();
    	stage.setTitle("Total number of vaccines");
		stage.setResizable(false);

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
    void openWindow(String name,Object controller) {
    	try {
    		Pane root0 = (Pane) this.buttonvaccines.getScene().getRoot();

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
