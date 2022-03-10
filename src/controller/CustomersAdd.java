package controller;

import DAO.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.Country;
import model.FirstLevelDivision;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomersAdd implements Initializable {

    Stage stage;
    Parent scene;

    public ComboBox<Country> countryCombo;
    public ComboBox<FirstLevelDivision> divisionCombo;

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void OnActionCountryCombo(ActionEvent event) {
        if (countryCombo.getValue().toString().equals("U.S")) {
            try {
                divisionCombo.setItems(FirstLevelDivisionDaoImpl.getDivUS());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (countryCombo.getValue().toString().equals("UK")) {
            try {
                divisionCombo.setItems(FirstLevelDivisionDaoImpl.getDivUK());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (countryCombo.getValue().toString().equals("Canada")){
            try {
                divisionCombo.setItems(FirstLevelDivisionDaoImpl.getDivCAN());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            countryCombo.setItems(CountryDaoImpl.getAllCountries());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}