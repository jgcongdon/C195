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
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Customers implements Initializable {

    Stage stage;
    Parent scene;

    ObservableList<model.Customer> CustomerList = FXCollections.observableArrayList();

    /*private static Customer selectedCustomer;

    public static void sendSelectedCustomer(Customer customer){
        selectedCustomer = customer;
    }*/

    @FXML
    private TableView<Customer> CustomerTable;

    @FXML
    private TableColumn<Customer, Integer> Customer_ID;

    @FXML
    private TableColumn<Customer, String> Customer_Name;

    @FXML
    private TableColumn<Customer, String> Address;

    @FXML
    private TableColumn<Customer, String> Postal_Code;

    @FXML
    private TableColumn<Customer, String> Phone_Number;

    @FXML
    private TableColumn<Customer, Calendar> Create_Date;

    @FXML
    private TableColumn<Customer, String> Create_By;

    @FXML
    private TableColumn<Customer, Calendar> Last_Update;

    @FXML
    private TableColumn<Customer, String> Last_Updated_By;

    @FXML
    private TableColumn<Customer, Integer> Division_ID;

    @FXML
    void onActionAddCustomer(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/CustomersAdd.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public static boolean checkCustAppt(int customerID) throws Exception {
        ObservableList<Appointment> aList = AppointmentDaoImpl.getAppointmentsCustomerID(customerID);
        if (aList.size()>0){
            return true;
        }
        else{
            return false;
        }
    }

    @FXML
    void onActionDeleteCustomer(ActionEvent event) throws Exception {

        //int customerID = CustomerTable.getSelectionModel().getSelectedItem().getCustomerId();

        if (CustomerTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("ERROR: No customer selected");
            alert.showAndWait();

        } /*else if (checkCustAppt(customerID) == true){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("ERROR: Must delete all customer appointments first");
            alert.showAndWait();

            /*ObservableList<Appointment> aList = AppointmentDaoImpl.getAllAppointments();
            for (Appointment a : aList) {
            }*/

        //}

        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this customer?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                ObservableList<Appointment> aList = AppointmentDaoImpl.getAllAppointments();
                for (Appointment a : aList) {
                    if (a.getCustomer_ID() == CustomerTable.getSelectionModel().getSelectedItem().getCustomerId()) {
                        Alert alertAppt = new Alert(Alert.AlertType.CONFIRMATION, "WARNING: Must delete all appointments first. \n \n Are you sure you want to delete Appointment ID " + a.getAppointment_ID() + "?");
                        Optional<ButtonType> resultAppt = alertAppt.showAndWait();
                        if (resultAppt.isPresent() && resultAppt.get() == ButtonType.OK) {
                            AppointmentDaoImpl.deleteApptCustID(CustomerTable.getSelectionModel().getSelectedItem().getCustomerId(), a.getAppointment_ID());
                        }
                    }
                }
                if (AppointmentDaoImpl.countCustAppt(CustomerTable.getSelectionModel().getSelectedItem().getCustomerId()) > 0){
                    Alert alert3 = new Alert(Alert.AlertType.WARNING);
                    alert3.setTitle("Warning Dialog");
                    alert3.setContentText("ERROR: Must delete all appointments first before deleting customer");
                    alert3.showAndWait();
                }
                else {
                    String deletedName = CustomerTable.getSelectionModel().getSelectedItem().getCustomerName();
                    CustomerDaoImpl.deleteCustomer(CustomerTable.getSelectionModel().getSelectedItem().getCustomerId());

                    Alert alert3 = new Alert(Alert.AlertType.WARNING);
                    alert3.setTitle("Warning Dialog");
                    alert3.setContentText(deletedName + " successfully deleted.");
                    alert3.showAndWait();

                    CustomerList = CustomerDaoImpl.getAllCustomers();
                    CustomerTable.setItems(CustomerList);
                    CustomerTable.refresh();
                }
            } else {
                CustomerList = CustomerDaoImpl.getAllCustomers();
                CustomerTable.setItems(CustomerList);
                CustomerTable.refresh();
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
    void onActionModifyCustomer(ActionEvent event) throws IOException {

        if (CustomerTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("ERROR: No customer selected");
            alert.showAndWait();

        } else {

            CustomersModify.receiveSelectedCustomer(CustomerTable.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/CustomersModify.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Customer_ID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        Customer_Name.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        Address.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        Postal_Code.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        Phone_Number.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        Create_Date.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        Create_By.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        Last_Update.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        Last_Updated_By.setCellValueFactory(new PropertyValueFactory<>("lastUpdateBy"));
        Division_ID.setCellValueFactory(new PropertyValueFactory<>("Division_ID"));

        try {
            CustomerList.addAll(CustomerDaoImpl.getAllCustomers());
        }

        catch (Exception ex) {
            Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, ex);
        }

        CustomerTable.setItems(CustomerList);

    }
}
