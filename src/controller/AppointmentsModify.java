package controller;

import DAO.AppointmentDaoImpl;
import DAO.ContactDaoImpl;
import DAO.CustomerDaoImpl;
import DAO.UserDaoImpl;
import helper.Globals;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;
import model.Customer;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AppointmentsModify implements Initializable {

    Stage stage;
    Parent scene;

    private static Appointment selectedAppointment;

    @FXML
    private Label appointmentModifyIDLabel;

    @FXML
    private ComboBox<Contact> appointmentModifyContactCombo;

    @FXML
    private TextField appointmentModifyLocationLabel;

    @FXML
    private TextField appointmentModifyTitleLabel;

    @FXML
    private TextField appointmentModifyDescriptionLabel;

    @FXML
    private TextField appointmentModifyTypeLabel;

    @FXML
    private DatePicker appointmentModifyDatePicker;

    @FXML
    private ComboBox<LocalTime> appointmentModifyStartCombo;

    @FXML
    private ComboBox<LocalTime> appointmentModifyEndCombo;

    @FXML
    private ComboBox<Customer> appointmentModifyCustomerIDCombo;

    @FXML
    private ComboBox<User> appointmentModifyUserIDCombo;


    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionSave(ActionEvent event) throws IOException {

        try {
            int appointmentID = Integer.parseInt(appointmentModifyIDLabel.getText());
            String appointmentTitle = appointmentModifyTitleLabel.getText();
            String appointmentDescription = appointmentModifyDescriptionLabel.getText();
            String appointmentLocation = appointmentModifyLocationLabel.getText();
            String appointmentType = appointmentModifyTypeLabel.getText();
            LocalDateTime appointmentStart = LocalDateTime.of(appointmentModifyDatePicker.getValue(), appointmentModifyStartCombo.getSelectionModel().getSelectedItem());
            LocalDateTime appointmentEnd = LocalDateTime.of(appointmentModifyDatePicker.getValue(), appointmentModifyEndCombo.getSelectionModel().getSelectedItem());
            Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());
            String lastUpdateBy = Globals.userName;
            int customerID = appointmentModifyCustomerIDCombo.getValue().getCustomerId();
            int userID = appointmentModifyUserIDCombo.getValue().getUserId();
            int contactID = appointmentModifyContactCombo.getValue().getContact_ID();

            AppointmentDaoImpl.modifyAppointment(appointmentID, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStart, appointmentEnd, lastUpdate, lastUpdateBy, customerID, userID, contactID);

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    public static void receiveSelectedAppointment(Appointment appointment){
        selectedAppointment = appointment;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try{
            appointmentModifyContactCombo.setValue(ContactDaoImpl.getContactFromContactID(selectedAppointment.getContact_ID()));
            appointmentModifyContactCombo.setItems(ContactDaoImpl.getAllContacts());
            appointmentModifyCustomerIDCombo.setValue(CustomerDaoImpl.getCustomerFromCustomerID(selectedAppointment.getCustomer_ID()));
            appointmentModifyCustomerIDCombo.setItems(CustomerDaoImpl.getAllCustomers());
            appointmentModifyUserIDCombo.setValue(UserDaoImpl.getUserFromUserID(selectedAppointment.getUser_ID()));
            appointmentModifyUserIDCombo.setItems(UserDaoImpl.getAllUsers());

        } catch (Exception throwables) {
        throwables.printStackTrace();
        }

        LocalTime startStart = LocalTime.of(8,0);
        LocalTime startEnd = LocalTime.of(21,45);
        LocalTime endStart = LocalTime.of(8,15);
        LocalTime endEnd = LocalTime.of(22,0);

        while(startStart.isBefore(startEnd.plusSeconds(1))){
            appointmentModifyStartCombo.getItems().add(startStart);
            startStart = startStart.plusMinutes(15);

            while(endStart.isBefore(endEnd.plusSeconds(1))){
                appointmentModifyEndCombo.getItems().add(endStart);
                endStart = endStart.plusMinutes(15);
            }
        }

        appointmentModifyIDLabel.setText(Integer.toString(selectedAppointment.getAppointment_ID()));
        appointmentModifyTitleLabel.setText(selectedAppointment.getTitle());
        appointmentModifyDescriptionLabel.setText(selectedAppointment.getDescription());
        appointmentModifyLocationLabel.setText(selectedAppointment.getLocation());
        appointmentModifyTypeLabel.setText(selectedAppointment.getType());
        appointmentModifyDatePicker.setValue(selectedAppointment.getStart().toLocalDate());
        appointmentModifyStartCombo.setValue(selectedAppointment.getStart().toLocalTime());
        appointmentModifyEndCombo.setValue(selectedAppointment.getEnd().toLocalTime());



    }
}
