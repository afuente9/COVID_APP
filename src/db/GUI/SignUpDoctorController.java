package db.GUI;

import java.sql.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import db.pojos.*;

public class SignUpDoctorController {

    @FXML
    private TextField DateTextField;

    @FXML
    private TextField NameTextField;

    @FXML
    private TextField ColNumTextField;

    @FXML
    private TextField SpetialityTextField;

    @FXML
    private TextField SexTextField;
    @FXML
    private TextField HospitalTextField;

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
       //id = getlistadedoctores().length(); 
    	Doctor d_new= new Doctor(id,name,sex,date,colnum,spetiality,hospital);
    	//añadir doctor a la lista de doctores
    	//popup registrado correctamente
    	//cerrar ventana (utilities)
    }

    @FXML
    void OnCancelClick(ActionEvent event) {
    	//cerrar ventana (utilities)

    }
    

}
