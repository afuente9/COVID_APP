package db.GUI;
import db.pojos.Lab;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpLaboratoryController {

    @FXML
    private TextField NameTextField;

    @FXML
    private TextField CifTextField;

    @FXML
    private TextField AdressTextField;

    @FXML
    void OnAcceptLabClick(ActionEvent event) {
    	System.out.println("hola");

    	System.out.println(CifTextField.getText());
    String name = NameTextField.getText();
    	String cif_text = CifTextField.getText();
    	String adress = AdressTextField.getText();
    	 int id=0;
    	 int number_vaccines=0;
    	 byte[] image=null;
      	Lab l_new= new Lab(id,number_vaccines,adress,name,cif_text, image);
      	//addlab to the list of labs
    	Stage stage = (Stage) this.CifTextField.getScene().getWindow();
		stage.close();
		
    }

    @FXML
    void OnCancelLabClick(ActionEvent event) {
    	Stage stage = (Stage) this.CifTextField.getScene().getWindow();
		stage.close();

    }

}
