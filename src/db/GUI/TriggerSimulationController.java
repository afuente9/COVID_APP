package db.GUI;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class TriggerSimulationController implements Initializable{

    @FXML
    private TableView<?> tablesimulation;

    @FXML
    void backfromsimulation(ActionEvent event) {
    	Stage stage = (Stage) this.tablesimulation.getScene().getWindow();
    	stage.close();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//ordenar pacientes por score
//hacer una lista con los n primeros pacientes donde n es el numero de vacunas disponibles
		//imprimir esas personas
		
	}

}
