package main;

import Interfaces.*;
import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ResourceBundle;
import java.util.Locale;
import java.util.TimeZone;

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
        //Locale.setDefault(new Locale("fr"));
        //TimeZone.setDefault(TimeZone.getTimeZone("America/New_York"));
        //TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        GeneralInterface square = a -> a * a;
        System.out.println("GI: " + square.calculateSquare(5));

        GeneralInterface2 square2 = b -> b * b;
        System.out.println("GI2: " + square2.calculateSquare(5));

        GeneralInterface3 message = s -> "GI3: Hello " + s;
        System.out.println(message.getMessage("Jackson"));

        GeneralInterface4 message2 = t -> System.out.println("GI4: Hello again " + t);
        message2.displayMessage("Jackson!");

        GeneralInterface5 sum = (c1, c2) -> c1 + c2;
        System.out.println("GI5: " + sum.calculateSum(5, 10));

        GeneralInterface6 message3 = () -> System.out.println("GI6: Hello World! ");
        message3.displayMessage2();

        GeneralInterface7 square3 = e -> {
            int result = e * e;
            return result;
        };
        System.out.println("GI7: " + square3.calculateSquare(6));

        final int num = 50;
        GeneralInterface8 square4 = f -> f * f;
        System.out.println("GI8: " + square4.calculateSquare(num));




        /*GeneralInterface square = n -> {
          int result = n * n;
          return result;
        };

        System.out.println(square.calculateSquare(6));*/

        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
    }
}