package db.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import db.pojos.Medication;
import db.pojos.Other_Pathologies;
import db.pojos.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SearchPatientbyLabController implements Initializable {
 ObservableList list = FXCollections.observableArrayList();
 @FXML
 private Label numberofpatients;
 
    @FXML
    private ChoiceBox<String> SearchOptions;
    @FXML
    private TextField typeTextfield;
    @FXML
    private TableView<Patient> tablePatients;
    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colBirth;

    @FXML
    private TableColumn colSex;

    @FXML
    private TableColumn colHeight;

    @FXML
    private TableColumn colWeight;

    @FXML
    private TableColumn colSSnum;
    Patient pselected;

    @FXML
    private TableColumn colHos;

    @FXML
    private TableColumn colInfected;

    @FXML
    private TableColumn colDateIntroduced;
 

    @FXML
    private TableColumn colAlive;

    @FXML
    private Button SearchButton;

    @FXML
    private TableColumn colBloodType;

    @FXML
    private TableColumn colVaccinated;

    @FXML
    private TableColumn colMedica;

    @FXML
    private TableColumn colPatho;
    
    private ObservableList<Patient> patientsTableList;

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
    	this.patientsTableList.clear();
    	String feature = SearchOptions.getValue();
		String type = typeTextfield.getText();
		if (feature == "Birth date") {
			List<Patient> result = Main.getInter().getPatientbyBD(type);
			System.out.println("Those are the patients: \n" + result.toString());
			this.patientsTableList.addAll(result);
	    	this.tablePatients.setItems(patientsTableList);  
	    	typeTextfield.setText("");
		}else if (feature == "Date introduced"){
			List<Patient> result = Main.getInter().getPatientbyDateIntro(type);
			System.out.println("Those are the patients: \n" + result.toString());
			this.patientsTableList.addAll(result);
	    	this.tablePatients.setItems(patientsTableList);  
	    	typeTextfield.setText("");
		}
		else {
			List<Patient> result = Main.getInter().searchPatientGeneric(feature,type);
			System.out.println("Those are the patients: \n" + result.toString());
			this.patientsTableList.addAll(result);
	    	this.tablePatients.setItems(patientsTableList);  
	    	typeTextfield.setText("");
		}
		
    	
    	
    }
    @FXML
    void Filterclick(ActionEvent event) {
    	this.patientsTableList.clear();
    	String dateFromText =dateFrom.getValue().toString(); 
		String dateToText = dateTo.getValue().toString();
		List<Patient> result = Main.getInter().filterPatient(dateFromText, dateToText);
		System.out.println("Those are the patients: \n" + result.toString());
		this.patientsTableList.addAll(result);
    	this.tablePatients.setItems(patientsTableList);  
    	typeTextfield.setText("");    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	loadData();
    	SearchOptions.setValue("Select an option");
    	int numberpatients= Main.getInter().getNumberofPatients();
    	numberofpatients.setText("There are "+numberpatients+" patients");
    	patientsTableList = FXCollections.observableArrayList();
        this.colName.setCellValueFactory(new PropertyValueFactory("name"));
        this.colBirth.setCellValueFactory(new PropertyValueFactory("birthday"));
        this.colSex.setCellValueFactory(new PropertyValueFactory("sex"));
        this.colHeight.setCellValueFactory(new PropertyValueFactory("height"));
        this.colWeight.setCellValueFactory(new PropertyValueFactory("weight"));
        this.colSSnum.setCellValueFactory(new PropertyValueFactory("social_security"));
        this.colHos.setCellValueFactory(new PropertyValueFactory("hospital"));
        this.colInfected.setCellValueFactory(new PropertyValueFactory("infected"));
        this.colAlive.setCellValueFactory(new PropertyValueFactory("alive"));
        this.colBloodType.setCellValueFactory(new PropertyValueFactory("bloodType"));
        this.colVaccinated.setCellValueFactory(new PropertyValueFactory("Vaccinated"));
        this.colMedica.setCellValueFactory(new PropertyValueFactory("medication"));
        this.colPatho.setCellValueFactory(new PropertyValueFactory("other_pathologies"));
        this.colDateIntroduced.setCellValueFactory(new PropertyValueFactory("DateIntroduced"));
        
        

    	List <Patient> allpatients= Main.getInter().searchPatientByName("");
    	this.patientsTableList.addAll(allpatients);
    	this.tablePatients.setItems(patientsTableList);
    	
    	
    			
    	
    	
    }
    private void loadData() {
    	list.removeAll(list);
    	String a="Name";
    	String b="Birth date";
    	String c="Sex";
    	String cc="Height";
    	String d="Weight";
    	String e="SS num";
    	String f="Hospital";
    	String g="Infected";
    	String h="Date introduced";
    	String i="Blood type";

    	list.addAll(a,b,c,cc,d,e,f,g,h,i);
    	SearchOptions.getItems().addAll(list);
    	

    }
    @FXML
    void selectRow(MouseEvent event) {
         pselected = this.tablePatients.getSelectionModel().getSelectedItem();

    }
    

}
