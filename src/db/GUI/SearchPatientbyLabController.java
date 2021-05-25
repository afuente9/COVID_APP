package db.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import db.pojos.Lab;
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
 Lab lab;
 boolean firsttime=true;
 @FXML
 private Button usetable;
 
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
    private Label searchbylabel;
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
    void usethetable(ActionEvent event) {
    	this.tablePatients.setDisable(false);
    	
    	this.usetable.setDisable(true);
    	this.usetable.setVisible(false);
    	this.SearchOptions.setDisable(false);
    	this.typeTextfield.setDisable(false);
    	this.SearchButton.setDisable(false);
    	this.searchbylabel.setDisable(false);
    
    	
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
        
        
       	List <Patient> allpatients= Main.getInter().getPatientsOfLab(lab.getId());
    	this.patientsTableList.addAll(allpatients);
    	this.tablePatients.setItems(patientsTableList);
    }
    @FXML
    void ModifySearch(ActionEvent event) {

    	
    	
    	String feature = SearchOptions.getValue();
		String type = typeTextfield.getText();
		if (!type.equals("") && !feature.equals("Select an option")){
	    
			
				
			
		
			
			
			
			
			
			this.patientsTableList.clear();

		if (feature == "Birth date") {
			boolean correctdate=true;
			try {
	        	Date date = Date.valueOf(type);

	    	}catch(Exception e) {
	            JOptionPane.showMessageDialog(null, "Wrong date. Please, use the format: yyyy-mm-dd");
	            correctdate=false;

	    	}
			if(correctdate==true) {
				
				
				
				
		    //HI RODRIGO! I HOPE YOU ARE ENJOYING OUR APPLICATION :)
			//WE SHOULD HAVE ADDED THE ID OF THE LAB IN THE METHODS BUT AS IT'S AN "ALL TO ALL" RELATION, WE DECIDED THAT
		    //IT'S UNNECESSARY BECAUSE ALL THE PATIENTS WILL APPEAR BECAUSE IT WON'T FILTER ANYTHING.
			
				
				
				List<Patient> result = Main.getInter().getPatientbyBD(type);
			this.patientsTableList.addAll(result);
	    	this.tablePatients.setItems(patientsTableList);  
	    	typeTextfield.setText("");
			}
		}else if (feature == "Date introduced"){
			boolean correctdate=true;
			try {
	        	Date date = Date.valueOf(type);

	    	}catch(Exception e) {
	            JOptionPane.showMessageDialog(null, "Wrong date. Please, use the format: yyyy-mm-dd");
	            correctdate=false;

	    	}
			if(correctdate==true) {
			List<Patient> result = Main.getInter().getPatientbyDateIntro(type);
			this.patientsTableList.addAll(result);
	    	this.tablePatients.setItems(patientsTableList);  
	    	typeTextfield.setText("");
			}
		}
		else {
			List<Patient> result = Main.getInter().searchPatientGeneric(feature,type);
			this.patientsTableList.addAll(result);
	    	this.tablePatients.setItems(patientsTableList);  
	    	typeTextfield.setText("");
	
    }
		}
	else {
		JOptionPane.showMessageDialog(null, "Empty field ");
	}
    	
    	
    	
    }
    public Lab getLab() {
		return lab;
	}

	public void setLab(Lab lab) {
		this.lab = lab;
	}

	@FXML
    void Filterclick(ActionEvent event) {
    	this.patientsTableList.clear();
    	String dateFromText="";
    	String dateToText="";
    	if(dateFrom.getValue()==null) {
    		 dateFromText = Date.valueOf(LocalDate.now()).toString();
    	}
    	else {
        	 dateFromText =dateFrom.getValue().toString(); 

    	}
    	if(dateTo.getValue()==null) {
    		 dateToText = Date.valueOf(LocalDate.now()).toString();

    	}
    	else {
    		 dateToText = dateTo.getValue().toString();

    	}List<Patient> result = Main.getInter().filterPatient(dateFromText, dateToText);
		this.patientsTableList.addAll(result);
    	this.tablePatients.setItems(patientsTableList);  
    	typeTextfield.setText("");    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	loadData();
    	SearchOptions.setValue("Select an option");
    			
    	
    	
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
