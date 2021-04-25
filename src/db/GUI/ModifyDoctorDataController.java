package db.GUI;

import java.sql.Date;

import db.pojos.Doctor;
import db.pojos.Sex;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModifyDoctorDataController {
	//poner los labels bien desde el principio
	//pasarle el doctor
	
Doctor dmodif;
	
    public void setDmodif(Doctor dmodif) {
	this.dmodif = dmodif;
}


	@FXML
    private TextField ModifyNameTextField;

    @FXML
    private TextField ModifySpetialityTextField;

    @FXML
    private TextField ModifySexTextField;

    @FXML
    private TextField ModifyColNumTextField;

    @FXML
    private TextField ModifyDateTextField;

    @FXML
    private TextField ModifyHospitalTextField;

    @FXML
    private Label OldSex;

    @FXML
    private Label OldName;

    @FXML
    private Label OldSpetiality;

    @FXML
    private Label OldColNum;

    @FXML
    private Label OldBDate;

    @FXML
    private Label OldHospi;

    @FXML
    void OnAcceptModifyBirthDate(ActionEvent event) {         
String newDate= ModifyDateTextField.getText();
dmodif.setBirthday(Date.valueOf(newDate));
OldBDate.setText(newDate);
ModifyDateTextField.setText("");
Main.getInter().modifyDoctor(this.dmodif.getId(), "birth_date", newDate);
    }

    @FXML
    void OnAcceptModifyCollegiate(ActionEvent event) {
String new_colnum= ModifyColNumTextField.getText();
dmodif.setCollegiate_number(new_colnum);
OldColNum.setText(new_colnum);
ModifyColNumTextField.setText("");
Main.getInter().modifyDoctor(this.dmodif.getId(), "collegiate_number", new_colnum);

    }

    @FXML
    void OnAcceptModifyHospital(ActionEvent event) {
    	String new_hospital= ModifyHospitalTextField.getText();
    	dmodif.setCollegiate_number(new_hospital);
    	OldHospi.setText(new_hospital);
    	ModifyHospitalTextField.setText("");
    	Main.getInter().modifyDoctor(this.dmodif.getId(), "hospital", new_hospital);

    }

    @FXML
    void OnAcceptModifyName(ActionEvent event) {

    	String new_name= ModifyNameTextField.getText();
    	OldName.setText(new_name);

    	dmodif.setName(new_name);
    	OldName.setText(new_name);
    	// TODO DoctorMenuController.DoctorName.setText(new_name); refresh o algo para la pantalla principal
    	ModifyNameTextField.setText("");
    	Main.getInter().modifyDoctor(this.dmodif.getId(), "name", new_name);

    	
    }

  

	@FXML
    void OnAcceptModifySex(ActionEvent event) {
    	String newSex= ModifySexTextField.getText();
    	dmodif.setSex(Sex.valueOf(newSex));
    	OldSex.setText(newSex);
    	ModifySexTextField.setText("");
    	Main.getInter().modifyDoctor(this.dmodif.getId(), "sex", newSex);

    }

    @FXML
    void OnAcceptModifySpetiality(ActionEvent event) {
    	String new_spetiality= ModifySpetialityTextField.getText();
    	dmodif.setSpeciality(new_spetiality);
    	OldSpetiality.setText(new_spetiality);
    	ModifySpetialityTextField.setText("");
    	Main.getInter().modifyDoctor(this.dmodif.getId(), "speciality", new_spetiality);

    }

    @FXML
    void OnBackModifyData(ActionEvent event) {
    	Stage stage = (Stage) this.ModifySpetialityTextField.getScene().getWindow();
    	stage.close();
    }
  

  	public void setOldSex(String oldSex) {
  		OldSex.setText(oldSex); 
  	}

 

  	public void setOldName(String nametext) {
  		OldName.setText(nametext);
  	}

  

  	public void setOldSpetiality(String oldSpetiality) {
  		OldSpetiality.setText(oldSpetiality); 
  	}



  	public void setOldColNum(String oldColNum) {
  		OldColNum.setText(oldColNum); 
  	}

  

  	public void setOldBDate(String oldBDate) {
  		OldBDate.setText(oldBDate); 
  	}

  	
  	public void setOldHospi(String oldHospi) {
  		OldHospi.setText(oldHospi); 
  	}

}
