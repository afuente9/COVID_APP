package db.GUI;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
		openWindow(name, controller);
		Stage stage = (Stage) this.BackButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	void OnSignUpAsLaboratoryClick(ActionEvent event) {
// TODO abre registrar lab
		String name = "SignUpLaboratoryView.fxml";
		SignUpLaboratoryController controller = null;
		openWindow(name, controller);
		Stage stage = (Stage) this.BackButton.getScene().getWindow();
		stage.close();
		
		
	}

	void openWindow(String name, Object controller) {
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
