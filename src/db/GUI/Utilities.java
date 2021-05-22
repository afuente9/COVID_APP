package db.GUI;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Utilities {
public  void openWindow(String name,Object controller) {
	try {
		
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ChooseSignUpView.fxml"));
	Parent root;
	
		root = loader.load();
	
	  controller = loader.getController();
	Scene scene = new Scene(root);
	Stage stage = new Stage();
	stage.setResizable(false);

    stage.initModality(Modality.APPLICATION_MODAL);
    stage.setScene(scene);
    stage.showAndWait();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
void closeWindow() {
	
}
}
