package controller;

import DAO.*;
import helper.Globals;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Country;
import model.Customer;
import model.FirstLevelDivision;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
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

        try {
            String customerName = customersAddNameLabel.getText();
            String customerAddress = customersAddAddressLabel.getText();
            String postalCode = customersAddPostalLabel.getText();
            String customerPhone = customersAddPhoneLabel.getText();
            Timestamp createDate = Timestamp.valueOf(LocalDateTime.now());
            String createdBy = Globals.userName;
            Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());
            String lastUpdateBy = Globals.userName;
            FirstLevelDivision D = divisionCombo.getValue();


            if (customerName.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("ERROR: The name must not be empty");
                alert.showAndWait();
            }

            else if (customerAddress.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("ERROR: The address must not be empty");
                alert.showAndWait();
            }

            else if (postalCode.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("ERROR: The postal code must not be empty");
                alert.showAndWait();
            }

            else if (customerPhone.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("ERROR: The phone must not be empty");
                alert.showAndWait();
            }

            else if (divisionCombo.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("ERROR: The division must not be empty");
                alert.showAndWait();
            }

            else if (countryCombo.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("ERROR: The country must not be empty");
                alert.showAndWait();
            }

            else{

                int Division_ID = D.getDivision_ID();

                CustomerDaoImpl.addCustomer(customerName, customerAddress, postalCode, customerPhone, createDate, createdBy, lastUpdate, lastUpdateBy, Division_ID);

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/Customers.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();

            }

        } catch (Exception e) {
            e.printStackTrace();
            return;
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
    void OnActionCountryCombo(ActionEvent event) throws SQLException {
        Country C = countryCombo.getValue();
        divisionCombo.setItems(FirstLevelDivisionDaoImpl.getDiv(C.getCountry_ID()));

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