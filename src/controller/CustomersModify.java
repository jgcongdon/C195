package controller;

import DAO.CountryDaoImpl;
import DAO.CustomerDaoImpl;
import DAO.FirstLevelDivisionDaoImpl;
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

/**
 * This class creates the CustomersModify controller.
 * @author Jackson Congdon
 */
public class CustomersModify implements Initializable {

    Stage stage;
    Parent scene;
    private static Customer selectedCustomer;
    private static int selectedCountryID;
    private static Country selectedCountry;

    @FXML private ComboBox<FirstLevelDivision> divisionCombo;
    @FXML private ComboBox<Country> countryCombo;
    @FXML private Label customersModifyIDLabel;
    @FXML private TextField customersModifyNameLabel;
    @FXML private TextField customersModifyPhoneLabel;
    @FXML private TextField customersModifyAddressLabel;
    @FXML private TextField customersModifyPostalLabel;

    /**
     * This is the method to return to the Customers screen when the user clicks the Cancel button. No inputted information is saved.
     * @param event the user clicks the Cancel button on the CustomersModify screen
     * @throws IOException
     */
    @FXML void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This is the method to modify an existing customer when the user clicks the Save button. This method updates an existing customer using the information inputted on the CustomersModify screen and updates the modified customer in the Customers table of the database. The method updates the existing customer being modified within the Customers table of the database using the customer ID.
     * @param event the user clicks the Save button on the CustomersModify screen
     * @throws IOException
     */
    @FXML void onActionSave(ActionEvent event) throws IOException {
        try {
            int customerID = Integer.parseInt(customersModifyIDLabel.getText());
            String customerName = customersModifyNameLabel.getText();
            String customerAddress = customersModifyAddressLabel.getText();
            String postalCode = customersModifyPostalLabel.getText();
            String customerPhone = customersModifyPhoneLabel.getText();
            Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());
            String lastUpdateBy = Globals.userName;
            FirstLevelDivision D = divisionCombo.getValue();;

            if (customerName.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("ERROR: The name must not be empty");
                alert.showAndWait();
            } else if (customerAddress.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("ERROR: The address must not be empty");
                alert.showAndWait();
            } else if (postalCode.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("ERROR: The postal code must not be empty");
                alert.showAndWait();
            } else if (customerPhone.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("ERROR: The phone must not be empty");
                alert.showAndWait();
            } else if (divisionCombo.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("ERROR: The division must not be empty");
                alert.showAndWait();
            } else if (countryCombo.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("ERROR: The country must not be empty");
                alert.showAndWait();
            } else {

                int Division_ID = D.getDivision_ID();
                CustomerDaoImpl.modifyCustomer(customerID, customerName, customerAddress, postalCode, customerPhone, lastUpdate, lastUpdateBy, Division_ID);

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

    /**
     * This is the method to set the divisionCombo combo box with only the divisions for only the specific country when the user selects a country from the countryCombo combo box.
     * @param event the user selects a country from the countryCombo combo box
     * @throws SQLException
     */
    @FXML void onActionCountryCombo(ActionEvent event) throws SQLException {
        divisionCombo.setValue(null);
        Country C = countryCombo.getValue();
        divisionCombo.setItems(FirstLevelDivisionDaoImpl.getDiv(C.getCountry_ID()));
    }

    /**
     * This method takes the customer selected on the Customers screen to be modified and sends the customer data to the fields on the CUstomersModify screen.
     * @param customer the selected existing customer being received to modify
     */
    public static void receiveSelectedCustomer(Customer customer) {
        selectedCustomer = customer;
    }

    /**
     * This is the method to set the combo boxes with all appropriate values, set the combo boxes to select the data from the modified customer, and populate all fields with the data from the modified customer.
     * @param url
     * @param resourceBundle
     */
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