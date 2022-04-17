package main;

import helper.JDBC;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {
    @Override
    public void start (Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/view/LogIn.fxml"));
        stage.setTitle(myBundle.getString("Title"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    ResourceBundle myBundle = ResourceBundle.getBundle("bundle/lang");

    public static void main(String[] args) {

        Locale.setDefault(new Locale("fr"));

        JDBC.openConnection();

        launch(args);

        JDBC.closeConnection();
    }

}
