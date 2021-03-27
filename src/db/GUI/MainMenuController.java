package db.GUI;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
public class MainMenuController {
	@FXML
    private PasswordField PasswordTextField;

    @FXML
    private TextField UserTextField;

    @FXML
    void OnSignUpClick(ActionEvent event) {
		//System.out.println(UserTextField.getText());
/*String a= UserTextField.getText();
String b= "gege";
me reconoce el texto pero no me lo compara bien:/
    	/*if(a==b) {
    		System.out.println("ererererererererer");
    	}*/
    	//INTENTAR ABRIR OTRA PESTAÑA DIFERENTE NO ME SALE
		try {
			Pane root = (Pane)FXMLLoader.load(getClass().getResource("ChooseSignUpView.fxml"));
			Scene scene = new Scene(root,700,700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//primaryStage.setScene(scene);
			//primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }
}
