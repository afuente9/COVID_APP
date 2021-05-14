package db.GUI;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import db.pojos.Medication;
import db.pojos.Other_Pathologies;
import db.pojos.Patient;
import db.pojos.Sex;
import db.ui.Menu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddPatientController {

    @FXML
    private TextField NamePatientTextField;

    @FXML
    private TextField SexPatientTextField;

    @FXML
    private TextField BirthDatePatientTextField;

    @FXML
    private TextField SSNumPatientTextField;

    @FXML
    private TextField HeihtPatientTextField;

    @FXML
    private TextField WeightPatientTextField;

    @FXML
    private TextField BloodPatientTextField;

    @FXML
    private TextField HospitalPatientTextField;
    @FXML
    private Button addMed;

    @FXML
    private Button addPat;
    @FXML
    private TextField PlacePatientTextField;

    @FXML
    private TextField MedicationPatientTextField;
    @FXML
    private TitledPane SeeAllMedication;

    @FXML
    private TextField countrytext;
    @FXML
    private TitledPane SeeAllMedication1;
    @FXML
    private Label AllMedLabel;

    @FXML
    private TextField DeleteMedNum;
    @FXML
    private TextField DeletePathologynum;
    @FXML
    private TextField VaccinatedTEXTFIELD;
    @FXML
    private Label pathologylabel;
    Patient p_new;

    @FXML
    private TextField OtherPathologiesPatientTextField;
    private List<Medication> medication_list=new ArrayList();
	private List<Other_Pathologies> other_pathologies_list=new ArrayList();
    @FXML
    private DialogPane AllMedDialog;

   
    @FXML
    void onConfirmWithoutMedPat(ActionEvent event) {

    
    	String Name_Text= NamePatientTextField.getText();
    	String Sex_Text= SexPatientTextField.getText();
    	String BirthDate_Text= BirthDatePatientTextField.getText();
    	String SSNum_Text= SSNumPatientTextField.getText();
    	String Height_Text= HeihtPatientTextField.getText();
    	String Weight_Text= WeightPatientTextField.getText();
    	String Blood_Text= BloodPatientTextField.getText();
    	String Hospital_Text= HospitalPatientTextField.getText();
    	String Place_Text= PlacePatientTextField.getText();
    	String VaccinatedText=VaccinatedTEXTFIELD.getText();
    	
    	
    	db.pojos.Sex sex= Sex.valueOf(Sex_Text);
    	Date date = Date.valueOf(BirthDate_Text);
        Integer id = null;
        float height= Float.parseFloat(Height_Text);
        float weight= Float.parseFloat(Weight_Text);
        boolean infected=true;
        boolean alive=true;
        int score=0;
        boolean vaccinated= Boolean.parseBoolean(VaccinatedText);
        Date dateIntroduced= Date.valueOf(LocalDate.now());
        int countryId= Main.getInter().searchadminIDByName(countrytext.getText());
        
        p_new= new Patient(Place_Text,Name_Text,date,SSNum_Text,height,weight,sex,infected,alive,Hospital_Text,vaccinated,Blood_Text,dateIntroduced,countryId);
    	Main.getInter().addPatient(p_new);
    
    	
    	
    	 NamePatientTextField.setDisable(true);
    	 SexPatientTextField.setDisable(true);
    	BirthDatePatientTextField.setDisable(true);
    	 SSNumPatientTextField.setDisable(true);
    	 HeihtPatientTextField.setDisable(true);
    	 WeightPatientTextField.setDisable(true);
    	 BloodPatientTextField.setDisable(true);
       HospitalPatientTextField.setDisable(true);
    	 PlacePatientTextField.setDisable(true);
    	VaccinatedTEXTFIELD.setDisable(true);
    	OtherPathologiesPatientTextField.setDisable(false);
    	MedicationPatientTextField.setDisable(false);
    	countrytext.setDisable(false);
    	addPat.setDisable(false);
    	addMed.setDisable(false);
    	SeeAllMedication1.setDisable(false);
    	SeeAllMedication.setDisable(false);
    	
    
    }
    @FXML
    void OnAddMedication(ActionEvent event) {
    	//	TODO CONTROLAR SI YA ESTA METIDA
    	String medicationName= MedicationPatientTextField.getText();
    	Medication m_new= new Medication(medicationName);
    	
    	
        Main.getInter().addMedication(m_new);
        Main.getInter().assignMed(Main.getInter().getLastPatient().getId(), Main.getInter().getLastMedication());
        Main.getInter().getLastPatient().setMedication(medication_list);
    	MedicationPatientTextField.setText("");
    	medication_list.add(Main.getInter().getLastMedication());
    		Iterator iter = medication_list.iterator();
    		String medications="";
    		while (iter.hasNext()) {
    		 medications+=iter.next()+"\n";
    		 }
    		AllMedLabel.setText(medications);    
    		
    	}
    	
    	
    
    	

    
    @FXML
    void DeletePathbyNum(ActionEvent event) {

        Main.getInter().deleteAssignmentPathology(Main.getInter().getLastPatient().getId(),Integer.parseInt(DeletePathologynum.getText()) );

    	
    	other_pathologies_list.remove(Main.getInter().getPathologyId(Integer.parseInt(DeletePathologynum.getText())));
    	
    	DeletePathologynum.setText("");
    	Iterator iter = other_pathologies_list.iterator();
		String paths="";
		while (iter.hasNext()) {
		 paths+=iter.next()+"\n";
		 }
		pathologylabel.setText(paths);   
    }

    @FXML
    void Deletemedbynum(ActionEvent event) {
    	
        Main.getInter().deleteAssignmentMedication(Main.getInter().getLastPatient().getId(),Integer.parseInt(DeleteMedNum.getText()) );

    	medication_list.remove(Main.getInter().getMedicationId(Integer.parseInt(DeleteMedNum.getText())));
    	DeleteMedNum.setText("");
    	Iterator iter = medication_list.iterator();
		String medications="";
		while (iter.hasNext()) {
		 medications+=iter.next()+"\n";
		 }
		AllMedLabel.setText(medications);   

    }

    @FXML
    void OnAddPathology(ActionEvent event) {
    	String otherPathologiesName= OtherPathologiesPatientTextField.getText();
    	Other_Pathologies op_new= new Other_Pathologies(otherPathologiesName);
    		
    		 Main.getInter().addOtherPathologies(op_new);
    	     Main.getInter().assignPatho(Main.getInter().getLastPatient().getId(), Main.getInter().getLastPath());
    	     Main.getInter().getLastPatient().setOther_pathologies(other_pathologies_list);
     		
    	     other_pathologies_list.add(Main.getInter().getLastPath());
    		OtherPathologiesPatientTextField.setText("");
    		Iterator iter = other_pathologies_list.iterator();
    		String paths="";

    		while (iter.hasNext()) {
    		 paths+=iter.next()+"\n";
    		 }
    		pathologylabel.setText(paths);   
    		
    	}
    @FXML
    void SeeAllMed(ActionEvent event) {
    	String name= "SeeAllMedView.fxml";
		SeeAllMedController controller = null;
		openWindow(name,controller);

    }

    @FXML
    void SeeAllPath(ActionEvent event) {
    	String name= "SeeAllPathView.fxml";
    	SeeAllPathController controller = null;
		openWindow(name,controller);

    }
    @FXML
    void OnCancelPatient(ActionEvent event) {
    	Stage stage = (Stage) this.PlacePatientTextField.getScene().getWindow();
    	stage.close();
    }
    void openWindow(String name,Object controller) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
    	Parent root;
    	
    		root = loader.load();
    	
    	  controller = loader.getController();
    	Scene scene = new Scene(root);
    	Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}

    }

}