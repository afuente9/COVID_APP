package db.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import db.pojos.Lab;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LabMenuController  {
Lab l=null;
    public Lab getL() {
	return l;
}

public void setL(Lab l) {
	this.l = l;
}
	@FXML
    private Label LabName;

    public void setLabName(String labName) {
		LabName.setText(labName);
	}
	@FXML
    private Button viewStatistics;

    @FXML
    private Button SearchModify_Patient;

    @FXML
    private Button ModifyData;

    @FXML
    private ImageView Doctor_Picture;

    @FXML
    private Button ModifyData1;

    @FXML
    private Button ModifyData2;

    @FXML
    void addvaccinesbutton(ActionEvent event) {
    	String name= "NewVaccinesView.fxml";
    	NewVaccinesController controller = null;
     	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
    	Parent root;
    	
    		root = loader.load();
    	
    	  controller = loader.getController();
controller.setLnewvaccines(l);    
controller.setLabnamenewvaccines("Recent batches of vaccines by " +l.getName());
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
    void modifylabdatabutton(ActionEvent event) {
    	String name= "ModifyLaboratoryView.fxml";
    	ModifyLaboratoryController controller = null;
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
    	Parent root;
    	
    		root = loader.load();
    		controller = loader.getController();
	    	  controller.setOldlabadress(l.getAddress());
	    	  controller.setOldlabcif(l.getCif());
	    	  controller.setOldlabname(l.getName());
	    	  controller.setLmodif(l);
	    	  
    	
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

    @FXML
    void outfromlab(ActionEvent event) {
    	Stage stage = (Stage) this.viewStatistics.getScene().getWindow();
    	stage.close();

    }

    @FXML
    void searchpatientbutton(ActionEvent event) {
    	String name= "SearchPatientLABView.fxml";
    	SearchPatientLABController controller = null;
    	openWindow(name,controller);
    	
    }

    @FXML
    void sendshipmentbutton(ActionEvent event) {
    	String name= "SendShipmentView.fxml";
    	SendShipmentController controller = null;
     	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
    	Parent root;
    	
    		root = loader.load();
    	
    	  controller = loader.getController();
    	  controller.setLsendvaccines(l);
    	  controller.setLabname("Recent shipments from " +l.getName());
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
    void viewStatisticsButton(ActionEvent event) {
    	String name= "StatisticsView.fxml";
    	StatisticsController controller = null;
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