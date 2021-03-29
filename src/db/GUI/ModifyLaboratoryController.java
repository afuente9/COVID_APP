package db.GUI;

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
oldlabadress.setText(newadress);
lmodif.setAddress(newadress);
newadresslab.setText("");

    }

    @FXML
    void newcifbutton(ActionEvent event) {
    	String newcif= newciflab.getText();
    	oldlabcif.setText(newcif);
    	lmodif.setCif(newcif);
    	newciflab.setText("");
    }

    @FXML
    void newnamebutton(ActionEvent event) {
    	String newname= newnamelab.getText();
    	oldlabname.setText(newname);
    	lmodif.setName(newname);
    	newnamelab.setText("");
    }

	

	
	public void setOldlabcif(String oldlabcif) {
		this.oldlabcif.setText(oldlabcif); 
	}

	public void setOldlabname(String oldlabname) {
		this.oldlabname.setText(oldlabname); }

	public void setOldlabadress(String oldlabadress) {
		this.oldlabadress.setText(oldlabadress);	}
    

}
