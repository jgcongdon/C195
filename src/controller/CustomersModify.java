package controller;

import DAO.CountryDaoImpl;
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
import java.util.ResourceBundle;

public class CustomersModify implements Initializable {

    Stage stage;
    Parent scene;

    private static Customer selectedCustomer;




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

    public static void receiveSelectedCustomer(Customer customer) {
        selectedCustomer = customer;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
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
