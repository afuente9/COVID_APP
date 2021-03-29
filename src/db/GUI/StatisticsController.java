package db.GUI;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class StatisticsController implements Initializable {
	 ObservableList list = FXCollections.observableArrayList();

    @FXML
    private Button backfromstatistics;

    @FXML
    private ChoiceBox<String> typeofpatient;

    @FXML
    private Button pdfbutton;
    @FXML
    void backfromstatistics(ActionEvent event) {
    	Stage stage = (Stage) this.pdfbutton.getScene().getWindow();
    	stage.close();
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadData();
		typeofpatient.setValue("Select an option");
	}
	 private void loadData() {
	    	list.removeAll(list);
	    	String a="Dead";
	    	String b="Alive";
	    	
	    	list.addAll(a,b);
	    	typeofpatient.getItems().addAll(list);
	    	

	    }

}
