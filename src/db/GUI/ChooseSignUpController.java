package db.GUI;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ChooseSignUpController {
	@FXML
	private Button BackButton;

	@FXML
	void OnBacktoFirstScreenClick(ActionEvent event) {
		Stage stage = (Stage) this.BackButton.getScene().getWindow();
		stage.close();

	}

	@FXML
	void OnSignUpAsDoctorClick(ActionEvent event) {
// TODO abre registro doctor
		
		
		String name = "SignUpDoctorView.fxml";
		SignUpDoctorController controller = null;
		openWindow(name, controller,"Sign up as doctor");
		Stage stage = (Stage) this.BackButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	void OnSignUpAsLaboratoryClick(ActionEvent event) {
// TODO abre registrar lab
		String name = "SignUpLaboratoryView.fxml";
		SignUpLaboratoryController controller = null;
		openWindow(name, controller,"Sign up as laboratory");
		Stage stage = (Stage) this.BackButton.getScene().getWindow();
		stage.close();
		
		
	}
	  @FXML
	    void SIgngovernment(ActionEvent event) {
	    	//String name = "RegisterGovView.fxml";
	    	
	    	try {
	    		Pane root0 = (Pane) this.BackButton.getScene().getRoot();

				 ColorAdjust adj = new ColorAdjust(0, -0.9, -0.5, 0);

				 GaussianBlur blur = new GaussianBlur(10); 
				    adj.setInput(blur);
				 root0.setEffect(adj);
				FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterGovView.fxml"));
				//loader.setRoot("RegisterGovView.fxml");
				Parent root = loader.load();
			

				RegisterGovController controller = loader.getController();
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.setResizable(false);

				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setScene(scene);
				stage.setTitle("Register government");
				stage.getIcons().add(new Image("https://image.flaticon.com/icons/png/512/2833/2833315.png"));

				stage.showAndWait();
				root0.setEffect(null);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			Stage stage = (Stage) this.BackButton.getScene().getWindow();
			stage.close();
	    }


	void openWindow(String name, Object controller, String title) {
		try {
			Pane root0 = (Pane) this.BackButton.getScene().getRoot();

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
			stage.setTitle(title);
			stage.getIcons().add(new Image("https://image.flaticon.com/icons/png/512/2833/2833315.png"));

			stage.showAndWait();
			root0.setEffect(null);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
  

}
