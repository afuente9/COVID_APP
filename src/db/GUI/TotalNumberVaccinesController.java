package db.GUI;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TotalNumberVaccinesController implements Initializable {

    @FXML
    private Label totalNumberVaccines;

    @FXML
    void BackFromTotalVaccines(ActionEvent event) {
    	Stage stage = (Stage) this.totalNumberVaccines.getScene().getWindow();
    	stage.close();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		totalNumberVaccines.setText("Total available number of vaccines: "/*government.getVaccines()*/);
		//rellenar la tabla
	}

}
