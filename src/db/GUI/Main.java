package db.GUI;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;

import db.interfaces.Cov_Manager;
import db.jdbc.JDBCManagment;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
public class Main extends Application{
	private static Cov_Manager inter = new JDBCManagment();
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	@Override
	public void start(Stage primaryStage) {
		try {
			getInter().connect();

			Pane root = (Pane)FXMLLoader.load(getClass().getResource("MainMenuView.fxml"));
			Scene scene = new Scene(root,700,700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
	public static Cov_Manager getInter() {
		return inter;
	}
	public static void setInter(Cov_Manager inter) {
		Main.inter = inter;
	}
}
