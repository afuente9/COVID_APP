package db.GUI;

import java.net.URL;
import java.util.ResourceBundle;

import db.pojos.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class SearchPatientLABController  implements Initializable  {
	 ObservableList list = FXCollections.observableArrayList();


    @FXML
    private Label numberofpatients;

    @FXML
    private ChoiceBox<String> SearchOptionslab;

    @FXML
    private DatePicker fromlab;

    @FXML
    private DatePicker tolab;

    @FXML
    private TableView<Patient> patientstablelab;
    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colAge;

    @FXML
    private TableColumn colSex;

    @FXML
    private TableColumn colHeight;

    @FXML
    private TableColumn colWeight;

    @FXML
    private TableColumn colSSnum;

    @FXML
    private TableColumn colHos;

    @FXML
    private TableColumn colInfected;

    @FXML
    private TableColumn colDateIntroduced;

    @FXML
    void BackFromModifylab(ActionEvent event) {
    	Stage stage = (Stage) this.numberofpatients.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void ModifySearchlab(ActionEvent event) {

    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	loadData();
    	SearchOptionslab.setValue("Select an option");
    	numberofpatients.setText("There are "+/*lista.gettotalnumberofpatients+*/"patients");
    	
    }
    private void loadData() {
    	list.removeAll(list);
    	String a="Name";
    	String b="Age";
    	String c="Sex";
    	String cc="Height";
    	String d="Weight";
    	String e="SS num";
    	String f="Hospital";
    	String g="Infected";
    	String h="Date introduced";
    	list.addAll(a,b,c,cc,d,e,f,g,h);
    	SearchOptionslab.getItems().addAll(list);
    	

    }


}
