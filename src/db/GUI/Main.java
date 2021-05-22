package db.GUI;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import db.interfaces.Cov_Manager;
import db.interfaces.UserManager;
import db.jdbc.JDBCManagment;
import db.jpa.JPAUserManagment;
import db.pojos.users.User;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
public class Main extends Application{
	private static UserManager userman = new JPAUserManagment();
	private static Connection c;

	private static Cov_Manager inter = new JDBCManagment();
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	public static void connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./database/covid.database");
			c.createStatement().execute("PRAGMA foreign_keys = ON");
			System.out.println("Database connection opened");
			inter.creatTables();
		} catch (SQLException E) {
			System.out.println("There was a database exception.");
			E.printStackTrace();
		} catch (Exception e) {
			System.out.println("There was a general exception.");
			e.printStackTrace();
		}

	}
	public static UserManager getUserman() {
		return userman;
	}
	public static void setUserman(UserManager userman) {
		Main.userman = userman;
	} 
	@Override
	public void start(Stage primaryStage) {
		try {
		//	getUserman().connect();

			getInter().connect();

			Pane root = (Pane)FXMLLoader.load(getClass().getResource("MainMenuView.fxml"));
			Scene scene = new Scene(root);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setIconified(false);
  
			primaryStage.setScene(scene);

			primaryStage.setResizable(false);
			primaryStage.getIcons().add(new Image("https://image.flaticon.com/icons/png/512/2833/2833315.png"));

			primaryStage.setTitle("Covidist");

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
