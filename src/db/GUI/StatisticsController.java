package db.GUI;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
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
    	lineChart.setVisible(false);

    	List<Date> dates = Main.getInter().getDates();
    	List<Integer> ages = new ArrayList<>();
		 LocalDate today = LocalDate.now();   
		 for(int i =0;i<dates.size();i++) {
			 System.out.println(i);

			 LocalDate fHoy= LocalDate.now();
		     LocalDate cumple= dates.get(i).toLocalDate();
		     long age= ChronoUnit.YEARS.between(cumple, fHoy); 
			 
	    		
	    		ages.add((int)age);
	    	}
		 int timer1=0;
		 int timer2=0;
		 int timer3=0;
		 int timer4=0;
		 int timer5=0;

		 for(int i =0;i<ages.size();i++) {
			 
			 if (ages.get(i)>0 && ages.get(i)<20) {
				 timer1++;
			 } 
			 if (ages.get(i)>20  && ages.get(i)<40) {
				 timer2++;
			 } 
			 if (ages.get(i)>40 && ages.get(i)<60) {
				 timer3++;
			 }
			 if (ages.get(i)>60 && ages.get(i)<80) {
				 timer4++;
			 } 
			 if (ages.get(i)>80) {
				 timer5++;
			 }
			 
	    		
	    	}
    
		 pieChartData = FXCollections.observableArrayList( FXCollections.observableArrayList(new PieChart.Data("<20 ", timer1),
	    			new PieChart.Data("20 - 40", timer2),
	    			new PieChart.Data("40- 60", timer3),
	    			new PieChart.Data("60 - 80", timer4),
	    			new PieChart.Data("> 80", timer5)));

    	
    	

    	
        if(this.checkWeight.isSelected()) {
        	
    		
    	}if(this.checkHeight.isSelected()) {
    		
    	}if(this.checkSex.isSelected()) {
    		
    	}if(this.checkHospital.isSelected()) {
    		
    	}if(this.checkPatientLocation.isSelected()) {
    		
    	}if(this.checkMedication.isSelected()) {
    		
    	}if(this.ckeckOtherPathologies.isSelected()) {
    		
    	}if(this.ckeckBloodType.isSelected()) {
    		
    	}
     	pieChart.setData(pieChartData);


    }

   
    @FXML
    void checkHei(ActionEvent event) {
    	lineChart.setVisible(false);

    	pieChartData =  FXCollections.observableArrayList(new PieChart.Data("<1'00 m", Main.getInter().getNumberPatientsbyRangeofFeature("height", (float)1.0, 0)),
    			new PieChart.Data("1'00 - 1'50 m", Main.getInter().getNumberPatientsbyRangeofFeature("height", (float)1.50, (float)1.0)),
    			new PieChart.Data("1'50 - 1'75 m", Main.getInter().getNumberPatientsbyRangeofFeature("height", (float)1.75, (float)1.50)),
    			new PieChart.Data("1'75 - 2'00 m", Main.getInter().getNumberPatientsbyRangeofFeature("height", (float)2.00, (float)1.75)),
    			new PieChart.Data(">2'00 m", Main.getInter().getNumberPatientsbyRangeofFeature("height", (float)5.0, (float)2.0)));

    	 if(this.checkWeight.isSelected()) {
     		
     	}if(this.checkAge.isSelected()) {
     		
     	}if(this.checkSex.isSelected()) {
     		
     	}if(this.checkHospital.isSelected()) {
     		
     	}if(this.checkPatientLocation.isSelected()) {
     		
     	}if(this.checkMedication.isSelected()) {
     		
     	}if(this.ckeckOtherPathologies.isSelected()) {
     		
     	}if(this.ckeckBloodType.isSelected()) {
     		
     	}
     	pieChart.setData(pieChartData);

    }

    @FXML
    void checkWei(ActionEvent event) {
lineChart.setVisible(false);
System.out.println("goald");

    	pieChartData =  FXCollections.observableArrayList(new PieChart.Data("<20 kg", Main.getInter().getNumberPatientsbyRangeofFeature("weight", 20, 0)),new PieChart.Data("20-50 kg", Main.getInter().getNumberPatientsbyRangeofFeature("weight", 50, 20)),new PieChart.Data("50-70 kg", Main.getInter().getNumberPatientsbyRangeofFeature("weight", 70, 50)),new PieChart.Data("70-90 kg", Main.getInter().getNumberPatientsbyRangeofFeature("weight", 90, 70)),new PieChart.Data("90-110 kg", Main.getInter().getNumberPatientsbyRangeofFeature("weight", 110, 90)),new PieChart.Data(">110 kg", Main.getInter().getNumberPatientsbyRangeofFeature("weight", 900, 110)));
    	
    	 if(this.checkAge.isSelected()) {
     		
     	}if(this.checkHeight.isSelected()) {
     		
     	}if(this.checkSex.isSelected()) {
     		
     	}if(this.checkHospital.isSelected()) {
     		
     	}if(this.checkPatientLocation.isSelected()) {
     		
     	}if(this.checkMedication.isSelected()) {
     		
     	}if(this.ckeckOtherPathologies.isSelected()) {
     		
     	}if(this.ckeckBloodType.isSelected()) {
     		
     	}
     	pieChart.setData(pieChartData);

    }

    @FXML
    void onCheckHos(ActionEvent event) {
    	lineChart.setVisible(false);

    	List<String> Hospitals = Main.getInter().getdifferentHospitals();
    	List<PieChart.Data> piedatas =new ArrayList<>();
    	for (int i =0; i<Hospitals.size();i++) {
    		System.out.println(Hospitals.get(i));
    		piedatas.add(new PieChart.Data(""+Hospitals.get(i), Main.getInter().getNumberPatientsbyanyString("hospital",""+ Hospitals.get(i))));
    	}
    	
    	pieChartData =  FXCollections.observableArrayList();
    	pieChartData.addAll(piedatas);

    	
    	
    	
    	 if(this.checkWeight.isSelected()) {
     		
     	}if(this.checkHeight.isSelected()) {
     		
     	}if(this.checkSex.isSelected()) {
     		
     	}if(this.checkAge.isSelected()) {
     		
     	}if(this.checkPatientLocation.isSelected()) {
     		
     	}if(this.checkMedication.isSelected()) {
     		
     	}if(this.ckeckOtherPathologies.isSelected()) {
     		
     	}if(this.ckeckBloodType.isSelected()) {
     		
     	}
     	pieChart.setData(pieChartData);

    }

    @FXML
    void onCheckLocation(ActionEvent event) {
    	
    	lineChart.setVisible(false);

    	pieChartData =  FXCollections.observableArrayList(new PieChart.Data("Home", Main.getInter().getNumberPatientsbyanyString("hos_location","Home" )),
    			new PieChart.Data("Floor hospital", Main.getInter().getNumberPatientsbyanyString("hos_location", "Floor hospital")),
    			new PieChart.Data("ICU", Main.getInter().getNumberPatientsbyanyString("hos_location", "ICU")));

    	 
    	
    	
    	 if(this.checkWeight.isSelected()) {
     		
     	}if(this.checkHeight.isSelected()) {
     		
     	}if(this.checkSex.isSelected()) {
     		
     	}if(this.checkHospital.isSelected()) {
     		
     	}if(this.checkAge.isSelected()) {
     		
     	}if(this.checkMedication.isSelected()) {
     		
     	}if(this.ckeckOtherPathologies.isSelected()) {
     		
     	}if(this.ckeckBloodType.isSelected()) {
     		
     	}
     	pieChart.setData(pieChartData);

    }

    @FXML
    void onCheckMedication(ActionEvent event) {
       	lineChart.setVisible(false);
       	
    	List<String> Medics = Main.getInter().getdifferentMeds();
    	List<PieChart.Data> piedatas =new ArrayList<>();

       	for (int i =0; i<Medics.size();i++) {
    		System.out.println(Medics.get(i));
    		piedatas.add(new PieChart.Data(""+Medics.get(i), Main.getInter().getdifferentMedsCOUNT( Medics.get(i))));

    	}
       	pieChartData =  FXCollections.observableArrayList();
    	pieChartData.addAll(piedatas);

    	 if(this.checkWeight.isSelected()) {
     		
     	}if(this.checkHeight.isSelected()) {
     		
     	}if(this.checkSex.isSelected()) {
     		
     	}if(this.checkHospital.isSelected()) {
     		
     	}if(this.checkPatientLocation.isSelected()) {
     		
     	}if(this.checkAge.isSelected()) {
     		
     	}if(this.ckeckOtherPathologies.isSelected()) {
     		
     	}if(this.ckeckBloodType.isSelected()) {
     		
     	}
     	pieChart.setData(pieChartData);

    }

    @FXML
    void oncheckSex(ActionEvent event) {
    	lineChart.setVisible(false);
    	
    	pieChartData =  FXCollections.observableArrayList(new PieChart.Data("Male", Main.getInter().searchPatientGenericCOUNT("sex", "M")),new PieChart.Data("Female", Main.getInter().searchPatientGenericCOUNT("sex","F")));
    	
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
    	lineChart.setVisible(false);

    	pieChartData =  FXCollections.observableArrayList(new PieChart.Data("A+", Main.getInter().getNumberPatientsbyanyString("bloodType","A+" )),
    			new PieChart.Data("A-", Main.getInter().getNumberPatientsbyanyString("bloodType", "A-")),
    			new PieChart.Data("B+", Main.getInter().getNumberPatientsbyanyString("bloodType","B+")),
    			new PieChart.Data("B-", Main.getInter().getNumberPatientsbyanyString("bloodType", "B-")),
    			new PieChart.Data("0+", Main.getInter().getNumberPatientsbyanyString("bloodType", "0+")),
    			new PieChart.Data("0-", Main.getInter().getNumberPatientsbyanyString("bloodType", "0-")),
    			new PieChart.Data("AB-", Main.getInter().getNumberPatientsbyanyString("bloodType", "AB-")),
    			new PieChart.Data("AB+", Main.getInter().getNumberPatientsbyanyString("bloodType", "AB+")));

    	 
    	 if(this.checkWeight.isSelected()) {
     		
     	}if(this.checkHeight.isSelected()) {
     		
     	}if(this.checkSex.isSelected()) {
     		
     	}if(this.checkHospital.isSelected()) {
     		
     	}if(this.checkPatientLocation.isSelected()) {
     		
     	}if(this.checkMedication.isSelected()) {
     		
     	}if(this.ckeckOtherPathologies.isSelected()) {
     		
     	}if(this.checkAge.isSelected()) {
     		
     	}
     	pieChart.setData(pieChartData);

    }

    @FXML
    void onckeckOtherPathologies(ActionEvent event) {
lineChart.setVisible(false);
       	
    	List<String> paths = Main.getInter().getdifferentPaths();
    	List<PieChart.Data> piedatas =new ArrayList<>();

       	for (int i =0; i<paths.size();i++) {
    		System.out.println(paths.get(i));
    		piedatas.add(new PieChart.Data(""+paths.get(i), Main.getInter().getdifferentPathsCOUNT( paths.get(i))));

    	}
       	pieChartData =  FXCollections.observableArrayList();
    	pieChartData.addAll(piedatas);
    	 if(this.checkWeight.isSelected()) {
     		
     	}if(this.checkHeight.isSelected()) {
     		
     	}if(this.checkSex.isSelected()) {
     		
     	}if(this.checkHospital.isSelected()) {
     		
     	}if(this.checkPatientLocation.isSelected()) {
     		
     	}if(this.checkMedication.isSelected()) {
     		
     	}if(this.checkAge.isSelected()) {
     		
     	}if(this.ckeckBloodType.isSelected()) {
     		
     	}
     	pieChart.setData(pieChartData);

    }

	private void loadData() {
		list.removeAll(list);
		String a = "Dead";
		String b = "Alive";

		list.addAll(a, b);
		typeofpatient.getItems().addAll(list);

	}

}
