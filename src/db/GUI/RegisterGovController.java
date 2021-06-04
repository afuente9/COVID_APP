package db.GUI;


import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import db.pojos.Administration;
import db.pojos.users.Role;
import db.pojos.users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterGovController {
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

    @FXML
    void OnAcceptLabClick(ActionEvent event) {

    	if (!passwordgovernment.getText().equals("")&&!repeatpasswordgovernment1.getText().equals("")&&!emailgov.getText().equals("")&&
    			!secretnumbtext.getText().equals("")&&!NameTextField.getText().equals("")) {
    		boolean is= Main.getInter().checkAdminName(NameTextField.getText());
    		if(is==false) {
        	if(passwordgovernment.getText().equals(repeatpasswordgovernment1.getText())){
        		
            	Main.getUserman().connect();

            	Role role = Main.getUserman().getRole(3);   
            	if (Main.getUserman().checkEmail(emailgov.getText())) {
    				JOptionPane.showMessageDialog(null, "Email already used, try to log in");

    			}else {
    				Main.getInter().disconnect();
              List <String> secretPaswords = new ArrayList<>();
              secretPaswords.add("U54");
              secretPaswords.add("C4N4D4");
              secretPaswords.add("5P41N");
              secretPaswords.add("FR4NC3");
              secretPaswords.add("UN1T3D K1N6D0M");
              secretPaswords.add("174LY");
              secretPaswords.add("BR4Z1L");
              secretPaswords.add("CH1N4");
              secretPaswords.add("RU551A");
              secretPaswords.add("4U57R4L14");
              secretPaswords.add("SW17Z3RL4ND");
              secretPaswords.add("S0U7H 4FR1C4");
              secretPaswords.add("4R6ENT1N4");
              
              boolean correctData = validateName(NameTextField.getText());
  		
              
              if(secretPaswords.contains(secretnumbtext.getText())&&correctData==true) {
    				try {
    					
    					MessageDigest md = MessageDigest.getInstance("MD5");
    					md.update(passwordgovernment.getText().getBytes());
    					byte[] hash = md.digest();
    					 u = new User(emailgov.getText(), hash, role);
    					 Main.getUserman().newUser(u);
    						Main.getInter().connect();
    						
    						Main.getInter().addGovermentUser(new Administration(0,NameTextField.getText()), u);
    						JOptionPane.showMessageDialog(null, "Government registered");
    						Stage stage = (Stage) this.NameTextField.getScene().getWindow();
    						stage.close();

    			}
    				catch(Exception e) {
    					e.printStackTrace();
    				}
    			}else {
    				if(correctData==true) {
					JOptionPane.showMessageDialog(null, "Wrong secret password");
    				}

    			}
        		
    			}
        		
        		
        		
        	} else {
        		JOptionPane.showMessageDialog(null, "Passwords are not equal");

        	}
    	
    	
    }else {
		JOptionPane.showMessageDialog(null, "Government already registered");

	}
    	}else {
    		JOptionPane.showMessageDialog(null, "Country already registered");

    	}
    }

    @FXML
    void OnCancelLabClick(ActionEvent event) {
    	Stage stage = (Stage) this.NameTextField.getScene().getWindow();
		stage.close();
    }
}
