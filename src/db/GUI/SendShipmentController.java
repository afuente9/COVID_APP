package db.GUI;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import db.pojos.Lab;
import db.pojos.Patient;
import db.pojos.Shipment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SendShipmentController implements Initializable {
   
    private ObservableList<Shipment> ShipmentList;

	Lab lsendvaccines=null;
	


    @FXML
    private Label totalNumberVaccines;

    @FXML
    private Label labname;

    @FXML
    private TableView<Shipment> TableShipments;

    @FXML
    private TableColumn colNumberVaccines;

    @FXML
    private TableColumn DateShipment;

    @FXML
    private TextField textamountvaccinessend;
 
	public void setLabname(String labname) {
		this.labname.setText(labname); 
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	    	ShipmentList = FXCollections.observableArrayList();
	        this.colNumberVaccines.setCellValueFactory(new PropertyValueFactory("Vaccines"));
	        this.DateShipment.setCellValueFactory(new PropertyValueFactory("date_ship"));
	    	List <Shipment> allships= Main.getInter().getAllShipment();
	    	ShipmentList.addAll(allships);
	    	this.TableShipments.setItems(ShipmentList);
	    	
	    	
	}
	
	@FXML
    void BackFromSendVaccines(ActionEvent event) {
    	Stage stage = (Stage) this.textamountvaccinessend.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void sendvaccinesbutton(ActionEvent event) {
    	this.ShipmentList.clear();

    	Shipment snew= new Shipment(Integer.parseInt(textamountvaccinessend.getText()),Date.valueOf(LocalDate.now()),lsendvaccines.getName());

    	Main.getInter().addShipment(snew, Main.getInter().getLab(lsendvaccines.getId()), Main.getInter().getAdministration());
        Main.getInter().ModifyVaccinesFromLab(Integer.parseInt("-"+textamountvaccinessend.getText()), lsendvaccines.getId());

        Main.getInter().ModifyVaccinesAdmin(Integer.parseInt(textamountvaccinessend.getText()));
        List<Shipment> result = Main.getInter().getAllShipment();

		this.ShipmentList.addAll(result);
		
    	this.TableShipments.setItems(ShipmentList);  
    	
    	textamountvaccinessend.setText("");
    	
    

    }
    public void setLsendvaccines(Lab lsendvaccines) {
		this.lsendvaccines = lsendvaccines;
	}




    
    
    

}
