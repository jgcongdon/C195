package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class FirstScreen implements Initializable {
    public Label TheLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I am initialized!");
        TheLabel.setText("Initialized!");

    }

    public void onButtonAction(ActionEvent actionEvent) {
        System.out.println("I am clicked");

        TheLabel.setText("You Clicked It!");
    }
}
