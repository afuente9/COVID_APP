package db.GUI;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import db.pojos.*;

public class SignUpDoctorController implements Initializable {

    @FXML
    private TextField DateTextField;

    @FXML
    private TextField NameTextField;

    @FXML
    private TextField ColNumTextField;

    @FXML
    private TextField SpetialityTextField;

    @FXML
    private TextField EmaDcotorText;

    @FXML
    private TextField pasDoctorTxtF;
    @FXML
    private TextField SexTextField;
    @FXML
    private TextField HospitalTextField;
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
    
    @FXML
    void OnAcceptClick(ActionEvent event) {
    	
    	String name = NameTextField.getText();
    	String date_text = DateTextField.getText();
    	String colnum = ColNumTextField.getText();
    	String spetiality = SpetialityTextField.getText();
    	String hospital = HospitalTextField.getText();


    	String sex_text = SexTextField.getText();
    	db.pojos.Sex sex= Sex.valueOf(sex_text);
    	Date date = Date.valueOf(date_text);
        int id=0;
    	byte[] image=null;
    	Doctor d_new= new Doctor(name,spetiality,date,colnum,hospital,sex,image);
    	//añadir doctor a la lista de doctores
    	Main.getInter().addDoctor(d_new);
    	


    	Stage stage = (Stage) this.NameTextField.getScene().getWindow();
		stage.close();    	
    }

    @FXML
    void OnCancelClick(ActionEvent event) {
    Stage stage = (Stage) this.NameTextField.getScene().getWindow();
	stage.close();
    }

	

}
