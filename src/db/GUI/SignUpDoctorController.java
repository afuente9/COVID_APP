package db.GUI;

import java.net.URL;
import java.security.MessageDigest;
import java.sql.Date;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import db.interfaces.UserManager;
import db.jpa.JPAUserManagment;
import db.pojos.*;
import db.pojos.users.Role;
import db.pojos.users.User;

public class SignUpDoctorController implements Initializable {



    @FXML
    private Button createUserbutton;

  

    

    @FXML
    private Label Sexlabel;

    @FXML
    private Label passwordlabel;

    @FXML
    private Label EmailLabel;

  

    @FXML
    private Label repeatLabel;

    @FXML
    private PasswordField RepeatPassword;
  

    @FXML
    private Label specialityLabel;

    @FXML
    private Label collabel;

    @FXML
    private Label bdlabel;


	User u;

    @FXML
    private Label namelabel;


    @FXML
    private TextField DateTextField;

    @FXML
    private Label hoslabel;

  
    @FXML
    private TextField NameTextField;

    @FXML
    private TextField ColNumTextField;

    @FXML
    private TextField SpetialityTextField;

    @FXML
    private TextField EmaDcotorText;

    @FXML
    private PasswordField pasDoctorTxtF;
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
    	Main.getInter().addDoctorUser(d_new, u);

    	


    	Stage stage = (Stage) this.NameTextField.getScene().getWindow();
		stage.close();    	
    }
    @FXML
    void oncreateuser(ActionEvent event) {
    	String doctoremail = EmaDcotorText.getText();
    	String doctorPassword =     pasDoctorTxtF.getText();
    	if(!doctoremail.equals("")&&!doctorPassword.equals("")&&!RepeatPassword.getText().equals("")) {
			if(doctorPassword.equals(RepeatPassword.getText())) {
	    		
	        	
	        	
	        	NameTextField.setDisable(false);
	        	DateTextField.setDisable(false);
	        	ColNumTextField.setDisable(false);
	        	NameTextField.setDisable(false);
	        	SpetialityTextField.setDisable(false);
	        	HospitalTextField.setDisable(false);
	        	namelabel.setDisable(false);
	        	bdlabel.setDisable(false);
	        	collabel.setDisable(false);
	        	specialityLabel.setDisable(false);
	        	hoslabel.setDisable(false);
	        	SexTextField.setDisable(false);
	        	Sexlabel.setDisable(false);
	        	EmaDcotorText.setDisable(true);
	        	repeatLabel.setDisable(true);
	        	passwordlabel.setDisable(true);
	        	EmailLabel.setDisable(true);
	        	EmaDcotorText.setDisable(true);
	        	pasDoctorTxtF.setDisable(true);
	        	RepeatPassword.setDisable(true);
	        	createUserbutton.setDisable(true);
	        	Role role = Main.getUserman().getRole(1);
	        	
	        	System.out.println(role.getName());
	        	System.out.println(doctoremail);
	        	if (Main.getUserman().checkEmail(doctoremail)) {
					JOptionPane.showMessageDialog(null, "Email already used, try to log in");

				}else {
					try {
					
					MessageDigest md = MessageDigest.getInstance("MD5");
					md.update(doctorPassword.getBytes());
					byte[] hash = md.digest();
					User u = new User(doctoremail, hash, role);
					System.out.println("hola");

					Main.getUserman().newUser(u);
					System.out.println("hola");
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
			    	Main.getInter().addDoctorUser(d_new, u);
					
				}catch(Exception e) {
					e.printStackTrace();
				}
				}
	        	
	         

	    		
	    	}else {
				JOptionPane.showMessageDialog(null, "Passwords are not equal");

	    	}
    	}else {
			JOptionPane.showMessageDialog(null, "Empty fields");

    	
    }
    }

    @FXML
    void OnCancelClick(ActionEvent event) {
    Stage stage = (Stage) this.NameTextField.getScene().getWindow();
	stage.close();
    }

	

}
