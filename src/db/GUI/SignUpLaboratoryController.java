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
//TODO Comprobar si funciona 
    	System.out.println(CifTextField.getText());
    String name = NameTextField.getText();
    	String cif_text = CifTextField.getText();
    	String adress = AdressTextField.getText();
    	 
    	 int number_vaccines=0;
    	 byte[] image=null;
      	Lab l_new= new Lab(name,adress,cif_text,number_vaccines, image);
		Main.getInter().addLab(l_new);

      	Stage stage = (Stage) this.CifTextField.getScene().getWindow();
		stage.close();
		
    }

    @FXML
    void OnCancelLabClick(ActionEvent event) {
    	Stage stage = (Stage) this.CifTextField.getScene().getWindow();
		stage.close();

    }

}
