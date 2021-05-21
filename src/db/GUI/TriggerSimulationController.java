package db.GUI;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import db.pojos.Administration;
import db.pojos.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TriggerSimulationController implements Initializable {

	@FXML
	private TableView<Patient> tablesimulation;
	public int id;

	@FXML
	private TableColumn scoreCol;

	@FXML
	private TableColumn nameCol;

	@FXML
	private TableColumn bdCol;
	private Administration admin;
	@FXML
	private TableColumn SSnumcol;

	public TableColumn getScoreCol() {
		return scoreCol;
	}

	public TableColumn getNameCol() {
		return nameCol;
	}

	public TableColumn getBdCol() {
		return bdCol;
	}

	public Administration getAdmin() {
		return admin;
	}

	public TableColumn getSSnumcol() {
		return SSnumcol;
	}

	public TableColumn getHospitalCol() {
		return HospitalCol;
	}

	public TableColumn getDateIntroducedCol() {
		return DateIntroducedCol;
	}

	public ObservableList<Patient> getPatientsTableList() {
		return patientsTableList;
	}

	public void setScoreCol(TableColumn scoreCol) {
		this.scoreCol = scoreCol;
	}

	public void setNameCol(TableColumn nameCol) {
		this.nameCol = nameCol;
	}

	public void setBdCol(TableColumn bdCol) {
		this.bdCol = bdCol;
	}

	public void setAdmin(Administration admin) {
		this.admin = admin;
	}

	public void setSSnumcol(TableColumn sSnumcol) {
		SSnumcol = sSnumcol;
	}

	public void setHospitalCol(TableColumn hospitalCol) {
		HospitalCol = hospitalCol;
	}

	public void setDateIntroducedCol(TableColumn dateIntroducedCol) {
		DateIntroducedCol = dateIntroducedCol;
	}

	public void setPatientsTableList(ObservableList<Patient> patientsTableList) {
		this.patientsTableList = patientsTableList;
	}

	@FXML
	private TableColumn HospitalCol;

	@FXML
	private TableColumn DateIntroducedCol;
	private ObservableList<Patient> patientsTableList;

	@FXML
	void backfromsimulation(ActionEvent event) {
		Stage stage = (Stage) this.tablesimulation.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		patientsTableList = FXCollections.observableArrayList();
		this.scoreCol.setCellValueFactory(new PropertyValueFactory("score"));
		this.nameCol.setCellValueFactory(new PropertyValueFactory("name"));
		this.bdCol.setCellValueFactory(new PropertyValueFactory("birthday"));
		this.SSnumcol.setCellValueFactory(new PropertyValueFactory("social_security"));
		this.HospitalCol.setCellValueFactory(new PropertyValueFactory("hospital"));
		this.DateIntroducedCol.setCellValueFactory(new PropertyValueFactory("DateIntroduced"));
		this.tablesimulation.setItems(patientsTableList);

	}

	@FXML
	void ongerepdf(ActionEvent event) throws ClassNotFoundException, SQLException {

		List<Patient> allpatients = Main.getInter()
				.getSimulatedPatients(Main.getInter().getNumberVaccinesAdmin(this.admin.getId()), this.admin.getId());
		try {

			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager.getConnection("jdbc:sqlite:./database/covid.database");
			String sql = "SELECT p.score, p.name, p.social_security, p.hospital, a.nameCountry,	a.total_vacciness FROM patients AS p JOIN administration AS a ON a.id = p.id_adm WHERE id_adm = "+this.admin.getId()+ " ORDER BY p.score DESC";


			JasperDesign jdesign = JRXmlLoader
					.load("src\\db\\reports\\reportSimulation2.jrxml");

			JRDesignQuery updateQuery = new JRDesignQuery();
			updateQuery.setText(sql);
			jdesign.setQuery(updateQuery);

			JasperReport Jreport = JasperCompileManager.compileReport(jdesign);

			JasperPrint JasperPrint = JasperFillManager.fillReport(Jreport, null, con);
			JasperViewer.viewReport(JasperPrint, false);

			/*
			 * JasperCompileManager.compileReportToFile(
			 * "src\\db\\reports\\reportSimulation2.jrxml");
			 * 
			 * JasperPrint reportToPrint =
			 * JasperFillManager.fillReport("src\\db\\reports\\reportSimulation2.jasper",
			 * null, new JRBeanCollectionDataSource(allpatients));
			 * 
			 * JasperViewer jasperViewer = new JasperViewer (reportToPrint);
			 * jasperViewer.setVisible(true);
			 */

		} catch (JRException ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	void simulation(ActionEvent event) {
		tablesimulation.setDisable(false);
		List<Patient> allpatients = Main.getInter()
				.getSimulatedPatients(Main.getInter().getNumberVaccinesAdmin(this.admin.getId()), this.admin.getId());
		this.patientsTableList.addAll(allpatients);
		this.tablesimulation.setItems(patientsTableList);
	}

}
