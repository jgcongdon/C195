package controller;

import DAO.ContactDaoImpl;
import DAO.CountryDaoImpl;
import DAO.CustomerDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;
import model.Country;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Reports implements Initializable {

    Stage stage;
    Parent scene;


    @FXML
    private ComboBox<?> monthCombo;

    @FXML
    private ComboBox<Appointment> typeCombo;

    @FXML
    private Label appointmentsTotalLabel;

    @FXML
    private ComboBox<Contact> contactCombo;


    @FXML
    private ComboBox<Country> countryCombo;

    @FXML
    private Label customersTotalLabel;

    @FXML
    void onActionCountryCombo(ActionEvent event) throws SQLException {
        Country selectedCountry = countryCombo.getValue();
        int selectedCountryID = selectedCountry.getCountry_ID();

        if (selectedCountryID == 1) {
            customersTotalLabel.setText(String.valueOf(CustomerDaoImpl.countCustomersUS()));
        }
        else if (selectedCountryID == 2) {
            customersTotalLabel.setText(String.valueOf(CustomerDaoImpl.countCustomersUK()));
        }
        else if (selectedCountryID == 3) {
            customersTotalLabel.setText(String.valueOf(CustomerDaoImpl.countCustomersCAN()));
        }
    }

    @FXML
    void onActionMainMenu(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            contactCombo.setItems(ContactDaoImpl.getAllContacts());
            countryCombo.setItems(CountryDaoImpl.getAllCountries());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
