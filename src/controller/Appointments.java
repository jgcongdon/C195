package controller;

import DAO.AppointmentDaoImpl;
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
import model.Appointment;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class creates the Appointments controller.
 * @author Jackson Congdon
 */
public class Appointments implements Initializable {

    Stage stage;
    Parent scene;
    ObservableList<model.Appointment> Appointments = FXCollections.observableArrayList();

    @FXML private RadioButton weekRadioButton;
    @FXML private RadioButton monthRadioButton;
    @FXML private RadioButton allRadioButton;
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
     * This is the method to check for an appointment overlap. The method checks the various combinations of overlaps and returns true if a condition is met. The method returns false if no overlaps are found.
     * @param customerID the customer ID to check overlaps for
     * @param appointmentID the appointment ID being created
     * @param appointmentStart the start time of the appointment being created
     * @param appointmentEnd the end time of the appointment being created
     * @return true if appointment overlap found, false if no overlap
     * @throws Exception
     */
    public static boolean checkOverlap(int customerID, int appointmentID, LocalDateTime appointmentStart, LocalDateTime appointmentEnd) throws Exception {
        ObservableList<Appointment> aList = AppointmentDaoImpl.getAllAppointments();
        LocalDateTime checkApptStart;
        LocalDateTime checkApptEnd;

        for (Appointment a : aList) {
            checkApptStart = a.getStart();
            checkApptEnd = a.getEnd();
            if (customerID != a.getCustomer_ID()) {
               continue;
            }
            if (appointmentID == a.getAppointment_ID()) {
                continue;
            } else if (checkApptStart.isEqual(appointmentStart) || checkApptStart.isEqual(appointmentEnd) || checkApptEnd.isEqual(appointmentStart) || checkApptEnd.isEqual(appointmentEnd)){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("ERROR: Appointments must not start or end at same time as existing customer appointments");
                alert.showAndWait();
                return true;
            } else if (appointmentStart.isAfter(checkApptStart) && (appointmentStart.isBefore(checkApptEnd))){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("ERROR: Appointment start must not be during existing customer appointments");
                alert.showAndWait();
                return true;
            } else if (appointmentEnd.isAfter(checkApptStart) && appointmentEnd.isBefore(checkApptEnd)){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("ERROR: Appointment end must not be during existing customer appointments");
                alert.showAndWait();
                return true;
            }
        }
        return false;
    }

    /**
     * This method loads the AppointmentsAdd screen when the user clicks the Add button from the Appointments screen.
     * @param event user clicks the Add button from the Appointments screen
     * @throws IOException
     */
    @FXML void onActionAddAppointment(ActionEvent event) throws IOException {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/AppointmentsAdd.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
    }

    /**
     * This method to delete an appointment when the user clicks the Delete button from the Appointments screen.
     * @param event user clicks the Delete button from the Appointments screen
     * @throws Exception
     */
    @FXML void onActionDeleteAppointment(ActionEvent event) throws Exception {
        if (AppointmentTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("ERROR: No appointment selected");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this appointment?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                int deletedApptID = AppointmentTable.getSelectionModel().getSelectedItem().getAppointment_ID();
                String deletedApptType = AppointmentTable.getSelectionModel().getSelectedItem().getType();
                AppointmentDaoImpl.deleteAppointment(AppointmentTable.getSelectionModel().getSelectedItem().getAppointment_ID());

                Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setTitle("Warning Dialog");
                alert2.setContentText("Appointment ID # " + deletedApptID + " of type " + deletedApptType + " successfully deleted.");
                alert2.showAndWait();

                Appointments = AppointmentDaoImpl.getAllAppointments();
                AppointmentTable.setItems(Appointments);
                AppointmentTable.refresh();
            } else {
                Appointments = AppointmentDaoImpl.getAllAppointments();
                AppointmentTable.setItems(Appointments);
                AppointmentTable.refresh();
            }
        }
    }

    /**
     * This method loads the MainMenu screen when the user clicks the Main Menu button from the Appointments screen.
     * @param event the user clicks the Main Menu button on the Appointments screen
     * @throws IOException
     */
    @FXML
    void onActionMainMenu(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This method loads the AppointmentsModify screen when the user clicks the Modify button from the Appointments screen.
     * @param event the user clicks the Modify button on the Appointments screen
     * @throws IOException
     */
    @FXML void onActionModifyAppointment(ActionEvent event) throws IOException {
        if (AppointmentTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("ERROR: No appointment selected");
            alert.showAndWait();
        } else {
            AppointmentsModify.receiveSelectedAppointment(AppointmentTable.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/AppointmentsModify.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**
     * This is the method to change the AppointmentTable view to all appointments when the user clicks the All radio button on the Appointments screen.
     * @param event the user clicks the All radio button on the Appointments screen
     * @throws Exception
     */
    @FXML void onActionAll(ActionEvent event) throws Exception {
        weekRadioButton.setSelected(false);
        monthRadioButton.setSelected(false);
        Appointments.clear();
        Appointments.addAll(AppointmentDaoImpl.getAllAppointments());
    }

    /**
     * This is the method to change the AppointmentTable view to only the current month when the user clicks the Month radio button on the Appointments screen.
     * @param event the user clicks the Month radio button on the Appointments screen
     * @throws Exception
     */
    @FXML void onActionMonth(ActionEvent event) throws Exception {
        allRadioButton.setSelected(false);
        weekRadioButton.setSelected(false);
        Appointments.clear();
        Appointments.addAll(AppointmentDaoImpl.getCurrentMonthAppointments());
    }

    /**
     * This is the method to change the AppointmentTable view to only the current week when the user clicks the Week radio button on the Appointments screen.
     * @param event the user clicks the Week radio button on the Appointments screen
     * @throws Exception
     */
    @FXML void onActionWeek(ActionEvent event) throws Exception {
        allRadioButton.setSelected(false);
        monthRadioButton.setSelected(false);
        Appointments.clear();
        Appointments.addAll(AppointmentDaoImpl.getCurrentWeekAppointments());
    }

    /**
     * This is the method to set AppointmentsTable and default select the All radio button.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

        try {
            Appointments.addAll(AppointmentDaoImpl.getAllAppointments());

        } catch (Exception ex) {
            Logger.getLogger(Appointments.class.getName()).log(Level.SEVERE, null, ex);
        }

        AppointmentTable.setItems(Appointments);
        allRadioButton.setSelected(true);
    }
}
