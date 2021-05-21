package db.GUI;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

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
	
boolean firsttime=true;

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
    private TextField countryTF;

    @FXML
    private TableColumn countrycolumn;

    @FXML
    private TextField textamountvaccinessend;
 
	public void setLabname(String labname) {
		this.labname.setText(labname); 
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	    	
	    	
	}
	
	@FXML
    void BackFromSendVaccines(ActionEvent event) {
    	Stage stage = (Stage) this.textamountvaccinessend.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void sendvaccinesbutton(ActionEvent event) {

    	if(textamountvaccinessend.getText()==""||countryTF.getText()=="") {
    		
    	    JOptionPane.showMessageDialog(null, "Empty field");

    	}
    	else {
    		boolean correctData=true;
    		if( Main.getInter().adminRegisteredByName(countryTF.getText())==false) {
    		    JOptionPane.showMessageDialog(null, "Country not registered. Please, contact to the Ministry of Health ");
    		    correctData=false;

    		}
    		try {
    		if(Main.getInter().getNumberVaccinesLab(lsendvaccines.getId())-Integer.parseInt(textamountvaccinessend.getText())<0) {
    		    JOptionPane.showMessageDialog(null, "You do not have that amount of vaccines yet ");
    		    correctData=false;

    		}
    		}catch(Exception e) {
    		    JOptionPane.showMessageDialog(null, "Wrong number ");
    		    correctData=false;

    		}
    		if (correctData==true) {
    		if(Integer.parseInt(textamountvaccinessend.getText())>0 ){
    			if (firsttime==true) {
        	    	this.TableShipments.setDisable(false);

    				ShipmentList = FXCollections.observableArrayList();
        	        this.colNumberVaccines.setCellValueFactory(new PropertyValueFactory("Vaccines"));
        	        
        	        this.DateShipment.setCellValueFactory(new PropertyValueFactory("date_ship"));
        	        this.countrycolumn.setCellValueFactory(new PropertyValueFactory("governmentID"));
        	        
        	    	List <Shipment> allships= Main.getInter().getAllShipment(lsendvaccines.getId());
        	    	ShipmentList.addAll(allships);
        	    	this.TableShipments.setItems(ShipmentList);
        	    	firsttime=false;
        	    	this.ShipmentList.clear();

        	    	Shipment snew= new Shipment(Integer.parseInt(textamountvaccinessend.getText()),Date.valueOf(LocalDate.now()),lsendvaccines.getName(),Main.getInter().searchadminIDByName(countryTF.getText()));

        	    	Main.getInter().addShipment(snew, Main.getInter().getLab(lsendvaccines.getId()), Main.getInter().getAdministration(Main.getInter().searchadminIDByName(countryTF.getText())));
        	        Main.getInter().ModifyVaccinesFromLab(Integer.parseInt("-"+textamountvaccinessend.getText()), lsendvaccines.getId());

        	        Main.getInter().ModifyVaccinesAdmin(Integer.parseInt(textamountvaccinessend.getText()),Main.getInter().searchadminIDByName(countryTF.getText()));
        	        List<Shipment> result = Main.getInter().getAllShipment(lsendvaccines.getId());

        			this.ShipmentList.addAll(result);
        			
        	    	this.TableShipments.setItems(ShipmentList);  
        	    	
        	    	textamountvaccinessend.setText("");
        	    	countryTF.setText("");

        	
    			}else {
        	    	this.ShipmentList.clear();
        	    	this.TableShipments.setDisable(false);
        	    	
        	    	Shipment snew= new Shipment(Integer.parseInt(textamountvaccinessend.getText()),Date.valueOf(LocalDate.now()),lsendvaccines.getName(),Main.getInter().searchadminIDByName(countryTF.getText()));

        	    	Main.getInter().addShipment(snew, Main.getInter().getLab(lsendvaccines.getId()), Main.getInter().getAdministration(Main.getInter().searchadminIDByName(countryTF.getText())));
        	        Main.getInter().ModifyVaccinesFromLab(Integer.parseInt("-"+textamountvaccinessend.getText()), lsendvaccines.getId());

        	        Main.getInter().ModifyVaccinesAdmin(Integer.parseInt(textamountvaccinessend.getText()),Main.getInter().searchadminIDByName(countryTF.getText()));
        	        List<Shipment> result = Main.getInter().getAllShipment(lsendvaccines.getId());

        			this.ShipmentList.addAll(result);
        			
        	    	this.TableShipments.setItems(ShipmentList);  
        	    	
        	    	textamountvaccinessend.setText("");
        	    	countryTF.setText("");
        	    	}
    		}
    			}

    		
    	}

    }
    public void setLsendvaccines(Lab lsendvaccines) {
		this.lsendvaccines = lsendvaccines;
	}




    
    
    

}
