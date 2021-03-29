package db.GUI;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import db.pojos.Medication;
import db.pojos.Other_Pathologies;
import db.pojos.Patient;
import db.pojos.Sex;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private TextField PlacePatientTextField;

    @FXML
    private TextField MedicationPatientTextField;
    @FXML
    private TitledPane SeeAllMedication;

    @FXML
    private TitledPane SeeAllOtherPath;
    @FXML
    private Label AllMedLabel;

    @FXML
    private TextField DeleteMedNum;
    @FXML
    private TextField DeletePathologynum;

    @FXML
    private Label pathologylabel;

    @FXML
    private TextField OtherPathologiesPatientTextField;
    private List<Medication> medication_list=new ArrayList();
	private List<Other_Pathologies> other_pathologies_list=new ArrayList();
    @FXML
    private DialogPane AllMedDialog;

    @FXML
    void OnAcceptPatient(ActionEvent event) {

    	String Name_Text= NamePatientTextField.getText();
    	String Sex_Text= SexPatientTextField.getText();
    	String BirthDate_Text= BirthDatePatientTextField.getText();
    	String SSNum_Text= SSNumPatientTextField.getText();
    	String Height_Text= HeihtPatientTextField.getText();
    	String Weight_Text= WeightPatientTextField.getText();
    	String Blood_Text= BloodPatientTextField.getText();
    	String Hospital_Text= HospitalPatientTextField.getText();
    	String Place_Text= PlacePatientTextField.getText();
    	
    	db.pojos.Sex sex= Sex.valueOf(Sex_Text);
    	Date date = Date.valueOf(BirthDate_Text);
        Integer id = null;
        float height= Float.parseFloat(Height_Text);
        float weight= Float.parseFloat(Weight_Text);
        boolean infected=true;
        boolean alive=true;
        int score=0;
        boolean vaccinated=false;
        // TODO id=listadepacientes.size();
    	
    	
    	Patient p_new= new Patient(id,Place_Text,Name_Text,date,SSNum_Text,height,weight,sex,infected,alive,Hospital_Text,vaccinated,Blood_Text,medication_list,other_pathologies_list);
    	Main.getInter().addPatient(p_new);
    }

    @FXML
    void OnAddMedication(ActionEvent event) {
    	String medicationName= MedicationPatientTextField.getText();
    	int medicationId=medication_list.size();
    	Medication m_new= new Medication(medicationId,medicationName);
    	if(!medication_list.contains(m_new)) {
    		medication_list.add(m_new);
    		MedicationPatientTextField.setText("");
    		Iterator iter = medication_list.iterator();
    		String medications="";
    		while (iter.hasNext()) {
    		 medications+=iter.next()+"\n";
    		 }
    		AllMedLabel.setText(medications);    
    		
    	}
    	
    	
    	else {
    		// TODO mensaje error ya se ha añadido esa medication
    	}
    	

    }
    @FXML
    void DeletePathbyNum(ActionEvent event) {
    	other_pathologies_list.remove(Integer.parseInt(DeletePathologynum.getText()));
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
    	medication_list.remove(Integer.parseInt(DeleteMedNum.getText()));
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
    	int otherpatId= other_pathologies_list.size();
    	Other_Pathologies op_new= new Other_Pathologies(otherpatId,otherPathologiesName);
    	if(!other_pathologies_list.contains(op_new)){
    		other_pathologies_list.add(op_new);
    		OtherPathologiesPatientTextField.setText("");
    		Iterator iter = other_pathologies_list.iterator();
    		String paths="";
    		while (iter.hasNext()) {
    		 paths+=iter.next()+"\n";
    		 }
    		pathologylabel.setText(paths);   
    		
    	}
    	else {
    		//TODO mensaje error ya se ha añadido esa patologia

    	}
    	
    	
    	

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