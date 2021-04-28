package db.GUI;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import db.pojos.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TriggerSimulationController implements Initializable{

    @FXML
    private TableView<Patient> tablesimulation;

    @FXML
    private TableColumn scoreCol;

    @FXML
    private TableColumn nameCol;

    @FXML
    private TableColumn bdCol;

    @FXML
    private TableColumn SSnumcol;

    @FXML
    private TableColumn HospitalCol;

    @FXML
    private TableColumn DateIntroducedCol;
    private ObservableList<Patient> patientsTableList;


    @FXML
    void backfromsimulation(ActionEvent event) {
    	Stage stage = (Stage) this.tablesimulation.getScene().getWindow();
    	stage.close();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
    	patientsTableList = FXCollections.observableArrayList();
        this.scoreCol.setCellValueFactory(new PropertyValueFactory("score"));
        this.nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        this.bdCol.setCellValueFactory(new PropertyValueFactory("birthday"));
        this.SSnumcol.setCellValueFactory(new PropertyValueFactory("social_security"));
        this.HospitalCol.setCellValueFactory(new PropertyValueFactory("hospital"));
        this.DateIntroducedCol.setCellValueFactory(new PropertyValueFactory("DateIntroduced"));
        List <Patient> allpatients= Main.getInter().getSimulatedPatients(Main.getInter().getNumberVaccinesAdmin());
    	this.patientsTableList.addAll(allpatients);
    	this.tablesimulation.setItems(patientsTableList);
    	
    	
		
	}

}
