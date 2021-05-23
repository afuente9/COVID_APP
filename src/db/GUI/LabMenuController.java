package db.GUI;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import db.pojos.Lab;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
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
     		Pane root0 = (Pane) this.LabName.getScene().getRoot();

			 ColorAdjust adj = new ColorAdjust(0, -0.9, -0.5, 0);

			 GaussianBlur blur = new GaussianBlur(10); 
			    adj.setInput(blur);
			 root0.setEffect(adj);
    		FXMLLoader loader = new FXMLLoader(getClass().getResource(name));

           	Parent root = loader.load();

    	  controller = loader.getController();

           controller.setLnewvaccines(l); 
   		controller.setTotalNumberVaccines1("You have "+ Main.getInter().getNumberVaccinesLab(l.getId())+ " vaccines");


    	Scene scene = new Scene(root);
    	Stage stage = new Stage();
		stage.setResizable(false);
		stage.setTitle("Add vaccines");

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
    void modifylabdatabutton(ActionEvent event) {
    	String name= "ModifyLaboratoryView.fxml";
    	ModifyLaboratoryController controller = null;
    	try {
    		Pane root0 = (Pane) this.LabName.getScene().getRoot();

			 ColorAdjust adj = new ColorAdjust(0, -0.9, -0.5, 0);

			 GaussianBlur blur = new GaussianBlur(10); 
			    adj.setInput(blur);
			 root0.setEffect(adj);
    		FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
    	Parent root;
    	
    		root = loader.load();
    		controller = loader.getController();
	    	  controller.setOldlabadress(l.getAddress());
	    	  controller.setOldlabcif(l.getCif());
	    	  controller.setOldlabname(l.getName());
	    	  controller.setOldusername(Main.getInter().getUserMailbylab(l));
	    	  controller.setLmodif(l);
	    	  
    	
    	  controller = loader.getController();
    	Scene scene = new Scene(root);
    	Stage stage = new Stage();
    	stage.setTitle("Modify laboratory");
		stage.setResizable(false);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
		stage.getIcons().add(new Image("https://image.flaticon.com/icons/png/512/2833/2833315.png"));

		stage.showAndWait();

		root0.setEffect(null);

        this.LabName.setText(Main.getInter().getLab(l.getId()).getName());
        
        
        
        
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	

    }
    @FXML
    void changelabpic(ActionEvent event) {
    	try {
    		
    	    
	    	final FileChooser fileChooser= new FileChooser();
	    	File file = fileChooser.showOpenDialog((Stage) this.LabName.getScene().getWindow());
	    	
	    	if(file.isFile() && file.getName().contains(".jpg")) {
	    		String thumbURL = file.toURI().toURL().toString();
	    		Image imgLoad= new Image(thumbURL,500,300,true,true,true);
	    		System.out.println("fnriuferiusiaoiasdfj");
	    		this.Doctor_Picture.setImage(imgLoad);
	    		 BufferedImage bImage = ImageIO.read(file);
	    	      ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    	      ImageIO.write(bImage, "jpg", bos );
	    	      byte [] data = bos.toByteArray();
	    		Main.getInter().changeLabPic(l, data);
	    		
	    	}
	    		
	    	}catch (Exception e) {e.printStackTrace();
	    	}
    }

    @FXML
    void outfromlab(ActionEvent event) {
    	Stage stage = (Stage) this.viewStatistics.getScene().getWindow();
    	stage.close();

    }

    @FXML
    void searchpatientbutton(ActionEvent event) {
    	String name= "SearchPatientbyLabView.fxml";
    	SearchPatientbyLabController controller = null;
    	String title="Search patient";
    	try {
    		Pane root0 = (Pane) this.LabName.getScene().getRoot();

			 ColorAdjust adj = new ColorAdjust(0, -0.9, -0.5, 0);

			 GaussianBlur blur = new GaussianBlur(10); 
			    adj.setInput(blur);
			 root0.setEffect(adj);
    		FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
    	Parent root;
    	
    		root = loader.load();
    	
    	  controller = loader.getController();
    	  controller.setLab(l);
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


    @FXML
    void seepiclab(ActionEvent event) {
Main.getInter().openpicturelab(l);
    }
    
    @FXML
    void sendshipmentbutton(ActionEvent event) {
    	String name= "SendShipmentView.fxml";
    	SendShipmentController controller = null;
     	try {
     		Pane root0 = (Pane) this.LabName.getScene().getRoot();

			 ColorAdjust adj = new ColorAdjust(0, -0.9, -0.5, 0);

			 GaussianBlur blur = new GaussianBlur(10); 
			    adj.setInput(blur);
			 root0.setEffect(adj);
    		FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
    	Parent root;
    	
    		root = loader.load();
    	
    	  controller = loader.getController();
    	  controller.setLsendvaccines(l);
    	  System.out.println(l);
    	  controller.setLabname("Recent shipments from " +l.getName());
    	Scene scene = new Scene(root);
    	Stage stage = new Stage();
		stage.setResizable(false);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Send shipment");
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
    void viewStatisticsButton(ActionEvent event) {
    	String name= "StatisticsView.fxml";
    	StatisticsController controller = null;
    	openWindow(name,controller, "Statistics");
    	
    }
    void openWindow(String name,Object controller, String title) {
    	try {
    		Pane root0 = (Pane) this.LabName.getScene().getRoot();

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

	
    

}