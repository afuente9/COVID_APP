package db.GUI;

import javax.swing.JOptionPane;

import db.pojos.Administration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NotifyVaccinesUsedController {
	Administration admin;

    @FXML
    private TextField textamountofvaccines;

    @FXML
    void AcceptVaccinesUsed(ActionEvent event) {
    if(textamountofvaccines.getText()!="") {	
    	
int amount=0;
boolean correctData=true;
try {
amount = Integer.parseInt(textamountofvaccines.getText());
}
catch(Exception e) {
correctData=false;
}

if(correctData==true&& Main.getInter().getNumberVaccinesAdmin(admin.getId())-amount>0&&amount>0) {
	Main.getInter().ModifyVaccinesAdmin(Integer.parseInt("-"+amount),admin.getId());
	textamountofvaccines.setText("");
    JOptionPane.showMessageDialog(null, "Use of vaccines notified");

}
else {
    JOptionPane.showMessageDialog(null, "Wrong number or maybe you do not have that amount of vaccines yet.");

}

    }else {
	    JOptionPane.showMessageDialog(null, "Empty field");

	} }

    public Administration getAdmin() {
		return admin;
	}

	public void setAdmin(Administration admin) {
		this.admin = admin;
	}

	@FXML
    void CancelandBack(ActionEvent event) {
    	Stage stage = (Stage) this.textamountofvaccines.getScene().getWindow();
    	stage.close();
    }

}