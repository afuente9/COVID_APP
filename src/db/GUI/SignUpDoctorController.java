package db.GUI;

import java.net.URL;
import java.security.MessageDigest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
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
		db.pojos.Sex sex = Sex.valueOf(sex_text);
		Date date = Date.valueOf(date_text);
		int id = 0;
		byte[] image = null;
		Doctor d_new = new Doctor(name, spetiality, date, colnum, hospital, sex, image);
		// añadir doctor a la lista de doctores
		Main.getInter().addDoctorUser(d_new, u);

		Stage stage = (Stage) this.NameTextField.getScene().getWindow();
		stage.close();
	}

	public boolean validateName(String name) {
		boolean correctData = true;
		List<Integer> ascciNumsName = ConvertAscii(NameTextField.getText());

		for (int i = 0; i < ascciNumsName.size(); i++) {
			if (!(ascciNumsName.get(i) >= 65 && ascciNumsName.get(i) <= 122)) {
				correctData = false;
				if(ascciNumsName.get(i)==32){
					correctData = true;

					
				}

			}

		}
		if(correctData==false){
	           JOptionPane.showMessageDialog(null, "Wrong name.");

		}
		return correctData;
	}
	public boolean validateSex(String sexText) {
		boolean correctData = true;
		try {
			db.pojos.Sex sex= Sex.valueOf(sexText);
        	

    	}catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Wrong sex. Please, put: Male or Female");
            correctData=false;

    	}
		return correctData;
	}
	
	public boolean validateBD(String BD) {
		boolean correctData = true;
		try {
			Date date = Date.valueOf(BD);

		}catch(Exception e) {
		    JOptionPane.showMessageDialog(null, "Wrong date. Please, use the format: yyyy-mm-dd");
		    correctData=false;
		}
		return correctData;
	}

	private List<Integer> ConvertAscii(String StringtoConvert) {
		List<Integer> ascciNumsName = new ArrayList<>();

		try {
			for (int i = 0; i < StringtoConvert.length(); i++) {
				char chartoconvert = StringtoConvert.charAt(i);
				int asciinum = (int) chartoconvert;
				ascciNumsName.add(asciinum);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Wrong data, put it again.");

		}

		return ascciNumsName;

	}

	@FXML
	void oncreateuser(ActionEvent event) {
		String doctoremail = EmaDcotorText.getText();
		String doctorPassword = pasDoctorTxtF.getText();
		if (!doctoremail.equals("") && !doctorPassword.equals("") && !RepeatPassword.getText().equals("")
				&& !NameTextField.getText().equals("") && !SexTextField.getText().equals("")
				&& !DateTextField.getText().equals("") && !ColNumTextField.getText().equals("")
				&& !HospitalTextField.getText().equals("") && !SpetialityTextField.getText().equals("")) {
			boolean correctData = validateName(NameTextField.getText());
			correctData= validateSex(SexTextField.getText());
			correctData=validateBD(DateTextField.getText());

			if (doctorPassword.equals(RepeatPassword.getText()) && correctData == true) {

				Main.getUserman().connect();


				if (Main.getUserman().checkEmail(doctoremail)) {
					JOptionPane.showMessageDialog(null, "Email already used, try to log in");

				} else {
					try {
						Main.getInter().disconnect();
						Role role = Main.getUserman().getRole(1);

						MessageDigest md = MessageDigest.getInstance("MD5");
						md.update(doctorPassword.getBytes());
						byte[] hash = md.digest();
						User u = new User(doctoremail, hash, role);

						Main.getUserman().newUser(u);
						Main.getInter().connect();

						String name = NameTextField.getText();
						String date_text = DateTextField.getText();
						String colnum = ColNumTextField.getText();
						String spetiality = SpetialityTextField.getText();
						String hospital = HospitalTextField.getText();

						String sex_text = SexTextField.getText();
						db.pojos.Sex sex = Sex.valueOf(sex_text);
						Date date = Date.valueOf(date_text);
						int id = 0;
						byte[] image = null;
						Doctor d_new = new Doctor(name, spetiality, date, colnum, hospital, sex, image);
						// añadir doctor a la lista de doctores
						Main.getInter().addDoctorUser(d_new, u);
						Doctor dlast= Main.getInter().getLastDoctor();
		              	List<Patient> allpatients= Main.getInter().getAllPatient();
		              	
						for (int i=0;i<allpatients.size();i++) {
							
							Main.getInter().assignPattoDoc(allpatients.get(i).getId(), dlast);
						}
						
			            JOptionPane.showMessageDialog(null, "Doctor registered. ");
			        	Stage stage = (Stage) this.NameTextField.getScene().getWindow();
			    		stage.close();
			            


					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			} else {
				if (correctData == true) {
					JOptionPane.showMessageDialog(null, "Passwords are not equal");
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Empty fields");

		}
	}

	@FXML
	void OnCancelClick(ActionEvent event) {
		Stage stage = (Stage) this.NameTextField.getScene().getWindow();
		stage.close();
	}

}
