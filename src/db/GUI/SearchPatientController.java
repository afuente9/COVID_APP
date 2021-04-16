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
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class SearchPatientController implements Initializable {
 ObservableList list = FXCollections.observableArrayList();
 @FXML
 private Label numberofpatients;
 
    @FXML
    private ChoiceBox<String> SearchOptions;
    @FXML
    private TableView<?> tablePatients;

    @FXML
    void BackFromModify(ActionEvent event) {
    	Stage stage = (Stage) this.SearchOptions.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void ModifySearch(ActionEvent event) {

//System.out.println(SearchOptions.getValue());
    	//Select de la base de datos todos los pacientes que lo cumplan
    	
    }@FXML
    void ModifyPatient(ActionEvent event) {

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
