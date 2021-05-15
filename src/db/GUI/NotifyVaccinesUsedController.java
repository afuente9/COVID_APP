package db.GUI;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NotifyVaccinesUsedController {

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

if(correctData==true&& Main.getInter().getNumberVaccinesAdmin()-amount>0&&amount>0) {
	Main.getInter().ModifyVaccinesAdmin(Integer.parseInt("-"+amount));
	textamountofvaccines.setText("");
}
else {
    JOptionPane.showMessageDialog(null, "Wrong number or maybe you do not have that amount of vaccines yet.");

}

    }else {
	    JOptionPane.showMessageDialog(null, "Empty field");

	} }

    @FXML
    void CancelandBack(ActionEvent event) {
    	Stage stage = (Stage) this.textamountofvaccines.getScene().getWindow();
    	stage.close();
    }

}