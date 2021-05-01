package db.GUI;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StatisticsController implements Initializable {
	ObservableList list = FXCollections.observableArrayList();

	@FXML
	private Button backfromstatistics;

	@FXML
	private ChoiceBox<String> typeofpatient;

	@FXML
	private Label plotTittle;

	@FXML
	private CheckBox checkAge;

	@FXML
	private CheckBox checkWeight;

	@FXML
	private CheckBox ckeckOtherPathologies;

	@FXML
	private CheckBox checkMedication;

	@FXML
	private CheckBox checkPatientLocation;

	@FXML
	private CheckBox checkHeight;

	@FXML
	private CheckBox checkSex;

	@FXML
	private CheckBox checkHospital;

	@FXML
	private Button pdfbutton;
ObservableList <PieChart.Data> pieChartData;
	@FXML
	private PieChart pieChart;

	@FXML
	private LineChart<?, ?> lineChart;

	XYChart.Series series;

	@FXML
	private CategoryAxis daysAxis;
	@FXML
    private CheckBox ckeckBloodType;
	@FXML
	private NumberAxis numAxis;

	@FXML
	void backfromstatistics(ActionEvent event) {
		Stage stage = (Stage) this.pdfbutton.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		plotTittle.setText("");

		loadData();
		series = new XYChart.Series();
		typeofpatient.setValue("Select an option");
		typeofpatient.getSelectionModel().selectedIndexProperty()
				.addListener((ObservableValue<? extends Number> ov, Number oldVal, Number newVal) -> {
					series.getData().clear();
					lineChart.getData().clear();
					series = new XYChart.Series();
					lineChart.setTitle("");

					System.out.println(typeofpatient.getValue());
					if (typeofpatient.getValue().equals("Alive") == true) {
						plotTittle.setText("Dead people per day");

						List<Integer> listDeaths = Main.getInter().getNumberofDeadsofEachDay();
						for (int i = 0; i < listDeaths.size(); i++) {
							series.getData().add(new XYChart.Data("" + i, listDeaths.get(i)));

						}

						lineChart.getData().addAll(series);

					} else if (typeofpatient.getValue().toString().equals("Dead") == true) {
						plotTittle.setText("Infected people per day");

						List<Integer> listInfecteds = Main.getInter().getNumberofInfectedsofEachDay();
						for (int i = 0; i < listInfecteds.size(); i++) {
							series.getData().add(new XYChart.Data("" + i, listInfecteds.get(i)));

						}

						lineChart.getData().addAll(series);

					}

				});

	}

    @FXML
    void CheckAge(ActionEvent event) {
    	
        if(this.checkWeight.isSelected()) {
        	
    		
    	}if(this.checkHeight.isSelected()) {
    		
    	}if(this.checkSex.isSelected()) {
    		
    	}if(this.checkHospital.isSelected()) {
    		
    	}if(this.checkPatientLocation.isSelected()) {
    		
    	}if(this.checkMedication.isSelected()) {
    		
    	}if(this.ckeckOtherPathologies.isSelected()) {
    		
    	}if(this.ckeckBloodType.isSelected()) {
    		
    	}

    }

   
    @FXML
    void checkHei(ActionEvent event) {
    	 if(this.checkWeight.isSelected()) {
     		
     	}if(this.checkAge.isSelected()) {
     		
     	}if(this.checkSex.isSelected()) {
     		
     	}if(this.checkHospital.isSelected()) {
     		
     	}if(this.checkPatientLocation.isSelected()) {
     		
     	}if(this.checkMedication.isSelected()) {
     		
     	}if(this.ckeckOtherPathologies.isSelected()) {
     		
     	}if(this.ckeckBloodType.isSelected()) {
     		
     	}
    }

    @FXML
    void checkWei(ActionEvent event) {
    	 if(this.checkAge.isSelected()) {
     		
     	}if(this.checkHeight.isSelected()) {
     		
     	}if(this.checkSex.isSelected()) {
     		
     	}if(this.checkHospital.isSelected()) {
     		
     	}if(this.checkPatientLocation.isSelected()) {
     		
     	}if(this.checkMedication.isSelected()) {
     		
     	}if(this.ckeckOtherPathologies.isSelected()) {
     		
     	}if(this.ckeckBloodType.isSelected()) {
     		
     	}
    }

    @FXML
    void onCheckHos(ActionEvent event) {
    	 if(this.checkWeight.isSelected()) {
     		
     	}if(this.checkHeight.isSelected()) {
     		
     	}if(this.checkSex.isSelected()) {
     		
     	}if(this.checkAge.isSelected()) {
     		
     	}if(this.checkPatientLocation.isSelected()) {
     		
     	}if(this.checkMedication.isSelected()) {
     		
     	}if(this.ckeckOtherPathologies.isSelected()) {
     		
     	}if(this.ckeckBloodType.isSelected()) {
     		
     	}
    }

    @FXML
    void onCheckLocation(ActionEvent event) {
    	 if(this.checkWeight.isSelected()) {
     		
     	}if(this.checkHeight.isSelected()) {
     		
     	}if(this.checkSex.isSelected()) {
     		
     	}if(this.checkHospital.isSelected()) {
     		
     	}if(this.checkAge.isSelected()) {
     		
     	}if(this.checkMedication.isSelected()) {
     		
     	}if(this.ckeckOtherPathologies.isSelected()) {
     		
     	}if(this.ckeckBloodType.isSelected()) {
     		
     	}
    }

    @FXML
    void onCheckMedication(ActionEvent event) {
    	 if(this.checkWeight.isSelected()) {
     		
     	}if(this.checkHeight.isSelected()) {
     		
     	}if(this.checkSex.isSelected()) {
     		
     	}if(this.checkHospital.isSelected()) {
     		
     	}if(this.checkPatientLocation.isSelected()) {
     		
     	}if(this.checkAge.isSelected()) {
     		
     	}if(this.ckeckOtherPathologies.isSelected()) {
     		
     	}if(this.ckeckBloodType.isSelected()) {
     		
     	}
    }

    @FXML
    void oncheckSex(ActionEvent event) {
    	lineChart.setVisible(false);
    	
    	pieChartData =  FXCollections.observableArrayList(new PieChart.Data("Male", Main.getInter().searchPatientGenericCOUNT("sex", "Male")),new PieChart.Data("sex", Main.getInter().searchPatientGenericCOUNT("sex","Female")));
    	
    	 if(this.checkWeight.isSelected()) {
     		
     	}if(this.checkHeight.isSelected()) {
     		
     	}if(this.checkAge.isSelected()) {
     		
     	}if(this.checkHospital.isSelected()) {
     		
     	}if(this.checkPatientLocation.isSelected()) {
     		
     	}if(this.checkMedication.isSelected()) {
     		
     	}if(this.ckeckOtherPathologies.isSelected()) {
     		
     	}if(this.ckeckBloodType.isSelected()) {
     		
     	}
     	pieChart.setData(pieChartData);
     	
    }

    @FXML
    void onckeckBloodType(ActionEvent event) {
    	 if(this.checkWeight.isSelected()) {
     		
     	}if(this.checkHeight.isSelected()) {
     		
     	}if(this.checkSex.isSelected()) {
     		
     	}if(this.checkHospital.isSelected()) {
     		
     	}if(this.checkPatientLocation.isSelected()) {
     		
     	}if(this.checkMedication.isSelected()) {
     		
     	}if(this.ckeckOtherPathologies.isSelected()) {
     		
     	}if(this.checkAge.isSelected()) {
     		
     	}
    }

    @FXML
    void onckeckOtherPathologies(ActionEvent event) {
    	 if(this.checkWeight.isSelected()) {
     		
     	}if(this.checkHeight.isSelected()) {
     		
     	}if(this.checkSex.isSelected()) {
     		
     	}if(this.checkHospital.isSelected()) {
     		
     	}if(this.checkPatientLocation.isSelected()) {
     		
     	}if(this.checkMedication.isSelected()) {
     		
     	}if(this.checkAge.isSelected()) {
     		
     	}if(this.ckeckBloodType.isSelected()) {
     		
     	}
    }

	private void loadData() {
		list.removeAll(list);
		String a = "Dead";
		String b = "Alive";

		list.addAll(a, b);
		typeofpatient.getItems().addAll(list);

	}

}
