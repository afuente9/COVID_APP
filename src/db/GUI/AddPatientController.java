package db.GUI;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import db.pojos.Doctor;
import db.pojos.Lab;
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
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddPatientController {
    @FXML
    private Label country;
    @FXML
    private Label otherpatlabel; 
    @FXML
    private Label sexlab;

    @FXML
    private Label medicationslabel;

    
    
    
    @FXML
    private Label place;

    @FXML
    private Label hos;

    @FXML
    private Label bloodg;

    @FXML
    private Label wei;

    @FXML
    private Label hei;

    @FXML
    private Label ss;

    @FXML
    private Label bd;

    @FXML
    private Label countrylbl;

    @FXML
    private Label namelab;
	@FXML
	private TextField NamePatientTextField;

	@FXML
	private TextField SexPatientTextField;
	@FXML
	private Button buttonConfirm;
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
	private List<Medication> medication_list = new ArrayList();
	private List<Other_Pathologies> other_pathologies_list = new ArrayList();
	@FXML
	private DialogPane AllMedDialog;

	private List<Integer> ConvertAscii(String StringtoConvert) {
		List<Integer> ascciNumsName = new ArrayList<>();

		try {
			for (int i = 0; i < StringtoConvert.length(); i++) {
				char chartoconvert = StringtoConvert.charAt(i);
				int asciinum = (int) chartoconvert;
				ascciNumsName.add(asciinum);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Wrong data, put it again.");

		}

		return ascciNumsName;

	}

	@FXML
	void onConfirmWithoutMedPat(ActionEvent event) {
		if (NamePatientTextField.getText() == "" || SexPatientTextField.getText() == ""
				|| BirthDatePatientTextField.getText() == "" || SSNumPatientTextField.getText() == ""
				|| HeihtPatientTextField.getText() == "" || WeightPatientTextField.getText() == ""
				|| BloodPatientTextField.getText() == "" || HospitalPatientTextField.getText() == ""
				|| PlacePatientTextField.getText() == "" || VaccinatedTEXTFIELD.getText() == "") {
			JOptionPane.showMessageDialog(null, "There are empty fields.");

		} else {

			String Name_Text = NamePatientTextField.getText();
			String Sex_Text = SexPatientTextField.getText();
			String BirthDate_Text = BirthDatePatientTextField.getText();
			String SSNum_Text = SSNumPatientTextField.getText();
			String Height_Text = HeihtPatientTextField.getText();
			String Weight_Text = WeightPatientTextField.getText();
			String Blood_Text = BloodPatientTextField.getText();
			String Hospital_Text = HospitalPatientTextField.getText();
			String Place_Text = PlacePatientTextField.getText();
			String VaccinatedText = VaccinatedTEXTFIELD.getText();
			String country = countrytext.getText();
			boolean correctData = true;

			List<Integer> ascciNumsName = ConvertAscii(Name_Text);

			for (int i = 0; i < ascciNumsName.size(); i++) {
				if (!(ascciNumsName.get(i) >= 65 && ascciNumsName.get(i) <= 122)) {
					correctData = false;

				}

			}
			if (correctData == false) {
				JOptionPane.showMessageDialog(null, "Wrong name. ");

			}

			try {
				db.pojos.Sex sex = Sex.valueOf(Sex_Text);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Wrong sex. Please, put: Male or Female");
				correctData = false;

			}
			try {
				Date date = Date.valueOf(BirthDate_Text);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Wrong date. Please, use the format: yyyy-mm-dd");
				correctData = false;

			}
			try {
				float height = Float.parseFloat(Height_Text);
				if (height < 0 || height > 3) {
					JOptionPane.showMessageDialog(null, "Wrong height.");
					correctData = false;
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Wrong height.");
				correctData = false;

			}
			try {
				float weight = Float.parseFloat(Weight_Text);
				if (weight < 0 || weight > 500) {
					JOptionPane.showMessageDialog(null, "Wrong weight.");
					correctData = false;
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Wrong weight.");
				correctData = false;

			}
			if (!(Blood_Text.equals("AB+") || Blood_Text.equals("AB-") || Blood_Text.equals("A+")
					|| Blood_Text.equals("A-") || Blood_Text.equals("B+") || Blood_Text.equals("B-")
					|| Blood_Text.equals("0+") || Blood_Text.equals("0-"))) {
				JOptionPane.showMessageDialog(null, "Wrong blood type.");
				correctData = false;

			}
			if (!(Place_Text.equals("ICU") || Place_Text.equals("Floor") || Place_Text.equals("Home"))) {
				JOptionPane.showMessageDialog(null, "Wrong place. Please, put: ICU, Floor, Home");
				correctData = false;

			}

			if (!(VaccinatedText.equals("True") || VaccinatedText.equals("False"))) {

				JOptionPane.showMessageDialog(null, "Wrong vaccinated, please put: True or False.");
				correctData = false;

			} else if (Main.getInter().adminRegisteredByName(country) == false) {
				JOptionPane.showMessageDialog(null,
						"Country notregistered. Please, contact to the Ministry of Health ");
				correctData = false;

			}

			if (correctData == true) {

				db.pojos.Sex sex = Sex.valueOf(Sex_Text);
				Date date = Date.valueOf(BirthDate_Text);
				float height = Float.parseFloat(Height_Text);
				float weight = Float.parseFloat(Weight_Text);
				boolean infected = true;
				boolean alive = true;
				int score = 0;
				boolean vaccinated = Boolean.parseBoolean(VaccinatedText);
				Date dateIntroduced = Date.valueOf(LocalDate.now());
				int countryId = Main.getInter().searchadminIDByName(countrytext.getText());

				p_new = new Patient(Place_Text, Name_Text, date, SSNum_Text, height, weight, sex, infected, alive,
						Hospital_Text, vaccinated, Blood_Text, dateIntroduced, countryId);
				Main.getInter().addPatient(p_new);
				Patient lastpatient= Main.getInter().getLastPatient();
				
				List<Doctor> alldoctors = Main.getInter().getAllDoctors();
				List<Lab> alllabs= Main.getInter().showLabs();
				System.out.println("df"+alldoctors.size());
				for (int i=0;i<alldoctors.size();i++) {
					
					Main.getInter().assignPattoDoc(lastpatient.getId(), alldoctors.get(i));
				
					
					}
				for (int i=0;i<alllabs.size();i++) {
						
						Main.getInter().assignPattoLab((int)lastpatient.getId(),alllabs.get(i));
					
						
						}
				

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
				buttonConfirm.setDisable(true);
				place.setDisable(true);
				hos.setDisable(true);
				bloodg.setDisable(true);
				wei.setDisable(true);
				hei.setDisable(true);
				ss.setDisable(true);
				bd.setDisable(true);
				sexlab.setDisable(true);
				namelab.setDisable(true);
				countrylbl.setDisable(true);
				
				otherpatlabel.setDisable(false);
				medicationslabel.setDisable(false);
				
				
				
				OtherPathologiesPatientTextField.setDisable(false);
				MedicationPatientTextField.setDisable(false);
				countrytext.setDisable(true);
				addPat.setDisable(false);
				addMed.setDisable(false);
				SeeAllMedication1.setDisable(false);
				SeeAllMedication.setDisable(false);
			}

		}
	}

	@FXML
	void OnAddMedication(ActionEvent event) {
		String medicationName = MedicationPatientTextField.getText();
		if (!medicationName.equals("")) {
			Medication m_new = new Medication(medicationName);

			List<Medication> medicationsPatient = Main.getInter()
					.getMedicationfromPatientwithoutID(Main.getInter().getLastPatient().getId());
			if (medicationsPatient.contains(m_new)) {

				JOptionPane.showMessageDialog(null, "Medication already added to this patient");

			} else {

				if (Main.getInter().MedicationRegisteredByName(medicationName) == false) {
					m_new = new Medication(medicationName);

					Main.getInter().addMedication(m_new);
					Main.getInter().assignMed(Main.getInter().getLastPatient().getId(),
							Main.getInter().getLastMedication());
					Main.getInter().getLastPatient().setMedication(medication_list);
					MedicationPatientTextField.setText("");
					medication_list.add(Main.getInter().getLastMedication());
					medicationsPatient.add(m_new);
					Iterator iter = medication_list.iterator();
					String medications = "";
					while (iter.hasNext()) {
						medications += iter.next() + "\n";
					}
					AllMedLabel.setText(medications);

				} else {
					m_new = new Medication(medicationName);
					Main.getInter().assignMed(Main.getInter().getLastPatient().getId(),
							Main.getInter().getMedication(medicationName));
					Main.getInter().getLastPatient().setMedication(medication_list);
					MedicationPatientTextField.setText("");
					medication_list.add(Main.getInter().getMedication(medicationName));
					medicationsPatient.add(m_new);

					Iterator iter = medication_list.iterator();
					String medications = "";
					while (iter.hasNext()) {
						medications += iter.next() + "\n";
					}
					AllMedLabel.setText(medications);

				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Empty field");

		}
	}

	@FXML
	void DeletePathbyNum(ActionEvent event) {
		boolean isnumber = true;
		try {
			int num = Integer.parseInt(DeletePathologynum.getText());
			if (num < 0) {
				JOptionPane.showMessageDialog(null, "Wrong number.");
				isnumber = false;
			}

		} catch (Exception e) {
			isnumber = false;
			JOptionPane.showMessageDialog(null, "Please put a number to delete a pathology");

		}

		if (isnumber == true) {

			Main.getInter().deleteAssignmentPathology(Main.getInter().getLastPatient().getId(),
					Integer.parseInt(DeletePathologynum.getText()));

			other_pathologies_list
					.remove(Main.getInter().getPathologyId(Integer.parseInt(DeletePathologynum.getText())));

			DeletePathologynum.setText("");
			Iterator iter = other_pathologies_list.iterator();
			String paths = "";
			while (iter.hasNext()) {
				paths += iter.next() + "\n";
			}
			pathologylabel.setText(paths);
		}
	}

	@FXML
	void Deletemedbynum(ActionEvent event) {
		boolean isnumber = true;
		try {
			int num = Integer.parseInt(DeleteMedNum.getText());
			if (num < 0) {
				isnumber = false;
				JOptionPane.showMessageDialog(null, "Wrong number");

			}
		} catch (Exception e) {
			isnumber = false;
			JOptionPane.showMessageDialog(null, "Please put a number to delete a medication");

		}

		if (isnumber == true) {
			Main.getInter().deleteAssignmentMedication(Main.getInter().getLastPatient().getId(),
					Integer.parseInt(DeleteMedNum.getText()));

			medication_list.remove(Main.getInter().getMedicationId(Integer.parseInt(DeleteMedNum.getText())));
			DeleteMedNum.setText("");
			Iterator iter = medication_list.iterator();
			String medications = "";
			while (iter.hasNext()) {
				medications += iter.next() + "\n";
			}
			AllMedLabel.setText(medications);

		}
	}

	@FXML
	void OnAddPathology(ActionEvent event) {
		String otherPathologiesName = OtherPathologiesPatientTextField.getText();
		if (!otherPathologiesName.equals("")) {
			Other_Pathologies op_new = new Other_Pathologies(otherPathologiesName);
			List<Other_Pathologies> pathsPatient = Main.getInter()
					.getPatfromPatientwithoutID(Main.getInter().getLastPatient().getId());

			if (pathsPatient.contains(op_new)) {
				JOptionPane.showMessageDialog(null, "Pathology already added to this patient");

			}

			else {
				if (Main.getInter().PathologyRegisteredByName(otherPathologiesName) == false) {
					Main.getInter().addOtherPathologies(op_new);
					Main.getInter().assignPatho(Main.getInter().getLastPatient().getId(),
							Main.getInter().getLastPath());
					Main.getInter().getLastPatient().setOther_pathologies(other_pathologies_list);

					other_pathologies_list.add(Main.getInter().getLastPath());
					OtherPathologiesPatientTextField.setText("");
					Iterator iter = other_pathologies_list.iterator();
					String paths = "";

					while (iter.hasNext()) {
						paths += iter.next() + "\n";
					}
					pathologylabel.setText(paths);

				} else {
					Main.getInter().assignPatho(Main.getInter().getLastPatient().getId(),
							Main.getInter().getPatByName(otherPathologiesName));
					Main.getInter().getLastPatient().setOther_pathologies(other_pathologies_list);
					other_pathologies_list.add(Main.getInter().getPatByName(otherPathologiesName));
					OtherPathologiesPatientTextField.setText("");
					Iterator iter = other_pathologies_list.iterator();
					String paths = "";

					while (iter.hasNext()) {
						paths += iter.next() + "\n";
					}
					pathologylabel.setText(paths);

				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Empty field");

		}

	}

	@FXML
	void SeeAllMed(ActionEvent event) {
		String name = "SeeAllMedView.fxml";
		SeeAllMedController controller = null;
		openWindow(name, controller);

	}

	@FXML
	void SeeAllPath(ActionEvent event) {
		String name = "SeeAllPathView.fxml";
		SeeAllPathController controller = null;
		openWindow(name, controller);

	}

	@FXML
	void OnCancelPatient(ActionEvent event) {
		Stage stage = (Stage) this.PlacePatientTextField.getScene().getWindow();
		stage.close();
	}

	void openWindow(String name, Object controller) {
		try {
			Pane root0 = (Pane) this.addMed.getScene().getRoot();

			 ColorAdjust adj = new ColorAdjust(0, -0.9, -0.5, 0);

			 GaussianBlur blur = new GaussianBlur(10); 
			    adj.setInput(blur);
			 root0.setEffect(adj);
			FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
			Parent root;

			root = loader.load();

			controller = loader.getController();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setResizable(false);

			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.setTitle("All pathologies");
			stage.showAndWait();
			root0.setEffect(null);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}