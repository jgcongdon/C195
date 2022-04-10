package controller;

import DAO.AppointmentDaoImpl;
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
import model.Appointment;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Appointments implements Initializable {

    Stage stage;
    Parent scene;

    ObservableList<model.Appointment> Appointment = FXCollections.observableArrayList();

    @FXML
    private RadioButton weekRadioButton;

    @FXML
    private RadioButton monthRadioButton;

    @FXML
    private RadioButton allRadioButton;

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

    public static boolean checkOverlap(int customerID, int appointmentID, LocalDateTime appointmentStart, LocalDateTime appointmentEnd) throws Exception {
        ObservableList<model.Appointment> aList = AppointmentDaoImpl.getAllAppointments();
        for (Appointment a:aList){
            if (customerID != a.getCustomer_ID()){
                continue;
            }
            if (appointmentID == a.getAppointment_ID()){
                continue;
            }



        }
        return false;
    }

    @FXML
    void onActionAddAppointment(ActionEvent event) throws IOException {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/AppointmentsAdd.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
    }

    @FXML
    void onActionDeleteAppointment(ActionEvent event) throws Exception {

        if (AppointmentTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("ERROR: No appointment selected");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this appointment?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

                AppointmentDaoImpl.deleteAppointment(AppointmentTable.getSelectionModel().getSelectedItem().getAppointment_ID());
                Appointment = AppointmentDaoImpl.getAllAppointments();
                AppointmentTable.setItems(Appointment);
                AppointmentTable.refresh();
            }
            else {
                Appointment = AppointmentDaoImpl.getAllAppointments();
                AppointmentTable.setItems(Appointment);
                AppointmentTable.refresh();
            }


        }
    }

    @FXML
    void onActionMainMenu(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionModifyAppointment(ActionEvent event) throws IOException {

        if (AppointmentTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("ERROR: No appointment selected");
            alert.showAndWait();
        }
        else {

            AppointmentsModify.receiveSelectedAppointment(AppointmentTable.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/AppointmentsModify.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    @FXML
    void onActionAll(ActionEvent event) {
        weekRadioButton.setSelected(false);
        monthRadioButton.setSelected(false);


    }

    @FXML
    void onActionMonth(ActionEvent event) {
        allRadioButton.setSelected(false);
        weekRadioButton.setSelected(false);

    }

    @FXML
    void onActionWeek(ActionEvent event) {
        allRadioButton.setSelected(false);
        monthRadioButton.setSelected(false);

    }

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
            Appointment.addAll(AppointmentDaoImpl.getAllAppointments());

        } catch (Exception ex) {
            Logger.getLogger(Appointments.class.getName()).log(Level.SEVERE, null, ex);
        }

        AppointmentTable.setItems(Appointment);
    }
}
