
	package db.GUI;

	import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import db.pojos.Doctor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
	import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

	public class DoctorMenuController  {
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
	    private ImageView DoctorPic;

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
	    	  controller.setOldusername(Main.getInter().getUserMailbydoctor(d));
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
	    @FXML
	    void onChangePicDoc(ActionEvent event) {
	    	try {
	    		
	    
	    	final FileChooser fileChooser= new FileChooser();
	    	File file = fileChooser.showOpenDialog((Stage) this.DoctorName.getScene().getWindow());
	    	
	    	if(file.isFile() && file.getName().contains(".jpg")) {
	    		String thumbURL = file.toURI().toURL().toString();
	    		Image imgLoad= new Image(thumbURL,500,300,true,true,true);
	    		System.out.println("fnriuferiusiaoiasdfj");
	    		this.DoctorPic.setImage(imgLoad);
	    		 BufferedImage bImage = ImageIO.read(file);
	    	      ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    	      ImageIO.write(bImage, "jpg", bos );
	    	      byte [] data = bos.toByteArray();
	    		Main.getInter().changeDocPic(d, data);
	    		
	    	}
	    		
	    	}catch (Exception e) {e.printStackTrace();
	    	}
	    }




		public Button getAdd_Patient() {
			return Add_Patient;
		}




		public Button getSearchModify_Patient() {
			return SearchModify_Patient;
		}




		public Button getModifyData() {
			return ModifyData;
		}




		public ImageView getDoctorPic() {
			return DoctorPic;
		}




		public void setDoctorName(Label doctorName) {
			DoctorName = doctorName;
		}




		public void setAdd_Patient(Button add_Patient) {
			Add_Patient = add_Patient;
		}




		public void setSearchModify_Patient(Button searchModify_Patient) {
			SearchModify_Patient = searchModify_Patient;
		}




		public void setModifyData(Button modifyData) {
			ModifyData = modifyData;
		}




		public void setDoctorPic(ImageView doctorPic) {
			DoctorPic = doctorPic;
		}
	    

	}


