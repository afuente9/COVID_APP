package db.GUI;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ModifyGovUserController {

    @FXML
    private TextField modifyusername;

    @FXML
    private Label oldusername;

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
    public Label getOldusername() {
		return oldusername;
	}

	public void setOldusername(String oldusername) {
		this.oldusername.setText(oldusername);
	}

}
