package db.GUI;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import db.pojos.Lab;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewVaccinesController  {
Lab lnewvaccines=null;
    public void setLnewvaccines(Lab lnewvaccines) {
	this.lnewvaccines = lnewvaccines;
}

	public Lab getLnewvaccines() {
		return lnewvaccines;
	}

	public Label getTotalNumberVaccines() {
		return totalNumberVaccines;
	}

	public Label getLabnamenewvaccines() {
		return labnamenewvaccines;
	}

	public Label getTotalNumberVaccines1() {
		return totalNumberVaccines1;
	}

	public TextField getTextamountnew() {
		return textamountnew;
	}

	public Label getLabname1() {
		return labname1;
	}

	public void setTotalNumberVaccines(String totalNumberVaccines) {
		this.totalNumberVaccines.setText(totalNumberVaccines);
	}

	public void setLabnamenewvaccines(Label labnamenewvaccines) {
		this.labnamenewvaccines = labnamenewvaccines;
	}

	public void setTotalNumberVaccines1(String totalNumberVaccines1) {
		this.totalNumberVaccines1.setText(totalNumberVaccines1);
	}

	public void setTextamountnew(TextField textamountnew) {
		this.textamountnew = textamountnew;
	}

	public void setLabname1(Label labname1) {
		this.labname1 = labname1;
	}

	@FXML
    private Label totalNumberVaccines;

    @FXML
    private Label labnamenewvaccines;

    @FXML
    private Label totalNumberVaccines1;

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
    	 int amountNewVaccines=0;
    	 boolean correctData= true;
    	try {
             amountNewVaccines= Integer.parseInt(textamountnew.getText());
            
            

    	}
    	catch(Exception e) {
    	    JOptionPane.showMessageDialog(null, "Wrong number");

    		
    	}
        if(amountNewVaccines>0&&correctData==true) {
        Main.getInter().ModifyVaccinesFromLab(amountNewVaccines,lnewvaccines.getId() );
        textamountnew.setText("");
		totalNumberVaccines1.setText("You have "+ Main.getInter().getNumberVaccinesLab(lnewvaccines.getId())+ " vaccines");

    } else {
	    JOptionPane.showMessageDialog(null, "Wrong number");

    }
    }


}
