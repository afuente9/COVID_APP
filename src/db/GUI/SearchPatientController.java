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

import db.pojos.Doctor;
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
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SearchPatientController implements Initializable {
 ObservableList list = FXCollections.observableArrayList();
 @FXML
 private Label numberofpatients;
 Doctor d;
    @FXML
    private ChoiceBox<String> SearchOptions;
    @FXML
    private TextField typeTextfield;
    @FXML
    private TableView<Patient> tablePatients;
    @FXML
    private TableColumn colName;
    
    private boolean firsttime=true;
    @FXML
    private TableColumn colBirth;
    @FXML
    private Button modifybutton;
    @FXML
    private Label searchbylabel;
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
    private Button usetable;
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
    void usethetable(ActionEvent event) {
    	this.tablePatients.setDisable(false);
    	this.modifybutton.setDisable(false);
    	this.modifybutton.setVisible(true);
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
        
        

    	List <Patient> allpatients= Main.getInter().getPatientsOfDoctor(d.getId());
    	this.patientsTableList.addAll(allpatients);
    	this.tablePatients.setItems(patientsTableList);
    }

    @FXML
    void ModifySearch(ActionEvent event) {
    	
    	
    	String feature = SearchOptions.getValue();
		String type = typeTextfield.getText();
		if(!type.equals("")&&!feature.equals("Select an option")){
			
	    
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

    	}
		List<Patient> result = Main.getInter().filterPatient(dateFromText, dateToText);
		this.patientsTableList.addAll(result);
    	this.tablePatients.setItems(patientsTableList);  
    	typeTextfield.setText("");    }
    
    @FXML
    void ModifyPatient(ActionEvent event) {
    	if (pselected==null) {
    	    JOptionPane.showMessageDialog(null, "Please, select the patient that you want to modify");

    	}
    	else {
    	String name= "ModifyPatient.fxml";
		ModifyPatientController controller = null;
		try {Pane root0 = (Pane) this.numberofpatients.getScene().getRoot();

		 ColorAdjust adj = new ColorAdjust(0, -0.9, -0.5, 0);

		 GaussianBlur blur = new GaussianBlur(10); 
		    adj.setInput(blur);
		 root0.setEffect(adj);
    		FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
    	Parent root;
    	
    		root = loader.load();
    		
    	  controller = loader.getController();
    	  controller.setpmodif(pselected);
    	  controller.setOldName(pselected.getName());
     	 controller.setOldSex(pselected.getSex().toString()); 
     	 controller.setOldBirthDate(pselected.getBirthday().toString());
    	 controller.setOldSSNUM(pselected.getSocial_security().toString());
    	 controller.setOldHeight(""+pselected.getHeight());
    	 controller.setOldWei(""+pselected.getWeight());
    	 controller.setOldBlood(    	 pselected.getBloodType());
    	 controller.setOldHospital(pselected.getHospital());
    	 controller.setOldPlace(pselected.getHos_location());
    	 controller.setOldVaccinated(""+pselected.getVaccinated());
    	 controller.setOther_pathologies_list(Main.getInter().getPathofromPatient(pselected.getId()));
    	 controller.setOldInfected(""+pselected.isInfected());
    	 List <Other_Pathologies> patholo_list= Main.getInter().getPathofromPatient(pselected.getId());
     	Iterator iter1 = patholo_list.iterator();

    	 String  paths = "";
		while (iter1.hasNext()) {
			paths += iter1.next() + "\n";
		}
		controller.setPathologylabel(paths);

    	 
    	 controller.setMedication_list(Main.getInter().getMedicationfromPatient(pselected.getId()));
    	 List <Medication> medication_list= Main.getInter().getMedicationfromPatient(pselected.getId());
    	Iterator iter = medication_list.iterator();
		String  medications = "";
		while (iter.hasNext()) {
			medications += iter.next() + "\n";
		}
		controller.setAllMedLabel(medications);
    	 
    	Scene scene = new Scene(root);
    	Stage stage = new Stage();

		stage.setResizable(false);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("Modify patient");
		stage.getIcons().add(new Image("https://image.flaticon.com/icons/png/512/2833/2833315.png"));

        stage.showAndWait();
        
		root0.setEffect(null);


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
        
        

    	List <Patient> allpatients= Main.getInter().getPatientsOfDoctor(d.getId());
    	this.patientsTableList.addAll(allpatients);
    	this.tablePatients.setItems(patientsTableList);
    	
    	
    	
    	
    	
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}			
    }
    	
    
        
        

    	
    	
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    		
    	loadData();
    	SearchOptions.setValue("Select an option");

    }
    
    public Doctor getD() {
		return d;
	}

	public void setD(Doctor d) {
		this.d = d;
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
