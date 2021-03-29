package db.GUI;

import java.sql.Date;

import db.pojos.Lab;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewVaccinesController {
Lab lnewvaccines=null;
    public void setLnewvaccines(Lab lnewvaccines) {
	this.lnewvaccines = lnewvaccines;
}

	@FXML
    private Label totalNumberVaccines;

    @FXML
    private Label labnamenewvaccines;

    public void setLabnamenewvaccines(String labnamenewvaccines) {
		this.labnamenewvaccines.setText(labnamenewvaccines);
	}

	@FXML
    private TextField textamountnew;

    @FXML
    private Label labname1;

   

	@FXML
    void BackFromNewVaccines(ActionEvent event) {
    	Stage stage = (Stage) this.labname1.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void addvaccines(ActionEvent event) {
    	Date d_today=null; //sacar que dia es hoy
        int amountNewVaccines= Integer.parseInt(textamountnew.getText());
        lnewvaccines.setVaccines_produce(lnewvaccines.getVaccines_produce()+amountNewVaccines);
        //actualizar la tabla
        
        
        
        

    }

}
