package main;

import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ResourceBundle;

/**
 * <h1>Colandar - a GUI-based scheduling desktop application with filtering options  (C195 - Software II)</h1>
 * This class creates an app that manages a database of customers and their appointments.
 * <p><b>
 * Lambdas found on line 64 of LogIn.java and on line 182 of AppointmentDaoImpl.java
 * </b></p>
 * After unzipping c195jacksoncongdon2.zip, the Javadocs files can be found at /javadoc
 * @author Jackson Congdon
 */
public class Main extends Application {
    /**
     * Loads MainScreen.fxml to start the GUI and display the LogIn screen.
     * @param stage
     * @throws Exception
     */
    @Override
    public void start (Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LogIn.fxml"));
        stage.setTitle(myBundle.getString("Title"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    ResourceBundle myBundle = ResourceBundle.getBundle("bundle/lang");

    /** This is the main method, which launches the database program.
     * @param args
     */
    public static void main(String[] args) {
        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
    }
}