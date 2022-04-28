package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class creates the MainMenu controller.
 * @author Jackson Congdon
 */
public class MainMenu implements Initializable {

    Stage stage;
    Parent scene;

    /**
     *
     * @param event the user clicks the Appointments button
     * @throws IOException
     */
    @FXML void onActionAppointments(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     *
     * @param event the user clicks the Customers button
     * @throws IOException
     */
    @FXML void onActionCustomers(ActionEvent event) throws IOException {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/Customers.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
    }

    /**
     *
     * @param event The user clicks the Reports button
     * @throws IOException
     */
    @FXML void onActionReports(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Reports.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     *
     * @param event The user clicks the Log Out button
     * @throws IOException
     */
    @FXML void onActionLogOut(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/LogIn.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}