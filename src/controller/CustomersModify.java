package controller;

import DAO.CountryDaoImpl;
import DAO.FirstLevelDivisionDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import model.Customer;
import model.FirstLevelDivision;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomersModify implements Initializable {

    Stage stage;
    Parent scene;

    private static Customer selectedCustomer;
    private static int selectedCountryID;
    private static Country selectedCountry;

    @FXML
    private ComboBox<FirstLevelDivision> divisionCombo;

    @FXML
    private ComboBox<Country> countryCombo;

    @FXML
    private Label customersModifyIDLabel;

    @FXML
    private TextField customersModifyNameLabel;

    @FXML
    private TextField customersModifyPhoneLabel;

    @FXML
    private TextField customersModifyAddressLabel;

    @FXML
    private TextField customersModifyPostalLabel;

    public CustomersModify() throws SQLException {
    }

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
    void onActionCountryCombo(ActionEvent event) throws SQLException {
        divisionCombo.setValue(null);
        Country C = countryCombo.getValue();
        divisionCombo.setItems(FirstLevelDivisionDaoImpl.getDiv(C.getCountry_ID()));

    }

    public static void receiveSelectedCustomer(Customer customer) {
        selectedCustomer = customer;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            divisionCombo.setValue(FirstLevelDivisionDaoImpl.getDivFromDivID(selectedCustomer.getDivision_ID()));
            FirstLevelDivision selectedDivision = FirstLevelDivisionDaoImpl.getDivision(divisionCombo.getValue());
            selectedCountryID = selectedDivision.getCOUNTRY_ID();
            selectedCountry = CountryDaoImpl.getCountryFromCountryID(selectedCountryID);
            countryCombo.setValue(selectedCountry);
            Country C = countryCombo.getValue();
            divisionCombo.setItems(FirstLevelDivisionDaoImpl.getDiv(C.getCountry_ID()));
            countryCombo.setItems(CountryDaoImpl.getAllCountries());
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        try {
            countryCombo.setItems(CountryDaoImpl.getAllCountries());
            FirstLevelDivision selectedDivision = FirstLevelDivisionDaoImpl.getDivision(divisionCombo.getValue());
            selectedCountryID = selectedDivision.getCOUNTRY_ID();
            selectedCountry = CountryDaoImpl.getCountryFromCountryID(selectedCountryID);
            countryCombo.setValue(selectedCountry);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Country C = countryCombo.getValue();
        try {
            divisionCombo.setItems(FirstLevelDivisionDaoImpl.getDiv(C.getCountry_ID()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        */

        customersModifyIDLabel.setText(Integer.toString(selectedCustomer.getCustomerId()));
        customersModifyNameLabel.setText(selectedCustomer.getCustomerName());
        customersModifyAddressLabel.setText(selectedCustomer.getCustomerAddress());
        customersModifyPhoneLabel.setText(selectedCustomer.getCustomerPhone());
        customersModifyPostalLabel.setText(selectedCustomer.getPostalCode());

    }
}
