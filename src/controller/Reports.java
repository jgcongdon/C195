package controller;

import DAO.AppointmentDaoImpl;
import DAO.ContactDaoImpl;
import DAO.CountryDaoImpl;
import DAO.CustomerDaoImpl;
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
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Reports implements Initializable {

    Stage stage;
    Parent scene;


    @FXML
    private ComboBox<String> monthCombo;

    @FXML
    private ComboBox<appointmentType> typeCombo;

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

    ObservableList<String> Months = FXCollections.observableList(Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"));



    @FXML
    void onActionTypeCombo(ActionEvent event) throws SQLException {
        appointmentType selectedType = typeCombo.getValue();
        String selectedMonth = monthCombo.getValue();
        if (selectedMonth == "January") {
            int month = 1;
            appointmentsTotalLabel.setText(String.valueOf(AppointmentDaoImpl.countMonthType(selectedType, month)));
        }
        else if (selectedMonth == "February") {
            int month = 2;
            appointmentsTotalLabel.setText(String.valueOf(AppointmentDaoImpl.countMonthType(selectedType, month)));
        }
        else if (selectedMonth == "March") {
            int month = 3;
            appointmentsTotalLabel.setText(String.valueOf(AppointmentDaoImpl.countMonthType(selectedType, month)));
        }
        else if (selectedMonth == "April") {
            int month = 4;
            appointmentsTotalLabel.setText(String.valueOf(AppointmentDaoImpl.countMonthType(selectedType, month)));
        }
        else if (selectedMonth == "May") {
            int month = 5;
            appointmentsTotalLabel.setText(String.valueOf(AppointmentDaoImpl.countMonthType(selectedType, month)));
        }
        else if (selectedMonth == "June") {
            int month = 6;
            appointmentsTotalLabel.setText(String.valueOf(AppointmentDaoImpl.countMonthType(selectedType, month)));
        }
        else if (selectedMonth == "July") {
            int month = 7;
            appointmentsTotalLabel.setText(String.valueOf(AppointmentDaoImpl.countMonthType(selectedType, month)));
        }
        else if (selectedMonth == "August") {
            int month = 8;
            appointmentsTotalLabel.setText(String.valueOf(AppointmentDaoImpl.countMonthType(selectedType, month)));
        }
        else if (selectedMonth == "September") {
            int month = 9;
            appointmentsTotalLabel.setText(String.valueOf(AppointmentDaoImpl.countMonthType(selectedType, month)));
        }
        else if (selectedMonth == "October") {
            int month = 10;
            appointmentsTotalLabel.setText(String.valueOf(AppointmentDaoImpl.countMonthType(selectedType, month)));
        }
        else if (selectedMonth == "November") {
            int month = 11;
            appointmentsTotalLabel.setText(String.valueOf(AppointmentDaoImpl.countMonthType(selectedType, month)));
        }
        else if (selectedMonth == "December") {
            int month = 12;
            appointmentsTotalLabel.setText(String.valueOf(AppointmentDaoImpl.countMonthType(selectedType, month)));
        }

    }

    @FXML
    void onActonMonthCombo(ActionEvent event) throws SQLException {
        appointmentType selectedType = typeCombo.getValue();
        String selectedMonth = monthCombo.getValue();
        if (selectedMonth == "January") {
            int month = 1;
            appointmentsTotalLabel.setText(String.valueOf(AppointmentDaoImpl.countMonthType(selectedType, month)));
        }
        else if (selectedMonth == "February") {
            int month = 2;
            appointmentsTotalLabel.setText(String.valueOf(AppointmentDaoImpl.countMonthType(selectedType, month)));
        }
        else if (selectedMonth == "March") {
            int month = 3;
            appointmentsTotalLabel.setText(String.valueOf(AppointmentDaoImpl.countMonthType(selectedType, month)));
        }
        else if (selectedMonth == "April") {
            int month = 4;
            appointmentsTotalLabel.setText(String.valueOf(AppointmentDaoImpl.countMonthType(selectedType, month)));
        }
        else if (selectedMonth == "May") {
            int month = 5;
            appointmentsTotalLabel.setText(String.valueOf(AppointmentDaoImpl.countMonthType(selectedType, month)));
        }
        else if (selectedMonth == "June") {
            int month = 6;
            appointmentsTotalLabel.setText(String.valueOf(AppointmentDaoImpl.countMonthType(selectedType, month)));
        }
        else if (selectedMonth == "July") {
            int month = 7;
            appointmentsTotalLabel.setText(String.valueOf(AppointmentDaoImpl.countMonthType(selectedType, month)));
        }
        else if (selectedMonth == "August") {
            int month = 8;
            appointmentsTotalLabel.setText(String.valueOf(AppointmentDaoImpl.countMonthType(selectedType, month)));
        }
        else if (selectedMonth == "September") {
            int month = 9;
            appointmentsTotalLabel.setText(String.valueOf(AppointmentDaoImpl.countMonthType(selectedType, month)));
        }
        else if (selectedMonth == "October") {
            int month = 10;
            appointmentsTotalLabel.setText(String.valueOf(AppointmentDaoImpl.countMonthType(selectedType, month)));
        }
        else if (selectedMonth == "November") {
            int month = 11;
            appointmentsTotalLabel.setText(String.valueOf(AppointmentDaoImpl.countMonthType(selectedType, month)));
        }
        else if (selectedMonth == "December") {
            int month = 12;
            appointmentsTotalLabel.setText(String.valueOf(AppointmentDaoImpl.countMonthType(selectedType, month)));
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            contactCombo.setItems(ContactDaoImpl.getAllContacts());
            countryCombo.setItems(CountryDaoImpl.getAllCountries());
            typeCombo.setItems(AppointmentDaoImpl.typeAppt());
            monthCombo.setItems(Months);




        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
