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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Country;
import model.FirstLevelDivision;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class CustomersAdd implements Initializable {



    Stage stage;
    Parent scene;

    public ComboBox<Country> countryCombo;
    public ComboBox<FirstLevelDivision> divisionCombo;

    @FXML
    private Label customersAddIDLabel;

    @FXML
    private TextField customersAddNameLabel;

    @FXML
    private TextField customersAddPhoneLabel;

    @FXML
    private TextField customersAddAddressLabel;

    @FXML
    private TextField customersAddPostalLabel;

    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

        try {
            int customerId = Integer.parseInt(customersAddIDLabel.getText());
            String customerName = customersAddNameLabel.getText();
            String customerAddress = customersAddAddressLabel.getText();
            String postalCode = customersAddPostalLabel.getText();
            String customerPhone = customersAddPhoneLabel.getText();
            LocalDateTime createDate = LocalDateTime.now();
            String createdBy;
            LocalDateTime lastUpdate = LocalDateTime.now();
            String lastUpdateBy;
            int Division_ID = FirstLevelDivisionDaoImpl.getDivID(divisionCombo.getValue().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionDivisionCombo(ActionEvent event) {

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