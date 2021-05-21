package db.GUI;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import db.pojos.Administration;
import db.pojos.Shipment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TotalNumberVaccinesController implements Initializable {
    private ObservableList<Shipment> ShipmentList1;
    @FXML
    private TableView<Shipment> TableShipments1;
    Administration admin;

    public Administration getAdmin() {
		return admin;
	}

	public void setAdmin(Administration admin) {
		this.admin = admin;
	}

	@FXML
    private TableColumn colNumberVaccines1;

    @FXML
    private TableColumn DateShipment1;
    @FXML
    private TableColumn colLabName1;

    @FXML
    private Label totalNumberVaccines;

    @FXML
    void BackFromTotalVaccines(ActionEvent event) {
    	Stage stage = (Stage) this.totalNumberVaccines.getScene().getWindow();
    	stage.close();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	 @FXML
	    void seeinfo(ActionEvent event) {
		 totalNumberVaccines.setText("Total number of available vaccines: "+ Main.getInter().getNumberVaccinesAdmin(admin.getId()));
			//rellenar la tabla

	    	ShipmentList1 = FXCollections.observableArrayList();
	        this.colNumberVaccines1.setCellValueFactory(new PropertyValueFactory("Vaccines"));
	        this.DateShipment1.setCellValueFactory(new PropertyValueFactory("date_ship"));
	        this.colLabName1.setCellValueFactory(new PropertyValueFactory("LabName"));
	    	List <Shipment> allships= Main.getInter().getAllShipmentforAdminView(admin.getId());
	    	ShipmentList1.addAll(allships);
	    	this.TableShipments1.setItems(ShipmentList1);
	    	this.TableShipments1.setDisable(false);
	    }

}
