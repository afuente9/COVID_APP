package db.GUI;

import javax.swing.JOptionPane;

import db.pojos.Lab;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    

}
