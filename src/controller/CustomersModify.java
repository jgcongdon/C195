package controller;

import DAO.CountryDaoImpl;
import DAO.CustomerDaoImpl;
import DAO.FirstLevelDivisionDaoImpl;
import helper.Globals;
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
import java.sql.Timestamp;
import java.time.LocalDateTime;
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

        try {
            int customerID = Integer.parseInt(customersModifyIDLabel.getText());
            String customerName = customersModifyNameLabel.getText();
            String customerAddress = customersModifyAddressLabel.getText();
            String postalCode = customersModifyPostalLabel.getText();
            String customerPhone = customersModifyPhoneLabel.getText();
            Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());
            String lastUpdateBy = Globals.userName;
            FirstLevelDivision D = divisionCombo.getValue();;
            int Division_ID = D.getDivision_ID();

            CustomerDaoImpl.modifyCustomer(customerID, customerName, customerAddress, postalCode, customerPhone, lastUpdate, lastUpdateBy, Division_ID);

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

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

        customersModifyIDLabel.setText(Integer.toString(selectedCustomer.getCustomerId()));
        customersModifyNameLabel.setText(selectedCustomer.getCustomerName());
        customersModifyAddressLabel.setText(selectedCustomer.getCustomerAddress());
        customersModifyPhoneLabel.setText(selectedCustomer.getCustomerPhone());
        customersModifyPostalLabel.setText(selectedCustomer.getPostalCode());

    }
}
