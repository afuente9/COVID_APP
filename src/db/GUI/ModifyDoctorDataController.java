package db.GUI;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import db.pojos.Doctor;
import db.pojos.Sex;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ModifyDoctorDataController {
	//poner los labels bien desde el principio
	//pasarle el doctor
	
Doctor dmodif;
	
    public void setDmodif(Doctor dmodif) {
	this.dmodif = dmodif;
}
    @FXML
    private TextField showold;

    @FXML
    private TextField showNew;
    
    @FXML
    private PasswordField oldpastf;

    @FXML
    private PasswordField newpasstf;
    

    @FXML
    private TextField modifyusername;

    @FXML
    private Label oldusername;


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
boolean correctData=true;
try {
	Date date = Date.valueOf(newDate);

}catch(Exception e) {
    JOptionPane.showMessageDialog(null, "Wrong date. Please, use the format: yyyy-mm-dd");
    correctData=false;
}

if (correctData==true) {
dmodif.setBirthday(Date.valueOf(newDate));
OldBDate.setText(newDate);
ModifyDateTextField.setText("");
Main.getInter().modifyDoctor(this.dmodif.getId(), "birth_date", newDate);
    }
    }

    @FXML
    void OnAcceptModifyCollegiate(ActionEvent event) {
String new_colnum= ModifyColNumTextField.getText();
if(!new_colnum.equals("")) {
dmodif.setCollegiate_number(new_colnum);
OldColNum.setText(new_colnum);
ModifyColNumTextField.setText("");
Main.getInter().modifyDoctor(this.dmodif.getId(), "collegiate_number", new_colnum);

    }else {
	    JOptionPane.showMessageDialog(null, "Empty field");

	}
    }

    @FXML
    void OnAcceptModifyHospital(ActionEvent event) {
    	String new_hospital= ModifyHospitalTextField.getText();
    	if(!new_hospital.equals("")) {
    	dmodif.setCollegiate_number(new_hospital);
    	OldHospi.setText(new_hospital);
    	ModifyHospitalTextField.setText("");
    	Main.getInter().modifyDoctor(this.dmodif.getId(), "hospital", new_hospital);
    	}else {
    	    JOptionPane.showMessageDialog(null, "Empty field");

		}
    }
    private List <Integer> ConvertAscii(String StringtoConvert){
	   	List <Integer> ascciNumsName= new ArrayList<>();

	   try {
 for (int i =0;i<StringtoConvert.length();i++) {
	 char chartoconvert= StringtoConvert.charAt(i);
	 int asciinum=(int)chartoconvert;
	 ascciNumsName.add(asciinum);
 }
 }
	   catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Wrong data, put it again.");

	   }
	   
	   
	return ascciNumsName;
	   
   }
    @FXML
    void OnAcceptModifyName(ActionEvent event) {

    	String new_name= ModifyNameTextField.getText();
		boolean correctData=true;
		if (!new_name.equals("")) {
			List <Integer> ascciNumsName= ConvertAscii(new_name);
		    	
		    	for (int i =0; i<ascciNumsName.size();i++) {
		    		if (!(ascciNumsName.get(i)>=65&&ascciNumsName.get(i)<=122)) {
		    			correctData=false;

		    		}
		    	
		    	}
		    	if (correctData==false) {
		            JOptionPane.showMessageDialog(null, "Wrong name. ");

		    	}else {
    	OldName.setText(new_name);

    	dmodif.setName(new_name);
    	OldName.setText(new_name);
    	// TODO DoctorMenuController.DoctorName.setText(new_name); refresh o algo para la pantalla principal
    	ModifyNameTextField.setText("");
    	Main.getInter().modifyDoctor(this.dmodif.getId(), "name", new_name);

    	
    }
		}else {
    	    JOptionPane.showMessageDialog(null, "Empty field");

		}
    }

  

	@FXML
    void OnAcceptModifySex(ActionEvent event) {
    	String newSex= ModifySexTextField.getText();
    	boolean correctData=true;
		try {
			db.pojos.Sex sex= Sex.valueOf(newSex);
        	

    	}catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Wrong sex. Please, put: Male or Female");
            correctData=false;

    	}
		if (correctData==true) {
    	dmodif.setSex(Sex.valueOf(newSex));
    	OldSex.setText(newSex);
    	ModifySexTextField.setText("");
    	Main.getInter().modifyDoctor(this.dmodif.getId(), "sex", newSex);
		}
    }

    @FXML
    void OnAcceptModifySpetiality(ActionEvent event) {
    	String new_spetiality= ModifySpetialityTextField.getText();

		if (!new_spetiality.equals("")) {

    	dmodif.setSpeciality(new_spetiality);
    	OldSpetiality.setText(new_spetiality);
    	ModifySpetialityTextField.setText("");
    	Main.getInter().modifyDoctor(this.dmodif.getId(), "speciality", new_spetiality);

    }else {
	    JOptionPane.showMessageDialog(null, "Empty field");

	}}

    @FXML
    void OnBackModifyData(ActionEvent event) {
    	Stage stage = (Stage) this.ModifySpetialityTextField.getScene().getWindow();
    	stage.close();
    }
    @FXML
    void modifypass(ActionEvent event) {
    	

		if (!newpasstf.getText().equals("")&&!oldpastf.getText().equals("")) {
String newpass= newpasstf.getText();
boolean catchdone=false;

    	Main.getInter().disconnect();
    	Main.getUserman().connect();
    	catchdone=Main.getUserman().updateUserPassword(oldusername.getText(), newpass, oldpastf.getText(),catchdone);
        Main.getUserman().disconnect();
        Main.getInter().connect();
        if(catchdone==false) {
    	    JOptionPane.showMessageDialog(null, "Password changed");
            }
        newpasstf.setText("");
        oldpastf.setText("");
        
    }else {
	    JOptionPane.showMessageDialog(null, "Empty field");

	}
    }
   


    @FXML
    void modifyuser(ActionEvent event) {
    	String newUserName= modifyusername.getText();
    	String oldUName = oldusername.getText();

		if (!newUserName.equals("")) {

			oldusername.setText(newUserName);

			Main.getInter().disconnect();
	    	Main.getUserman().connect();
	    	Main.getUserman().updateUserMailWithoutpass(newUserName, oldUName);

	    	Main.getUserman().disconnect();
	        Main.getInter().connect();


		    modifyusername.setText("");
	        
			
		    JOptionPane.showMessageDialog(null, "Username changed");

    }else {
	    JOptionPane.showMessageDialog(null, "Empty field");

	}
    }
  

 
    
	public Label getOldusername() {
		return oldusername;
	}

	

	public void setOldusername(String oldusername) {
		this.oldusername.setText(oldusername);
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
  

    @FXML
    void visualizepassword7(MouseEvent event) {
    	showold.setText(oldpastf.getText());
    	showNew.setText(newpasstf.getText());
    	showold.setVisible(true);
    	showNew.setVisible(true);

    }

    @FXML
    void visualizepassword8(MouseEvent event) {
    	showold.setVisible(false);
    	showNew.setVisible(false);
    }

}
