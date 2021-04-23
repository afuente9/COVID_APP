package db.GUI;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import db.pojos.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SearchPatientController implements Initializable {
 ObservableList list = FXCollections.observableArrayList();
 @FXML
 private Label numberofpatients;
 
    @FXML
    private ChoiceBox<String> SearchOptions;
    @FXML
    private TextField typeTextfield;
    @FXML
    private TableView<?> tablePatients;
    @FXML
    private DatePicker dateFrom;

    @FXML
    private DatePicker dateTo;
    @FXML
    void BackFromModify(ActionEvent event) {
    	Stage stage = (Stage) this.SearchOptions.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void ModifySearch(ActionEvent event) {
    	String feature = SearchOptions.getValue();
		String type = typeTextfield.getText();
		List<Patient> result = Main.getInter().searchPatientGeneric(feature,type);
		System.out.println("Those are the patients: \n" + result.toString());
        //SearchOptions.getValue();
    	// TODO Select de la base de datos todos los pacientes que lo cumplan
    	
    	
    }
    @FXML
    void Filterclick(ActionEvent event) {
//TODO Select patients where dateintroduced>datefrom y < date to e imprimir
    }
    
    @FXML
    void ModifyPatient(ActionEvent event) {
    	//TODO metodo modificar paciente

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	loadData();
    	SearchOptions.setValue("Select an option");
    	numberofpatients.setText("There are "+/*lista.gettotalnumberofpatients+*/"patients");
    	List <Patient> allpatients= Main.getInter().searchPatientByName("");
    	//TODO IMPRIMIR LISTA EN LA TABLA
    	//tablePatients.set
    	
    			
    	
    	
    }
    private void loadData() {
    	list.removeAll(list);
    	String a="Name";
    	String b="Age";
    	String c="Sex";
    	String cc="Height";
    	String d="Weight";
    	String e="SS num";
    	String f="Hospital";
    	String g="Infected";
    	String h="Date introduced";
    	list.addAll(a,b,c,cc,d,e,f,g,h);
    	SearchOptions.getItems().addAll(list);
    	

    }

}
