package db.GUI;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class deleteacController {

    @FXML
    private TextField modifyusername;

    @FXML
    private PasswordField oldpastf;

    @FXML
    private PasswordField newpasstf;

    @FXML
    private TextField showOld;

    @FXML
    private TextField showNew;

    @FXML
    void backfrommodify(ActionEvent event) {
    	Stage stage = (Stage) this.showNew.getScene().getWindow();
    	stage.close();
    }
    @FXML
    void delete(ActionEvent event) {
    	if (!oldpastf.getText().equals("")&&!newpasstf.getText().equals("")&&!modifyusername.getText().equals("")) {
    		if (oldpastf.getText().equals(newpasstf.getText())) {
    			
    			Main.getInter().disconnect();
    	    	Main.getUserman().connect();
    	    	Main.getUserman().deleteUser(modifyusername.getText(),oldpastf.getText() );

    	    	Main.getUserman().disconnect();
    	        Main.getInter().connect();
				JOptionPane.showMessageDialog(null, "Account deleted.");
				Stage stage = (Stage) this.showNew.getScene().getWindow();
		    	stage.close();

    			
    			
    			
    			
    			
    		}	
    		else {
    		    JOptionPane.showMessageDialog(null, "Passwords are not equal");

    		}
    	}
    	else {
		    JOptionPane.showMessageDialog(null, "Empty field");

		}

    }

    @FXML
    void visualizepassword7(MouseEvent event) {
    	showOld.setText(oldpastf.getText());
    	showNew.setText(newpasstf.getText());
    	showOld.setVisible(true);
    	showNew.setVisible(true);
    }

    @FXML
    void visualizepassword8(MouseEvent event) {
    	showOld.setVisible(false);
    	showNew.setVisible(false);
    }

}