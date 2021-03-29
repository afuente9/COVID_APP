package db.GUI;

import java.sql.Date;

import db.pojos.Lab;
import db.pojos.Shipment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SendShipmentController {
   

	Lab lsendvaccines=null;
	public void setLsendvaccines(Lab lsendvaccines) {
		this.lsendvaccines = lsendvaccines;
	}

	@FXML
    private Label totalNumberVaccines;

    @FXML
    private TextField textamountvaccinessend;
    @FXML
    private Label labname;
 
	public void setLabname(String labname) {
		this.labname.setText(labname); 
	}

	@FXML
    void BackFromSendVaccines(ActionEvent event) {
    	Stage stage = (Stage) this.textamountvaccinessend.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void sendvaccinesbutton(ActionEvent event) {
    	Date d_today=null; //sacar que dia es hoy
    	int shipID=0;
    	//shipID=cuantos id hay
    			
    	Shipment snew= new Shipment(shipID,Integer.parseInt(textamountvaccinessend.getText()),d_today);
    	lsendvaccines.setVaccines_produce(lsendvaccines.getVaccines_produce()-Integer.parseInt(textamountvaccinessend.getText()));
    	//addshipment
    	//actualizar la tabla
    	//sumar vacunas a administracion

    }

}
