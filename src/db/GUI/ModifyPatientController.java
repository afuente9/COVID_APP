package db.GUI;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import db.pojos.Doctor;
import db.pojos.Medication;
import db.pojos.Other_Pathologies;
import db.pojos.Patient;
import db.pojos.Sex;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ModifyPatientController implements Initializable {

	Patient pmodif;

	public void setpmodif(Patient pmodif) {
		this.pmodif = pmodif;
	}

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
	private TextField OtherPathologiesPatientTextField;

	@FXML
	private Button addMed;

	@FXML
	private Button addPat;

	@FXML
	private TitledPane SeeAllMedication;

	@FXML
	private TextField DeleteMedNum;

	@FXML
	private Label AllMedLabel;

	@FXML
	private TitledPane SeeAllMedication1;

	@FXML
	private TextField DeletePathologynum;

	@FXML
	private Label pathologylabel;

	@FXML
	private TextField VaccinatedTEXTFIELD;

	@FXML
	private Label oldHospital;


    @FXML
    private TextField InfectedTEXTFIELD1;

    @FXML
    private Label oldInfected;

	@FXML
	private Label oldBlood;

	@FXML
	private Label oldBloodGroup;

	@FXML
	private Label oldWei;

	@FXML
	private Label oldHeight;

	@FXML
	private Label oldSSNUM;

	@FXML
	private Label oldBirthDate;

	@FXML
	private Label oldSex;
	@FXML
	private Label oldPlace;

	@FXML
	private Label oldName;

	@FXML
	private Label oldVaccinated;
	private List<Medication> medication_list ;
	private List<Other_Pathologies> other_pathologies_list ;

	@FXML
	void AcceptHosp(ActionEvent event) {
		String newHos = HospitalPatientTextField.getText();
		if(!newHos.equals("")) {
		oldHospital.setText(newHos);
		pmodif.setHospital(newHos);
		HospitalPatientTextField.setText("");
		Main.getInter().modifyPatient(this.pmodif.getId(), "hospital", newHos);
	}
		else {
    	    JOptionPane.showMessageDialog(null, "Empty field");

		}
	}

	@FXML
	void OnBackModifyData(ActionEvent event) {
		Stage stage = (Stage) this.NamePatientTextField.getScene().getWindow();
		stage.close();
	}

	@FXML
	void AcceptPlace(ActionEvent event) {
		
		boolean correctData=true;
		String newPlace = PlacePatientTextField.getText();
		
		if (!(newPlace.equals("ICU")||newPlace.equals("Floor")||newPlace.equals("Home"))) {
            JOptionPane.showMessageDialog(null, "Wrong place. Please, put: ICU, Floor, Home");
            correctData=false;

    	}
		if( correctData==true) {
		oldPlace.setText(newPlace);
		pmodif.setHos_location(newPlace);
		PlacePatientTextField.setText("");
		Main.getInter().modifyPatient(this.pmodif.getId(), "hos_location", newPlace);
		}
	}

	@FXML
	void AcceptWeig(ActionEvent event) {
		String newWeig = WeightPatientTextField.getText();
		boolean correctData=true;
		try {
            float weight= Float.parseFloat(newWeig);
            if(weight<0 ||weight>500) {
           	 JOptionPane.showMessageDialog(null, "Wrong weight.");
                correctData=false;
            }
            }catch(Exception e) {
               JOptionPane.showMessageDialog(null, "Wrong weight.");
               correctData=false;

       	}
    		if( correctData==true) {

		oldWei.setText(newWeig);

		pmodif.setWeight(Float.parseFloat(newWeig));
		WeightPatientTextField.setText("");
		Main.getInter().modifyPatient(this.pmodif.getId(), "weight", newWeig);

    		}
	
	}

	

	@FXML
	void DeletePathbyNum(ActionEvent event) {
		boolean isnumber=true;
    	try {
    		int num= Integer.parseInt(DeletePathologynum.getText());
    		if(num<0 ) {
           	 JOptionPane.showMessageDialog(null, "Wrong number.");
     		isnumber=false;
           }

    	}catch(Exception e) {
    		isnumber=false;
    	    JOptionPane.showMessageDialog(null, "Please put a number to delete a pathology");

    		
    	}
    	
    	if(isnumber==true) {

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
    }

	@FXML
	void Deletemedbynum(ActionEvent event) {
		
		boolean isnumber=true;
    	try {
    		int num = Integer.parseInt(DeleteMedNum.getText());
    		if(num<0) {
    			isnumber=false;
        	    JOptionPane.showMessageDialog(null, "Wrong number");

    		}
    	}catch(Exception e) {
    		isnumber=false;
    	    JOptionPane.showMessageDialog(null, "Please put a number to delete a medication");

    		
    	}
    	
    	if(isnumber==true) {
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
	}

	@FXML
	void OnAddMedication(ActionEvent event) {
		
		String medicationName = MedicationPatientTextField.getText();
		List<Medication> medicationsPatient = 	Main.getInter().getMedicationfromPatientwithoutID(Main.getInter().getPatient(this.pmodif.getId()).getId());

		if (!medicationName.equals("")) {
		Medication m_new = new Medication(medicationName);

		if(medicationsPatient.contains(m_new)) {
    		
    	    JOptionPane.showMessageDialog(null, "Medication already added to this patient");
    		
    	}else {
    		if(Main.getInter().MedicationRegisteredByName(medicationName)==false) {
    		     m_new= new Medication(medicationName);

    		
			Main.getInter().addMedication(m_new);
			Main.getInter().assignMed(this.pmodif.getId(), Main.getInter().getLastMedication());
			Main.getInter().getPatient(this.pmodif.getId()).setMedication(medication_list);
			MedicationPatientTextField.setText("");
			String  medications = "";
			medication_list.add(Main.getInter().getLastMedication());

			Iterator iter = medication_list.iterator();
			while (iter.hasNext()) {
				medications += iter.next() + "\n";
			}
			AllMedLabel.setText(medications);
	}
    		else {
   		     m_new= new Medication(medicationName);
 			Main.getInter().assignMed(this.pmodif.getId(), Main.getInter().getMedication(medicationName));
			Main.getInter().getPatient(this.pmodif.getId()).setMedication(medication_list);
			MedicationPatientTextField.setText("");
			String  medications = "";
			medication_list.add(Main.getInter().getLastMedication());

			Iterator iter = medication_list.iterator();
			while (iter.hasNext()) {
				medications += iter.next() + "\n";
			}
			AllMedLabel.setText(medications);
    			
    		}
    	}

	}else {
	    JOptionPane.showMessageDialog(null, "Empty field");

	}
	}

	@FXML
	void OnAddPathology(ActionEvent event) {
		String otherPathologiesName = OtherPathologiesPatientTextField.getText();
		if (!otherPathologiesName.equals("")) {
		Other_Pathologies op_new = new Other_Pathologies(otherPathologiesName);
		List<Other_Pathologies> pathsPatient = 	Main.getInter().getPatfromPatientwithoutID(Main.getInter().getLastPatient().getId());
		if(pathsPatient.contains(op_new)){
    	    JOptionPane.showMessageDialog(null, "Pathology already added to this patient");

    	}else {
        	if (Main.getInter().PathologyRegisteredByName(otherPathologiesName)==false) {
            	
			Main.getInter().addOtherPathologies(op_new);
			Main.getInter().assignPatho(this.pmodif.getId(), Main.getInter().getLastPath());
			Main.getInter().getPatient(this.pmodif.getId()).setOther_pathologies(other_pathologies_list);
   	     other_pathologies_list.add(Main.getInter().getLastPath());

			OtherPathologiesPatientTextField.setText("");
			String paths = "";


			Iterator iter = other_pathologies_list.iterator();
			while (iter.hasNext()) {
				paths += iter.next() + "\n";
			}
			pathologylabel.setText(paths);
        	}
        	else {
        		 Main.getInter().assignPatho(this.pmodif.getId(), Main.getInter().getPatByName(otherPathologiesName));
     			Main.getInter().getPatient(this.pmodif.getId()).setOther_pathologies(other_pathologies_list);
        	     other_pathologies_list.add( Main.getInter().getPatByName(otherPathologiesName));
         		OtherPathologiesPatientTextField.setText("");
         		Iterator iter = other_pathologies_list.iterator();
        		String paths="";

        		while (iter.hasNext()) {
        		 paths+=iter.next()+"\n";
        		 }
        		pathologylabel.setText(paths);   
        	}
		} 
	}else {
	    JOptionPane.showMessageDialog(null, "Empty field");

	}
	}
	

	@FXML
	void OnCancelPatient(ActionEvent event) {
		
		Stage stage = (Stage) this.BirthDatePatientTextField.getScene().getWindow();
    	stage.close();
	}

	@FXML
	void acceptBD(ActionEvent event) {
		String newbdate = BirthDatePatientTextField.getText();

		boolean correctData=true;
		try {
        	Date date = Date.valueOf(newbdate);

    	}catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Wrong date. Please, use the format: yyyy-mm-dd");
            correctData=false;
    	}
		
		if (correctData==true) {
		oldBirthDate.setText(newbdate);
		pmodif.setBirthday(Date.valueOf(newbdate));
		BirthDatePatientTextField.setText("");
		Main.getInter().modifyPatient(this.pmodif.getId(), "birthday", newbdate);

	}
	}

	@FXML
	void acceptBlood(ActionEvent event) {
		String newblood = BloodPatientTextField.getText();
		boolean correctData=true;

		if (!(newblood.equals("AB+")||newblood.equals("AB-")||newblood.equals("A+")||newblood.equals("A-")||
				newblood.equals("B+")||newblood.equals("B-")||newblood.equals("0+")||newblood.equals("0-"))) {
            JOptionPane.showMessageDialog(null, "Wrong blood type.");
            correctData=false;

    	}
		if(correctData==true) {
		oldBlood.setText(newblood);

		pmodif.setBloodType(newblood);

		BloodPatientTextField.setText("");

		Main.getInter().modifyPatient(this.pmodif.getId(), "bloodType", newblood);

	}
	}

	public Patient getPmodif() {
		return pmodif;
	}

	public TextField getNamePatientTextField() {
		return NamePatientTextField;
	}

	public TextField getSexPatientTextField() {
		return SexPatientTextField;
	}

	public TextField getBirthDatePatientTextField() {
		return BirthDatePatientTextField;
	}

	public TextField getSSNumPatientTextField() {
		return SSNumPatientTextField;
	}

	public TextField getHeihtPatientTextField() {
		return HeihtPatientTextField;
	}

	public TextField getWeightPatientTextField() {
		return WeightPatientTextField;
	}

	public TextField getBloodPatientTextField() {
		return BloodPatientTextField;
	}

	public TextField getHospitalPatientTextField() {
		return HospitalPatientTextField;
	}

	public TextField getPlacePatientTextField() {
		return PlacePatientTextField;
	}

	public TextField getMedicationPatientTextField() {
		return MedicationPatientTextField;
	}

	public TextField getOtherPathologiesPatientTextField() {
		return OtherPathologiesPatientTextField;
	}

	public Button getAddMed() {
		return addMed;
	}

	public Button getAddPat() {
		return addPat;
	}

	public TitledPane getSeeAllMedication() {
		return SeeAllMedication;
	}

	public TextField getDeleteMedNum() {
		return DeleteMedNum;
	}

	public Label getAllMedLabel() {
		return AllMedLabel;
	}

	public TitledPane getSeeAllMedication1() {
		return SeeAllMedication1;
	}

	public TextField getDeletePathologynum() {
		return DeletePathologynum;
	}

	public Label getPathologylabel() {
		return pathologylabel;
	}

	public TextField getVaccinatedTEXTFIELD() {
		return VaccinatedTEXTFIELD;
	}

	public Label getOldHospital() {
		return oldHospital;
	}

	public Label getOldBlood() {
		return oldBlood;
	}

	public Label getOldBloodGroup() {
		return oldBloodGroup;
	}

	public Label getOldWei() {
		return oldWei;
	}

	public Label getOldHeight() {
		return oldHeight;
	}

	public Label getOldSSNUM() {
		return oldSSNUM;
	}

	public Label getOldBirthDate() {
		return oldBirthDate;
	}

	public Label getOldSex() {
		return oldSex;
	}

	public Label getOldPlace() {
		return oldPlace;
	}

	public Label getOldName() {
		return oldName;
	}

	public Label getOldVaccinated() {
		return oldVaccinated;
	}

	public List<Medication> getMedication_list() {
		return medication_list;
	}

	public List<Other_Pathologies> getOther_pathologies_list() {
		return other_pathologies_list;
	}

	public void setPmodif(Patient pmodif) {
		this.pmodif = pmodif;
	}

	public void setNamePatientTextField(TextField namePatientTextField) {
		NamePatientTextField = namePatientTextField;
	}

	public void setSexPatientTextField(TextField sexPatientTextField) {
		SexPatientTextField = sexPatientTextField;
	}

	public void setBirthDatePatientTextField(TextField birthDatePatientTextField) {
		BirthDatePatientTextField = birthDatePatientTextField;
	}

	public void setSSNumPatientTextField(TextField sSNumPatientTextField) {
		SSNumPatientTextField = sSNumPatientTextField;
	}

	public void setHeihtPatientTextField(TextField heihtPatientTextField) {
		HeihtPatientTextField = heihtPatientTextField;
	}

	public void setWeightPatientTextField(TextField weightPatientTextField) {
		WeightPatientTextField = weightPatientTextField;
	}

	public void setBloodPatientTextField(TextField bloodPatientTextField) {
		BloodPatientTextField = bloodPatientTextField;
	}

	public void setHospitalPatientTextField(TextField hospitalPatientTextField) {
		HospitalPatientTextField = hospitalPatientTextField;
	}

	public void setPlacePatientTextField(TextField placePatientTextField) {
		PlacePatientTextField = placePatientTextField;
	}

	public void setMedicationPatientTextField(TextField medicationPatientTextField) {
		MedicationPatientTextField = medicationPatientTextField;
	}

	public void setOtherPathologiesPatientTextField(TextField otherPathologiesPatientTextField) {
		OtherPathologiesPatientTextField = otherPathologiesPatientTextField;
	}

	public void setAddMed(Button addMed) {
		this.addMed = addMed;
	}

	public void setAddPat(Button addPat) {
		this.addPat = addPat;
	}

	public void setSeeAllMedication(TitledPane seeAllMedication) {
		SeeAllMedication = seeAllMedication;
	}

	public void setDeleteMedNum(TextField deleteMedNum) {
		DeleteMedNum = deleteMedNum;
	}

	public void setAllMedLabel(String allMedLabel) {
		AllMedLabel.setText(allMedLabel); 
	}

	public void setSeeAllMedication1(TitledPane seeAllMedication1) {
		SeeAllMedication1 = seeAllMedication1;
	}

	public void setDeletePathologynum(TextField deletePathologynum) {
		DeletePathologynum = deletePathologynum;
	}

	public void setPathologylabel(String pathologylabel) {
		this.pathologylabel.setText(pathologylabel);
	}

	public void setVaccinatedTEXTFIELD(TextField vaccinatedTEXTFIELD) {
		VaccinatedTEXTFIELD = vaccinatedTEXTFIELD;
	}

	public void setOldHospital(String oldHospital) {
		this.oldHospital.setText(oldHospital);
	}

	public void setOldBlood(String oldBlood) {
		this.oldBlood.setText(oldBlood);
	}

	public void setOldBloodGroup(String oldBloodGroup) {
		this.oldBloodGroup.setText(oldBloodGroup);
	}

	public void setOldWei(String oldWei) {
		this.oldWei.setText(oldWei);
	}

	public void setOldHeight(String oldHeight) {
		this.oldHeight.setText(oldHeight);
	}

	public void setOldSSNUM(String oldSSNUM) {
		this.oldSSNUM.setText(oldSSNUM);
	}

	public void setOldBirthDate(String oldBirthDate) {
		this.oldBirthDate.setText(oldBirthDate);
	}

	public void setOldSex(String oldSex) {
		this.oldSex.setText(oldSex);
	}

	public void setOldPlace(String oldPlace) {
		this.oldPlace.setText(oldPlace);
	}

	public void setOldName(String oldName) {
		this.oldName.setText(oldName);
	}

	public void setOldVaccinated(String oldVaccinated) {
		this.oldVaccinated.setText(oldVaccinated);
		;
	}

	public void setMedication_list(List<Medication> medication_list) {
		this.medication_list = medication_list;
	}

	public void setOther_pathologies_list(List<Other_Pathologies> other_pathologies_list) {
		this.other_pathologies_list = other_pathologies_list;
	}

	@FXML
	void acceptHeight(ActionEvent event) {
		boolean correctData=true;
		String height = HeihtPatientTextField.getText();

		try {
            float heightNUM= Float.parseFloat(height);
            if(heightNUM<0 ||heightNUM>3) {
            	 JOptionPane.showMessageDialog(null, "Wrong height.");
                 correctData=false;
            }

    	}catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Wrong height.");
            correctData=false;

    	}
		if (correctData==true) {
			
		
		
		oldHeight.setText(height);
		pmodif.setHeight(Float.parseFloat(height));
		HeihtPatientTextField.setText("");
		Main.getInter().modifyPatient(this.pmodif.getId(), "height", height);

	}
	}
	 private List <Integer> ConvertAscii(String StringtoConvert){
		   	List <Integer> ascciNumsName= new ArrayList<>();

		   try {
	 for (int i =0;i<StringtoConvert.length();i++) {
		 char chartoconvert= StringtoConvert.charAt(i);
		 int asciinum=(int)chartoconvert;
		 ascciNumsName.add(asciinum);
	 }
	 }
		   catch (Exception e) {
	           JOptionPane.showMessageDialog(null, "Wrong data, put it again.");

		   }
		   
		   
		return ascciNumsName;
		   
	   }
	@FXML
	void acceptName(ActionEvent event) {
		boolean correctData=true;
		String name = NamePatientTextField.getText();
if (!name.equals("")) {
	List <Integer> ascciNumsName= ConvertAscii(name);
    	
    	for (int i =0; i<ascciNumsName.size();i++) {
    		if (!(ascciNumsName.get(i)>=65&&ascciNumsName.get(i)<=122)) {
    			correctData=false;

    		}
    	
    	}
    	if (correctData==false) {
            JOptionPane.showMessageDialog(null, "Wrong name. ");

    	}else {
		
		
		oldName.setText(name);
		pmodif.setName(name);
		NamePatientTextField.setText("");
		Main.getInter().modifyPatient(this.pmodif.getId(), "name", name);

	}
}else {
    JOptionPane.showMessageDialog(null, "Empty field");

}
	}

	@FXML
	void acceptSSNUM(ActionEvent event) {
		String ssnum = SSNumPatientTextField.getText();
		if(!ssnum.equals("")) {

		oldSSNUM.setText(ssnum);
		pmodif.setSocial_security(ssnum);
		SSNumPatientTextField.setText("");
		Main.getInter().modifyPatient(this.pmodif.getId(), "social_security", ssnum);
		}else {
    	    JOptionPane.showMessageDialog(null, "Empty field");

		}
	}

	@FXML
	void acceptSex(ActionEvent event) {
		String sextext = SexPatientTextField.getText();
		boolean correctData=true;
		try {
			db.pojos.Sex sex= Sex.valueOf(sextext);
        	

    	}catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Wrong sex. Please, put: Male or Female");
            correctData=false;

    	}
		if (correctData==true) {

		oldSex.setText(sextext);
		pmodif.setSex(Sex.valueOf(sextext));
		SexPatientTextField.setText("");
		Main.getInter().modifyPatient(this.pmodif.getId(), "sex", sextext);
		}
	}

	@FXML
	void acceptVaccinated(ActionEvent event) {
		String vaccinated = VaccinatedTEXTFIELD.getText();
		if (!vaccinated.equals("")) {
		boolean correctData=true;

		if (!(vaccinated.equals("True") || vaccinated.equals("False"))) {
			JOptionPane.showMessageDialog(null, "Wrong vaccinated, please put: True or False.");
			correctData = false;
    	}

    	
		if (correctData==true) {
		oldVaccinated.setText(vaccinated);
		pmodif.setVaccinated(Boolean.parseBoolean(vaccinated));
		VaccinatedTEXTFIELD.setText("");
		Main.getInter().modifyPatient(this.pmodif.getId(), "vaccinated", vaccinated);
		}
		else {
    	    JOptionPane.showMessageDialog(null, "Empty field");

		}
		}
	}
	  @FXML
	    void acceptInfected(ActionEvent event) {
			String infected = InfectedTEXTFIELD1.getText();
if (infected.equals("True")||infected.equals("False"))	{		
			oldInfected.setText(infected);
			pmodif.setInfected(Boolean.parseBoolean(infected));
			InfectedTEXTFIELD1.setText("");
			Main.getInter().modifyPatient(this.pmodif.getId(), "infected", infected);
	    
	  }
else {
    JOptionPane.showMessageDialog(null, "Wrong infected, please put: True or False.");

}
	  }
	

	public Label getOldInfected() {
		return oldInfected;
	}

	public void setOldInfected(String oldInfected) {
		this.oldInfected.setText(oldInfected);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}