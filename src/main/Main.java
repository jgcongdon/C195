package main;

import helper.JDBC;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.Statement;

public class Main extends Application {
    @Override
    public void start (Stage stage) throws Exception {



        Parent root = FXMLLoader.load(getClass().getResource("/view/LogIn.fxml"));
        stage.setTitle("Log In");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {

        JDBC.openConnection();

        launch(args);

        JDBC.closeConnection();
    }

}
