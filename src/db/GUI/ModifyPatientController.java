package db.GUI;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

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
		oldHospital.setText(newHos);
		pmodif.setHospital(newHos);
		HospitalPatientTextField.setText("");
		Main.getInter().modifyPatient(this.pmodif.getId(), "hospital", newHos);
	}

	@FXML
	void OnBackModifyData(ActionEvent event) {
		Stage stage = (Stage) this.NamePatientTextField.getScene().getWindow();
		stage.close();
	}

	@FXML
	void AcceptPlace(ActionEvent event) {
		String newPlace = PlacePatientTextField.getText();
		oldPlace.setText(newPlace);
		pmodif.setHos_location(newPlace);
		PlacePatientTextField.setText("");
		Main.getInter().modifyPatient(this.pmodif.getId(), "hos_location", newPlace);

	}

	@FXML
	void AcceptWeig(ActionEvent event) {
		String newWeig = WeightPatientTextField.getText();
		oldWei.setText(newWeig);

		pmodif.setWeight(Float.parseFloat(newWeig));
		WeightPatientTextField.setText("");
		Main.getInter().modifyPatient(this.pmodif.getId(), "weight", newWeig);

	}

	@FXML
	void DeletePathbyNum(ActionEvent event) {

	}

	@FXML
	void Deletemedbynum(ActionEvent event) {

	}

	@FXML
	void OnAddMedication(ActionEvent event) {
		String medicationName = MedicationPatientTextField.getText();

		int medicationId = medication_list.size();
		Medication m_new = new Medication(medicationName);
		//TODO CONTROLAR SI LA MEDICACION YA ESTA METIDA EN EL PACIENTE
			Main.getInter().addMedication(m_new);
			Main.getInter().assignMed(Main.getInter().getLastPatient().getId(), Main.getInter().getLastMedication());
			Main.getInter().getLastPatient().setMedication(medication_list);
			MedicationPatientTextField.setText("");
			String  medications = "";
			medication_list.add(Main.getInter().getLastMedication());

			Iterator iter = medication_list.iterator();
			while (iter.hasNext()) {
				medications += iter.next() + "\n";
			}
			AllMedLabel.setText(medications);
	}

	@FXML
	void OnAddPathology(ActionEvent event) {
		String otherPathologiesName = OtherPathologiesPatientTextField.getText();
		
		Other_Pathologies op_new = new Other_Pathologies(otherPathologiesName);

			Main.getInter().addOtherPathologies(op_new);
			Main.getInter().assignPatho(Main.getInter().getLastPatient().getId(), Main.getInter().getLastPath());
			Main.getInter().getLastPatient().setOther_pathologies(other_pathologies_list);
			other_pathologies_list.add(Main.getInter().getLastPath());

			OtherPathologiesPatientTextField.setText("");
			Iterator iter = other_pathologies_list.iterator();
			String paths = "";
			while (iter.hasNext()) {
				paths += iter.next() + "\n";
			}
			pathologylabel.setText(paths);

		} 
	

	@FXML
	void OnCancelPatient(ActionEvent event) {
		
		Stage stage = (Stage) this.BirthDatePatientTextField.getScene().getWindow();
    	stage.close();
	}

	@FXML
	void acceptBD(ActionEvent event) {
		String newbdate = BirthDatePatientTextField.getText();
		oldBirthDate.setText(newbdate);
		pmodif.setBirthday(Date.valueOf(newbdate));
		BirthDatePatientTextField.setText("");
		Main.getInter().modifyPatient(this.pmodif.getId(), "birthday", newbdate);

	}

	@FXML
	void acceptBlood(ActionEvent event) {
		String newblood = BloodPatientTextField.getText();
		oldBlood.setText(newblood);

		pmodif.setBloodType(newblood);

		BloodPatientTextField.setText("");

		Main.getInter().modifyPatient(this.pmodif.getId(), "bloodType", newblood);

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

	public void setPathologylabel(Label pathologylabel) {
		this.pathologylabel = pathologylabel;
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
		String height = HeihtPatientTextField.getText();
		oldHeight.setText(height);
		pmodif.setHeight(Float.parseFloat(height));
		HeihtPatientTextField.setText("");
		Main.getInter().modifyPatient(this.pmodif.getId(), "height", height);

	}

	@FXML
	void acceptName(ActionEvent event) {
		String name = NamePatientTextField.getText();
		oldName.setText(name);
		pmodif.setName(name);
		NamePatientTextField.setText("");
		Main.getInter().modifyPatient(this.pmodif.getId(), "name", name);

	}

	@FXML
	void acceptSSNUM(ActionEvent event) {
		String ssnum = SSNumPatientTextField.getText();
		oldSSNUM.setText(ssnum);
		pmodif.setSocial_security(ssnum);
		SSNumPatientTextField.setText("");
		Main.getInter().modifyPatient(this.pmodif.getId(), "social_security", ssnum);

	}

	@FXML
	void acceptSex(ActionEvent event) {
		String sex = SexPatientTextField.getText();
		oldSex.setText(sex);
		pmodif.setSex(Sex.valueOf(sex));
		SexPatientTextField.setText("");
		Main.getInter().modifyPatient(this.pmodif.getId(), "sex", sex);

	}

	@FXML
	void acceptVaccinated(ActionEvent event) {
		String vaccinated = VaccinatedTEXTFIELD.getText();
		oldVaccinated.setText(vaccinated);
		pmodif.setVaccinated(Boolean.parseBoolean(vaccinated));
		VaccinatedTEXTFIELD.setText("");
		Main.getInter().modifyPatient(this.pmodif.getId(), "vaccinated", vaccinated);

	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}