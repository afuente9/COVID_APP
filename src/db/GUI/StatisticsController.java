package db.GUI;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import com.itextpdf.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import javafx.embed.swing.*;

public class StatisticsController implements Initializable {
	ObservableList list = FXCollections.observableArrayList();

	@FXML
	private Button backfromstatistics;

	@FXML
	private ChoiceBox<String> typeofpatient;

	@FXML
	private Label plotTittle;

	@FXML
	private Button checkAge;

	@FXML
	private Button checkWeight;

	@FXML
	private Button ckeckOtherPathologies;
    @FXML
    private Button bycountry;
    
	@FXML
	private Button checkMedication;

	@FXML
	private Button checkPatientLocation;
	boolean alive;

	@FXML
	private Button checkHeight;

	@FXML
	private Button checkSex;

	@FXML
	private Button checkHospital;

	@FXML
	private Button pdfbutton;
	ObservableList<PieChart.Data> pieChartData;
	@FXML
	private PieChart pieChart;

	@FXML
	private LineChart<?, ?> lineChart;

	XYChart.Series series;

	@FXML
	private CategoryAxis daysAxis;
	@FXML
	private Button ckeckBloodType;
	@FXML
	private NumberAxis numAxis;

	@FXML
	void backfromstatistics(ActionEvent event) {
		Stage stage = (Stage) this.pdfbutton.getScene().getWindow();
		stage.close();
	}
	@FXML
    void nBycotry(ActionEvent event) {
		lineChart.setVisible(false);
		pieChart.setVisible(true);

		plotTittle.setText("by country");

		List<String> countries = Main.getInter().getdifferentCountries(this.alive);
		List<PieChart.Data> piedatas = new ArrayList<>();
		for (int i = 1; i < countries.size(); i++) {
			piedatas.add(new PieChart.Data("" + countries.get(i),
					Main.getInter().getNumberPatientsbyGOVID(i, this.alive)));
		}

		pieChartData = FXCollections.observableArrayList();
		pieChartData.addAll(piedatas);

		pieChart.setData(pieChartData);
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
						// did it like that so it works well
						plotTittle.setText("Dead people per day");

						this.alive = false;
						lineChart.setVisible(true);

						pieChart.setVisible(false);

						List<Integer> listDeaths = Main.getInter().getNumberofDeadsofEachDay();
						for (int i = 0; i < listDeaths.size(); i++) {
							series.getData().add(new XYChart.Data("" + i, listDeaths.get(i)));

						}

						lineChart.getData().addAll(series);

					} else if (typeofpatient.getValue().toString().equals("Dead") == true) {
						// did it like that so it works well
						plotTittle.setText("Infected people per day");
						this.alive = true;
						pieChart.setVisible(false);
						lineChart.setVisible(true);

						List<Integer> listInfecteds = Main.getInter().getNumberofInfectedsofEachDay();
						for (int i = 0; i < listInfecteds.size(); i++) {
							series.getData().add(new XYChart.Data("" + i, listInfecteds.get(i)));

						}

						lineChart.getData().addAll(series);

					}
					this.checkAge.setDisable(false);
					this.checkHeight.setDisable(false);
					this.checkHospital.setDisable(false);
					this.checkMedication.setDisable(false);
					this.checkPatientLocation.setDisable(false);
					this.checkSex.setDisable(false);
					this.checkWeight.setDisable(false);
					this.ckeckOtherPathologies.setDisable(false);
					this.ckeckBloodType.setDisable(false);
					this.bycountry.setDisable(false);

				});

	}

	@FXML
	void CheckAge(ActionEvent event) {
		lineChart.setVisible(false);
		pieChart.setVisible(true);

		plotTittle.setText("by age");

		List<Date> dates = Main.getInter().getDates(this.alive);
		List<Integer> ages = new ArrayList<>();
		LocalDate today = LocalDate.now();
		for (int i = 0; i < dates.size(); i++) {
			System.out.println(i);

			LocalDate fHoy = LocalDate.now();
			LocalDate cumple = dates.get(i).toLocalDate();
			long age = ChronoUnit.YEARS.between(cumple, fHoy);

			ages.add((int) age);
		}
		int timer1 = 0;
		int timer2 = 0;
		int timer3 = 0;
		int timer4 = 0;
		int timer5 = 0;

		for (int i = 0; i < ages.size(); i++) {

			if (ages.get(i) > 0 && ages.get(i) < 20) {
				timer1++;
			}
			if (ages.get(i) > 20 && ages.get(i) < 40) {
				timer2++;
			}
			if (ages.get(i) > 40 && ages.get(i) < 60) {
				timer3++;
			}
			if (ages.get(i) > 60 && ages.get(i) < 80) {
				timer4++;
			}
			if (ages.get(i) > 80) {
				timer5++;
			}

		}

		pieChartData = FXCollections
				.observableArrayList(FXCollections.observableArrayList(new PieChart.Data("<20 ", timer1),
						new PieChart.Data("20 - 40", timer2), new PieChart.Data("40- 60", timer3),
						new PieChart.Data("60 - 80", timer4), new PieChart.Data("> 80", timer5)));

		pieChart.setData(pieChartData);

	}

	@FXML
	void checkHei(ActionEvent event) {
		lineChart.setVisible(false);
		pieChart.setVisible(true);

		plotTittle.setText("by height");

		pieChartData = FXCollections
				.observableArrayList(
						new PieChart.Data("<1'00 m",
								Main.getInter().getNumberPatientsbyRangeofFeature("height", (float) 1.0, 0,
										this.alive)),
						new PieChart.Data("1'00 - 1'50 m",
								Main.getInter().getNumberPatientsbyRangeofFeature("height", (float) 1.50, (float) 1.0,
										this.alive)),
						new PieChart.Data("1'50 - 1'75 m",
								Main.getInter().getNumberPatientsbyRangeofFeature("height", (float) 1.75, (float) 1.50,
										this.alive)),
						new PieChart.Data("1'75 - 2'00 m",
								Main.getInter().getNumberPatientsbyRangeofFeature("height", (float) 2.00, (float) 1.75,
										this.alive)),
						new PieChart.Data(">2'00 m", Main.getInter().getNumberPatientsbyRangeofFeature("height",
								(float) 5.0, (float) 2.0, this.alive)));

		pieChart.setData(pieChartData);

	}

	@FXML
	void checkWei(ActionEvent event) {
		lineChart.setVisible(false);
		pieChart.setVisible(true);

		plotTittle.setText("by weight");

		pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("<20 kg",
						Main.getInter().getNumberPatientsbyRangeofFeature("weight", 20, 0, this.alive)),
				new PieChart.Data("20-50 kg",
						Main.getInter().getNumberPatientsbyRangeofFeature("weight", 50, 20, this.alive)),
				new PieChart.Data("50-70 kg",
						Main.getInter().getNumberPatientsbyRangeofFeature("weight", 70, 50, this.alive)),
				new PieChart.Data("70-90 kg",
						Main.getInter().getNumberPatientsbyRangeofFeature("weight", 90, 70, this.alive)),
				new PieChart.Data("90-110 kg",
						Main.getInter().getNumberPatientsbyRangeofFeature("weight", 110, 90, this.alive)),
				new PieChart.Data(">110 kg",
						Main.getInter().getNumberPatientsbyRangeofFeature("weight", 900, 110, this.alive)));

		pieChart.setData(pieChartData);

	}

	@FXML
	void onCheckHos(ActionEvent event) {
		lineChart.setVisible(false);
		pieChart.setVisible(true);

		plotTittle.setText("by hospital");

		List<String> Hospitals = Main.getInter().getdifferentHospitals(this.alive);
		List<PieChart.Data> piedatas = new ArrayList<>();
		for (int i = 0; i < Hospitals.size(); i++) {
			System.out.println(Hospitals.get(i));
			piedatas.add(new PieChart.Data("" + Hospitals.get(i),
					Main.getInter().getNumberPatientsbyanyString("hospital", "" + Hospitals.get(i), this.alive)));
		}

		pieChartData = FXCollections.observableArrayList();
		pieChartData.addAll(piedatas);

		pieChart.setData(pieChartData);
	}

	@FXML
	void onCheckLocation(ActionEvent event) {
		lineChart.setVisible(false);
		pieChart.setVisible(true);

		plotTittle.setText("by location");

		pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("Home",
						Main.getInter().getNumberPatientsbyanyString("hos_location", "Home", this.alive)),
				new PieChart.Data("Floor hospital",
						Main.getInter().getNumberPatientsbyanyString("hos_location", "Floor hospital", this.alive)),
				new PieChart.Data("ICU",
						Main.getInter().getNumberPatientsbyanyString("hos_location", "ICU", this.alive)));

		pieChart.setData(pieChartData);

	}

	@FXML
	void onCheckMedication(ActionEvent event) {
		lineChart.setVisible(false);
		pieChart.setVisible(true);

		plotTittle.setText("by medication");

		List<String> Medics = Main.getInter().getdifferentMeds(this.alive);
		List<PieChart.Data> piedatas = new ArrayList<>();

		for (int i = 0; i < Medics.size(); i++) {
			System.out.println(Medics.get(i));
			piedatas.add(new PieChart.Data("" + Medics.get(i),
					Main.getInter().getdifferentMedsCOUNT(Medics.get(i), this.alive)));

		}
		pieChartData = FXCollections.observableArrayList();
		pieChartData.addAll(piedatas);

		pieChart.setData(pieChartData);
	}

	@FXML
	void oncheckSex(ActionEvent event) {
		lineChart.setVisible(false);
		pieChart.setVisible(true);

		plotTittle.setText("by sex");

		pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("Male", Main.getInter().searchPatientGenericCOUNT("sex", "M", this.alive)),
				new PieChart.Data("Female", Main.getInter().searchPatientGenericCOUNT("sex", "F", this.alive)));

		pieChart.setData(pieChartData);

	}

	@FXML
	void onckeckBloodType(ActionEvent event) {
		lineChart.setVisible(false);
		pieChart.setVisible(true);

		plotTittle.setText("by blood type");

		pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("A+", Main.getInter().getNumberPatientsbyanyString("bloodType", "A+", this.alive)),
				new PieChart.Data("A-", Main.getInter().getNumberPatientsbyanyString("bloodType", "A-", this.alive)),
				new PieChart.Data("B+", Main.getInter().getNumberPatientsbyanyString("bloodType", "B+", this.alive)),
				new PieChart.Data("B-", Main.getInter().getNumberPatientsbyanyString("bloodType", "B-", this.alive)),
				new PieChart.Data("0+", Main.getInter().getNumberPatientsbyanyString("bloodType", "0+", this.alive)),
				new PieChart.Data("0-", Main.getInter().getNumberPatientsbyanyString("bloodType", "0-", this.alive)),
				new PieChart.Data("AB-", Main.getInter().getNumberPatientsbyanyString("bloodType", "AB-", this.alive)),
				new PieChart.Data("AB+", Main.getInter().getNumberPatientsbyanyString("bloodType", "AB+", this.alive)));

		pieChart.setData(pieChartData);

	}

	@FXML
	void onckeckOtherPathologies(ActionEvent event) {
		lineChart.setVisible(false);
		pieChart.setVisible(true);

		plotTittle.setText("by other pathologies");

		List<String> paths = Main.getInter().getdifferentPaths(this.alive);
		List<PieChart.Data> piedatas = new ArrayList<>();

		for (int i = 0; i < paths.size(); i++) {
			System.out.println(paths.get(i));
			piedatas.add(new PieChart.Data("" + paths.get(i),
					Main.getInter().getdifferentPathsCOUNT(paths.get(i), this.alive)));

		}
		pieChartData = FXCollections.observableArrayList();
		pieChartData.addAll(piedatas);

		pieChart.setData(pieChartData);
	}

	private void loadData() {
		list.removeAll(list);
		String a = "Dead";
		String b = "Alive";

		list.addAll(a, b);
		typeofpatient.getItems().addAll(list);

	}
	/*
	@FXML
	public void saveAsPng() {

	    WritableImage image = lineChart.snapshot(new SnapshotParameters(), null);
	    File file = new File("C://Users//Dania//Desktop");
		doResize((Node)lineChart,file,30,30);

	    try {
	        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}*/
	
	 public void saveAsPng(Scene scene, String path) {
		    WritableImage image = scene.snapshot(null);
         File file = new File(path);
         try {
             ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

	@FXML
	void ongeneratePdf(ActionEvent event) {
		Document document = new Document();
		try {
			String path = System.getProperty("user.home");
			PdfWriter.getInstance(document, new FileOutputStream(path + "/Desktop/data.pdf"));
			Image header= Image.getInstance("src\\db\\img\\header.png");
			header.scaleToFit(650,1000);
			header.setAlignment(Chunk.ALIGN_CENTER);
			Paragraph parr= new Paragraph();
		    parr.setAlignment(Paragraph.ALIGN_CENTER);
			parr.add("estos son los resultados");
			parr.setFont(FontFactory.getFont("tahoma", 18,Font.ITALIC,BaseColor.BLUE));
			parr.add("esta es la tabla \n\n");
		
			
			//Obtaining all the charts
			
			
			Stage stage = new Stage();
			 stage.setTitle("Line Chart Sample");
	            //defining the axes
	            final NumberAxis xAxis = new NumberAxis();
	            final NumberAxis yAxis = new NumberAxis();
	            xAxis.setLabel("Number of Month");
	            xAxis.setLabel("Number of Month");
	            //creating the chart
	            LineChart<Number, Number> lineChart =
	                    new LineChart<Number, Number>(xAxis, yAxis);
	            lineChart.setTitle("Stock Monitoring, 2010");
	            //defining a series
	            XYChart.Series series = new XYChart.Series();
	            series.setName("My portfolio");
	            //populating the series with data
	            series.getData().add(new XYChart.Data(1, 23));
	            series.getData().add(new XYChart.Data(2, 14));
	            series.getData().add(new XYChart.Data(3, 15));
	            series.getData().add(new XYChart.Data(4, 24));
	            series.getData().add(new XYChart.Data(5, 34));
	            Scene scene = new Scene(lineChart, 800, 600);
	            lineChart.setAnimated(false);
	            lineChart.getData().add(series);
	           
	            saveAsPng(scene, "c:\\temp\\chart.png");
	            stage.setScene(scene);
	            saveAsPng(scene, "c:\\temp\\chart1.png");
	            //stage.show();
	            System.out.println("After show");
			
			
			
			
			
			
			
			document.open();
document.add(header);
document.add(parr);
			
			PdfPTable table = new PdfPTable(13);
			table.addCell("Name");
			table.addCell("Birth date");
			table.addCell("Sex");
			table.addCell("Height");
			table.addCell("Weight");
			table.addCell("SS Num");
			table.addCell("Hospital");
			table.addCell("Infected");
			table.addCell("Introduced");
			table.addCell("Alive");
			table.addCell("Location");
			table.addCell("Blood group");
			table.addCell("Vaccinated");
			//table.addCell("Medication");
			//table.addCell("Other pathologies");
try {
	
			Connection cn = DriverManager.getConnection("jdbc:sqlite:./database/covid.database", "root", "");
			PreparedStatement pst = cn.prepareStatement("select name,  birthday, sex, height, weight,social_security, hospital, alive, hos_location, bloodType, vaccinated    from patients");

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {

				do {
					table.addCell(rs.getString(1));
					table.addCell(rs.getString(2));
					table.addCell(rs.getString(3));
					table.addCell(rs.getString(4));
					table.addCell(rs.getString(5));
					table.addCell(rs.getString(6));
					table.addCell(rs.getString(7));
					table.addCell(rs.getString(8));
					table.addCell(rs.getString(9));
					table.addCell(rs.getString(10));
					table.addCell(rs.getString(11));
				
					
				} while (rs.next());
				document.add(table);
				
			}
			}catch(Exception e) {
				e.printStackTrace();
			}
document.close();
JOptionPane.showMessageDialog(null, "creado pdf"); 

		} catch (Exception e) {
			e.printStackTrace();

		}
	}/*
	void doResize(final Node node, final File file, final int aWidth, final int aHeight) {

	    final AnchorPane anchorPane = new AnchorPane();
	    anchorPane.setMinSize(aWidth, aHeight);
	    anchorPane.setMaxSize(aWidth, aHeight);
	    anchorPane.setPrefSize(aWidth, aHeight);

	    final ScrollPane scrollPane = new ScrollPane();
	    scrollPane.setContent(anchorPane);

	    final JFXPanel fxPanel = new JFXPanel();
	    fxPanel.setScene(new Scene(scrollPane));

	    final JFrame frame = new JFrame();

	    final Pane previousParentPane = (Pane) node.getParent();

	    frame.setSize(new Dimension(64, 64));
	    frame.setVisible(false);
	    frame.add(fxPanel);

	    anchorPane.getChildren().clear();
	    AnchorPane.setLeftAnchor(node, 0.0);
	    AnchorPane.setRightAnchor(node, 0.0);
	    AnchorPane.setTopAnchor(node, 0.0);
	    AnchorPane.setBottomAnchor(node, 0.0);
	    anchorPane.getChildren().add(node);

	    anchorPane.layout();

	    try {
	        final SnapshotParameters snapshotParameters = new SnapshotParameters();
	        snapshotParameters.setViewport(new Rectangle2D(0.0, 0.0, aWidth, aHeight));
	        ImageIO.write(SwingFXUtils.fromFXImage(anchorPane.snapshot(snapshotParameters, new WritableImage(aWidth, aHeight)), new BufferedImage(aWidth, aHeight, BufferedImage.TYPE_INT_ARGB)), "png", file);

	    }
	    catch (final Exception e) {
	        e.printStackTrace();
	    }
	    finally {
	        Platform.runLater(new Runnable() {
	            @Override
	            public void run() {
	                // Return the node back into it's previous parent
	                previousParentPane.getChildren().clear();
	                AnchorPane.setLeftAnchor(node, 0.0);
	                AnchorPane.setRightAnchor(node, 0.0);
	                AnchorPane.setTopAnchor(node, 0.0);
	                AnchorPane.setBottomAnchor(node, 0.0);
	                previousParentPane.getChildren().add(node);

	                frame.dispose();
	            }
	        });
	    }
	}
*/
}