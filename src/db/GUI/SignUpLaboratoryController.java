package db.GUI;
import java.security.MessageDigest;

import javax.swing.JOptionPane;

import db.pojos.Lab;
import db.pojos.users.Role;
import db.pojos.users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpLaboratoryController {
	User u;
    @FXML
    private TextField NameTextField;
    @FXML
    private TextField emailLab;
    @FXML
    private PasswordField  confirmpastx;
    @FXML
    private PasswordField  PassTextf;
    @FXML
    private TextField CifTextField;

    @FXML
    private TextField AdressTextField;

    @FXML
    void OnAcceptLabClick(ActionEvent event) {
//TODO Comprobar si funciona 
    	
    	if (!(emailLab.getText().equals("")&&PassTextf.getText().equals("")&&confirmpastx.getText().equals("")&&NameTextField.getText().equals("")&&CifTextField.getText().equals("")&&AdressTextField.getText().equals(""))) {
    	if(PassTextf.getText().equals(confirmpastx.getText())){
    		
    		   		
    		
        	if (Main.getUserman().checkEmail(emailLab.getText())) {
				JOptionPane.showMessageDialog(null, "Email already used, try to log in");

			}else {
try {
	Main.getInter().disconnect();
	Main.getUserman().connect();
	Role role = Main.getUserman().getRole(2); 
					MessageDigest md = MessageDigest.getInstance("MD5");
					md.update(PassTextf.getText().getBytes());
					byte[] hash = md.digest();
					 u = new User(emailLab.getText(), hash, role);

					Main.getUserman().newUser(u);
					Main.getInter().connect();


        	
    	    String name = NameTextField.getText();
    	    	String cif_text = CifTextField.getText();
    	    	String adress = AdressTextField.getText();
    	    	 
    	    	 int number_vaccines=0;
    	    	 byte[] image=null;
    	      	Lab l_new= new Lab(name,adress,cif_text,number_vaccines, image);
    			Main.getInter().addLabUser(l_new,u);
				JOptionPane.showMessageDialog(null, "Laboraty registered");

    	      	Stage stage = (Stage) this.CifTextField.getScene().getWindow();
    			stage.close();
    			
    			
}catch(Exception e) {
	e.printStackTrace();
}
			}}else {
			JOptionPane.showMessageDialog(null, "Passwords are not equal");

    	}
    		
    		
    	}else {
			JOptionPane.showMessageDialog(null, "Empty fields");

    	}
    	
		
    }

    @FXML
    void OnCancelLabClick(ActionEvent event) {
    	Stage stage = (Stage) this.CifTextField.getScene().getWindow();
		stage.close();

    }

}
