package db.GUI;

import javax.swing.JOptionPane;

import db.pojos.Lab;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ModifyLaboratoryController {

	Lab lmodif=null;
    public Lab getLmodif() {
		return lmodif;
	}

	public void setLmodif(Lab lmodif) {
		this.lmodif = lmodif;
		
	}

	
	 @FXML
	    private TextField newnamelab;

	 


	    @FXML
	    private TextField showOld;

	    @FXML
	    private TextField showNew;


	  

	 

	    @FXML
	    private TextField modifyusername;

	    @FXML
	    private Label oldusername;

	    @FXML
	    private PasswordField oldpastf;

	    @FXML
	    private PasswordField newpasstf;
    @FXML
    private TextField newciflab;

    @FXML
    private TextField newadresslab;

    @FXML
    private Label oldlabcif;

    @FXML
    private Label oldlabname;

    @FXML
    private Label oldlabadress;

    @FXML
    void backfrommodify(ActionEvent event) {
    	Stage stage = (Stage) this.oldlabadress.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void newadressbutton(ActionEvent event) {
String newadress= newadresslab.getText();
if (!newadress.equals("")) {

oldlabadress.setText(newadress);
lmodif.setAddress(newadress);
newadresslab.setText("");
Main.getInter().modifyLab(lmodif.getId(), "adress", newadress);
}else {
    JOptionPane.showMessageDialog(null, "Empty field");

}


    }

    @FXML
    void newcifbutton(ActionEvent event) {
    	String newcif= newciflab.getText();
    	if (!newcif.equals("")) {

    	oldlabcif.setText(newcif);
    	lmodif.setCif(newcif);
    	newciflab.setText("");
Main.getInter().modifyLab(lmodif.getId(), "cif", newcif);
    }else {
	    JOptionPane.showMessageDialog(null, "Empty field");

	}
    }

    @FXML
    void newnamebutton(ActionEvent event) {
    	String newname= newnamelab.getText();

    	if (!newname.equals("")) {

    	oldlabname.setText(newname);
    	lmodif.setName(newname);
    	newnamelab.setText("");
    	Main.getInter().modifyLab(lmodif.getId(), "name", newname);
    }else {
	    JOptionPane.showMessageDialog(null, "Empty field");

	}
    }

	

	
	public void setOldlabcif(String oldlabcif) {
		this.oldlabcif.setText(oldlabcif); 
	}

	public void setOldlabname(String oldlabname) {
		this.oldlabname.setText(oldlabname); }

	public void setOldlabadress(String oldlabadress) {
		this.oldlabadress.setText(oldlabadress);	}
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
    

}
