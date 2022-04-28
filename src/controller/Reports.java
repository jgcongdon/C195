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

/**
 * This class creates the Reports controller.
 * @author Jackson Congdon
 */
public class Reports implements Initializable {

    Stage stage;
    Parent scene;
    ObservableList<model.Appointment> Appointment = FXCollections.observableArrayList();
    ObservableList<String> Months = FXCollections.observableList(Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"));

    @FXML private ComboBox<String> monthCombo;
    @FXML private ComboBox<appointmentType> typeCombo;
    @FXML private Label appointmentsTotalLabel;
    @FXML private ComboBox<Contact> contactCombo;
    @FXML private ComboBox<Country> countryCombo;
    @FXML private Label customersTotalLabel;
    @FXML private TableView<Appointment> AppointmentTable;
    @FXML private TableColumn<Appointment, Integer> Appointment_ID;
    @FXML private TableColumn<Appointment, String> Title;
    @FXML private TableColumn<Appointment, String> Description;
    @FXML private TableColumn<Appointment, String> Location;
    @FXML private TableColumn<Appointment, Integer> Contact;
    @FXML private TableColumn<Appointment, String> Type;
    @FXML private TableColumn<Appointment, Calendar> Start;
    @FXML private TableColumn<Appointment, Calendar> End;
    @FXML private TableColumn<Appointment, Integer> Customer_ID;
    @FXML private TableColumn<Appointment, Integer> User_ID;

    /**
     * This is the method to display the count of the number of customers per country when the user selects a country from the countryCombo combo box.
     * @param event the user selects a country from the countryCombo combo box
     * @throws SQLException
     */
    @FXML void onActionCountryCombo(ActionEvent event) throws SQLException {
        Country selectedCountry = countryCombo.getValue();
        int selectedCountryID = selectedCountry.getCountry_ID();
        customersTotalLabel.setText(String.valueOf(CustomerDaoImpl.countCustomers(selectedCountryID)));
    }

    /**
     * This method loads the MainMenu screen when the user clicks the Main Menu button from the Reports screen.
     * @param event the user clicks the Main Menu button
     * @throws IOException
     */
    @FXML void onActionMainMenu(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This is the method to display the count of the selected type of appointments in the already selected month when the user selects an appointmentType from the typeCombo combo box.
     * @param event the user selects a type from the typeCombo combo box
     * @throws SQLException
     */
    @FXML void onActionTypeCombo(ActionEvent event) throws SQLException {
        appointmentType selectedType = typeCombo.getValue();
        String selectedMonth = monthCombo.getValue();
        appointmentsTotalLabel.setText(String.valueOf(AppointmentDaoImpl.countMonthType(selectedType, selectedMonth)));
    }

    /**
     * This is the method to display the count of the already selected type of appointments in the selected month when the user selects a month from the monthCombo combo box.
     * @param event the user selects a month from the monthCombo combo box
     * @throws SQLException
     */
    @FXML void onActonMonthCombo(ActionEvent event) throws SQLException {
        appointmentType selectedType = typeCombo.getValue();
        String selectedMonth = monthCombo.getValue();
        appointmentsTotalLabel.setText(String.valueOf(AppointmentDaoImpl.countMonthType(selectedType, selectedMonth)));
    }

    /**
     * This is the method to display the appointments for the selected contact when the user selects a contact from the contactCombo combo box.
     * @param event the user selects a contact from the contactCombo combo box
     */
    @FXML void onActionContactCombo(ActionEvent event) {
        Appointment.clear();
        Contact selectedContact = contactCombo.getValue();
        int selectedContactID = selectedContact.getContact_ID();

        try {
                Appointment.addAll(AppointmentDaoImpl.getAppointmentsContactID(selectedContactID));
        } catch (Exception ex) {
                Logger.getLogger(Appointments.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This is the method to set the combo boxes and set the AppointmentTable from the Appointments table in the database.
     * @param url
     * @param resourceBundle
     */
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
        AppointmentTable.setItems(Appointment);
    }
}