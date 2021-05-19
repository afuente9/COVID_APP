package db.GUI;


import java.security.MessageDigest;

import javax.swing.JOptionPane;

import db.pojos.Administration;
import db.pojos.users.Role;
import db.pojos.users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignGovController {
	User u;

	  @FXML
	    private TextField NameTextField;

	    @FXML
	    private TextField secretnumbtext;

	    @FXML
	    private TextField emailgov;

	    @FXML
	    private PasswordField passwordgovernment;

	    @FXML
	    private PasswordField repeatpasswordgovernment1;

    @FXML
    void OnAcceptLabClick(ActionEvent event) {

    	if (!(passwordgovernment.getText().equals("")&&emailgov.getText().equals("")&&secretnumbtext.getText().equals("")&&NameTextField.getText().equals(""))) {
        	if(passwordgovernment.getText().equals(repeatpasswordgovernment1.getText())){
        		
        		Main.getInter().disconnect();
            	Main.getUserman().connect();
            	Role role = Main.getUserman().getRole(3);   
            	if (Main.getUserman().checkEmail(emailgov.getText())) {
    				JOptionPane.showMessageDialog(null, "Email already used, try to log in");

    			}else {

    				try {
    					
    					MessageDigest md = MessageDigest.getInstance("MD5");
    					md.update(passwordgovernment.getText().getBytes());
    					byte[] hash = md.digest();
    					 u = new User(emailgov.getText(), hash, role);
    					 Main.getUserman().newUser(u);
    						Main.getInter().connect();
    						
    						Main.getInter().addGovermentUser(new Administration(0,NameTextField.getText()), u);
    						JOptionPane.showMessageDialog(null, "Government registered");

    			}
    				catch(Exception e) {
    					e.printStackTrace();
    				}
    			}
        		
        		
        		
        		
        		
        	} else {
    			JOptionPane.showMessageDialog(null, "Passwords are not equal");

        	}
    	
    	
    }else {
		JOptionPane.showMessageDialog(null, "Empty fields");

	}
    }

    @FXML
    void OnCancelLabClick(ActionEvent event) {

    }
}
