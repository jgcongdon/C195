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
import model.Contact;
import model.Customer;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.*;
import java.util.ResourceBundle;

public class AppointmentsAdd implements Initializable {

    Stage stage;
    Parent scene;

    //private ZonedDateTime startTimeConvert;
    private ZonedDateTime localnow;

    public ComboBox<User> userCombo;
    public ComboBox<Customer> customerCombo;
    public ComboBox<Contact> contactCombo;
    public ComboBox<LocalTime> startCombo;
    public ComboBox<LocalTime> endCombo;

    @FXML
    private Label appointmentAddIDLabel;

    @FXML
    private TextField appointmentAddLocationLabel;

    @FXML
    private TextField appointmentAddTitleLabel;

    @FXML
    private TextField appointmentAddDescriptionLabel;

    @FXML
    private TextField appointmentAddTypeLabel;

    @FXML
    private DatePicker appointmentAddDatePicker;

    @FXML
    private Label appointmentAddIUserIDLabel;

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionSave(ActionEvent event) throws IOException {

        try{
            String appointmentTitle = appointmentAddTitleLabel.getText();
            String appointmentDescription = appointmentAddDescriptionLabel.getText();
            String appointmentLocation = appointmentAddLocationLabel.getText();
            String appointmentType = appointmentAddTypeLabel.getText();
            LocalDateTime appointmentStart = LocalDateTime.of(appointmentAddDatePicker.getValue(), startCombo.getSelectionModel().getSelectedItem());
            LocalDateTime appointmentEnd = LocalDateTime.of(appointmentAddDatePicker.getValue(), endCombo.getSelectionModel().getSelectedItem());
            Timestamp createDate = Timestamp.valueOf(LocalDateTime.now());
            String createdBy = Globals.userName;
            Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());
            String lastUpdateBy = Globals.userName;
            int customerID = customerCombo.getValue().getCustomerId();
            int userID = userCombo.getValue().getUserId();
            int contactID = contactCombo.getValue().getContact_ID();

            System.out.println(appointmentStart);

            ZonedDateTime startTimeConvert = appointmentStart.atZone(ZoneId.systemDefault());
            ZonedDateTime startTimeEST = startTimeConvert.withZoneSameInstant(ZoneId.of("America/New_York"));
            LocalDateTime LDTstartEST = startTimeEST.toLocalDateTime();
            LocalDateTime bStart = LocalDateTime.of(appointmentAddDatePicker.getValue(), LocalTime.of(8,0));
            if (LDTstartEST.isBefore(bStart)){
                System.out.println("bus hours prob");
            return;
            }


            //startTimeConvert = ZonedDateTime.of(appointmentStart, ZoneId.of("America/New_York"));

            System.out.println(startTimeEST);

            //localnow = ZonedDateTime.now(ZoneId.systemDefault());
            //System.out.println(localnow);

            //AppointmentDaoImpl.addAppointment(appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStart, appointmentEnd, createDate, createdBy, lastUpdate, lastUpdateBy, customerID, userID, contactID);



        } catch (Exception e){
            e.printStackTrace();
            return;
        }


        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            userCombo.setItems(UserDaoImpl.getAllUsers());
            customerCombo.setItems(CustomerDaoImpl.getAllCustomers());
            contactCombo.setItems(ContactDaoImpl.getAllContacts());
        } catch (Exception e) {
            e.printStackTrace();
        }



        LocalTime startStart = LocalTime.of(5,0);
        LocalTime startEnd = LocalTime.of(21,45);
        LocalTime endStart = LocalTime.of(8,15);
        LocalTime endEnd = LocalTime.of(22,0);

        while(startStart.isBefore(startEnd.plusSeconds(1))){
            startCombo.getItems().add(startStart);
            startStart = startStart.plusMinutes(15);

            while(endStart.isBefore(endEnd.plusSeconds(1))){
                endCombo.getItems().add(endStart);
                endStart = endStart.plusMinutes(15);
            }
        }
    }
}
