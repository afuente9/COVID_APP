package db.GUI;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import db.pojos.Administration;
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
    public int id;

    @FXML
    private TableColumn scoreCol;

    @FXML
    private TableColumn nameCol;

    @FXML
    private TableColumn bdCol;
private Administration admin;
    @FXML
    private TableColumn SSnumcol;

   


	public TableColumn getScoreCol() {
		return scoreCol;
	}


	public TableColumn getNameCol() {
		return nameCol;
	}


	public TableColumn getBdCol() {
		return bdCol;
	}


	public Administration getAdmin() {
		return admin;
	}


	public TableColumn getSSnumcol() {
		return SSnumcol;
	}


	public TableColumn getHospitalCol() {
		return HospitalCol;
	}


	public TableColumn getDateIntroducedCol() {
		return DateIntroducedCol;
	}


	public ObservableList<Patient> getPatientsTableList() {
		return patientsTableList;
	}




	public void setScoreCol(TableColumn scoreCol) {
		this.scoreCol = scoreCol;
	}


	public void setNameCol(TableColumn nameCol) {
		this.nameCol = nameCol;
	}


	public void setBdCol(TableColumn bdCol) {
		this.bdCol = bdCol;
	}


	public void setAdmin(Administration admin) {
		this.admin = admin;
	}


	public void setSSnumcol(TableColumn sSnumcol) {
		SSnumcol = sSnumcol;
	}


	public void setHospitalCol(TableColumn hospitalCol) {
		HospitalCol = hospitalCol;
	}


	public void setDateIntroducedCol(TableColumn dateIntroducedCol) {
		DateIntroducedCol = dateIntroducedCol;
	}


	public void setPatientsTableList(ObservableList<Patient> patientsTableList) {
		this.patientsTableList = patientsTableList;
	}


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
	    	this.tablesimulation.setItems(patientsTableList);
	
		
	}
	 @FXML
	    void simulation(ActionEvent event) {
		 tablesimulation.setDisable(false);
	        List <Patient> allpatients= Main.getInter().getSimulatedPatients(Main.getInter().getNumberVaccinesAdmin(this.admin.getId()), this.admin.getId());
	    	this.patientsTableList.addAll(allpatients);
	    	this.tablesimulation.setItems(patientsTableList);
	    }

}
