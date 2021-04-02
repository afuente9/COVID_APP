package db.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NotifyVaccinesUsedController {

    @FXML
    private TextField textamountofvaccines;

    @FXML
    void AcceptVaccinesUsed(ActionEvent event) {
int amount= Integer.parseInt(textamountofvaccines.getText());
//TODO government.setVaccines()=government.getVaccines()-amount;
    }

    @FXML
    void CancelandBack(ActionEvent event) {
    	Stage stage = (Stage) this.textamountofvaccines.getScene().getWindow();
    	stage.close();
    }

}