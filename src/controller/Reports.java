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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reports implements Initializable {

    Stage stage;
    Parent scene;

    ObservableList<model.Appointment> Appointment = FXCollections.observableArrayList();

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
    private TableView<Appointment> AppointmentTable;

    @FXML
    private TableColumn<Appointment, Integer> Appointment_ID;

    @FXML
    private TableColumn<Appointment, String> Title;

    @FXML
    private TableColumn<Appointment, String> Description;

    @FXML
    private TableColumn<Appointment, String> Location;

    @FXML
    private TableColumn<Appointment, Integer> Contact;

    @FXML
    private TableColumn<Appointment, String> Type;

    @FXML
    private TableColumn<Appointment, Calendar> Start;

    @FXML
    private TableColumn<Appointment, Calendar> End;

    @FXML
    private TableColumn<Appointment, Integer> Customer_ID;

    @FXML
    private TableColumn<Appointment, Integer> User_ID;


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

    @FXML
    void onActionContactCombo(ActionEvent event) {

        AppointmentTable.getItems().clear();
        Appointment_ID.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
        Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        Location.setCellValueFactory(new PropertyValueFactory<>("Location"));
        Contact.setCellValueFactory(new PropertyValueFactory<>("Contact_ID"));
        Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        Start.setCellValueFactory(new PropertyValueFactory<>("Start"));
        End.setCellValueFactory(new PropertyValueFactory<>("End"));
        Customer_ID.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        User_ID.setCellValueFactory(new PropertyValueFactory<>("User_ID"));
        Contact selectedContact = contactCombo.getValue();
        int selectedContactID = selectedContact.getContact_ID();
           try {

                Appointment.addAll(AppointmentDaoImpl.getAppointmentsContactID(selectedContactID));

            } catch (Exception ex) {
                Logger.getLogger(Appointments.class.getName()).log(Level.SEVERE, null, ex);
            }

            AppointmentTable.setItems(Appointment);

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

