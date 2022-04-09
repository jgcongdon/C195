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
import java.time.ZoneId;
import java.time.ZonedDateTime;
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

            if (appointmentModifyDatePicker.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("ERROR: The date must not be empty");
                alert.showAndWait();
            }

            else if (appointmentModifyDatePicker.getValue() != null) {

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

                ZonedDateTime startTimeConvert = appointmentStart.atZone(ZoneId.systemDefault());
                ZonedDateTime startTimeEST = startTimeConvert.withZoneSameInstant(ZoneId.of("America/New_York"));
                LocalDateTime LDTstartEST = startTimeEST.toLocalDateTime();
                LocalDateTime bStart = LocalDateTime.of(appointmentModifyDatePicker.getValue(), LocalTime.of(8, 0));

                ZonedDateTime endTimeConvert = appointmentEnd.atZone(ZoneId.systemDefault());
                ZonedDateTime endTimeEST = endTimeConvert.withZoneSameInstant(ZoneId.of("America/New_York"));
                LocalDateTime LDTendEST = endTimeEST.toLocalDateTime();
                LocalDateTime bEnd = LocalDateTime.of(appointmentModifyDatePicker.getValue(), LocalTime.of(22, 0));

                if (appointmentTitle.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setContentText("ERROR: The title must not be empty");
                    alert.showAndWait();
                } else if (appointmentDescription.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setContentText("ERROR: The description must not be empty");
                    alert.showAndWait();
                } else if (appointmentLocation.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setContentText("ERROR: The location must not be empty");
                    alert.showAndWait();
                } else if (appointmentModifyContactCombo.getSelectionModel().getSelectedItem() == null) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setContentText("ERROR: The contact must not be empty");
                    alert.showAndWait();
                } else if (appointmentType.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setContentText("ERROR: The type must not be empty");
                    alert.showAndWait();
                } else if (appointmentModifyStartCombo.getSelectionModel().getSelectedItem() == null) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setContentText("ERROR: The start time must not be empty");
                    alert.showAndWait();
                } else if (appointmentModifyEndCombo.getSelectionModel().getSelectedItem() == null) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setContentText("ERROR: The end time must not be empty");
                    alert.showAndWait();
                } else if (appointmentModifyCustomerIDCombo.getSelectionModel().getSelectedItem() == null) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setContentText("ERROR: The customer must not be empty");
                    alert.showAndWait();
                } else if (appointmentModifyUserIDCombo.getSelectionModel().getSelectedItem() == null) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setContentText("ERROR: The user must not be empty");
                    alert.showAndWait();
                } else if (LDTstartEST.isBefore(bStart)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setContentText("ERROR: Appointment start time is too early!");
                    alert.showAndWait();
                } else if (LDTendEST.isAfter(bEnd)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setContentText("ERROR: Appointment end time is too late!");
                    alert.showAndWait();
                    return;
                } else {

                    AppointmentDaoImpl.modifyAppointment(appointmentID, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStart, appointmentEnd, lastUpdate, lastUpdateBy, customerID, userID, contactID);

                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();

                }
            }
            } catch (Exception e) {
            e.printStackTrace();
            return;
        }
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

        LocalTime startStart = LocalTime.of(5,0);
        LocalTime startEnd = LocalTime.of(21,45);
        LocalTime endStart = LocalTime.of(5,15);
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
