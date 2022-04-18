package controller;

import DAO.UserDaoImpl;
import helper.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointment;
import model.User;

import java.sql.SQLException;
import java.util.TimeZone;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class LogIn implements Initializable {
    Stage stage;
    Parent scene;

    TimeZone tz = TimeZone.getDefault();
    String tz1 = tz.getID();

    ResourceBundle myBundle = ResourceBundle.getBundle("bundle/lang");

    @FXML
    private TextField logInUsernameLabel;

    @FXML
    private PasswordField logInPasswordLabel;

    @FXML
    private Label userNameLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label locationText;

    @FXML
    private Label locationLabel;

    @FXML
    private Button logInButton;

    @FXML
    private Button exitButton;

    @FXML
    void onActionExit(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, myBundle.getString("Exit"));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            JDBC.closeConnection();
            System.exit(0);
        }
    }

    @FXML
    void onActionLogIn(ActionEvent event) throws Exception, SQLException {
        String User_Name = logInUsernameLabel.getText();
        String Password = logInPasswordLabel.getText();
        User userResult = UserDaoImpl.getUser(User_Name);

        if (User_Name.isEmpty() || User_Name.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(myBundle.getString("WarningDialog"));
            alert.setContentText(myBundle.getString("ERROR"));
            alert.showAndWait();
        }

        else if (Password.isEmpty() || Password.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(myBundle.getString("WarningDialog"));
            alert.setContentText(myBundle.getString("ERROR"));
            alert.showAndWait();
        }

        else if (UserDaoImpl.validateUserName(User_Name) == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(myBundle.getString("WarningDialog"));
            alert.setContentText(myBundle.getString("ERROR"));
            alert.showAndWait();
        }

        else if (UserDaoImpl.validatePassword(Password) == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(myBundle.getString("WarningDialog"));
            alert.setContentText(myBundle.getString("ERROR"));
            alert.showAndWait();
        }

        else if (UserDaoImpl.validateLogIn(User_Name, Password) == true) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/MainMenu.fxml"));
            loader.load();

            Globals.userName = userResult.getUserName();

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();

        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(myBundle.getString("WarningDialog"));
            alert.setContentText(myBundle.getString("ERROR"));
            alert.showAndWait();
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        locationLabel.setText(tz1);

        userNameLabel.setText(myBundle.getString("Username"));
        passwordLabel.setText(myBundle.getString("Password"));
        locationText.setText(myBundle.getString("Location"));
        logInButton.setText(myBundle.getString("Login"));
        exitButton.setText(myBundle.getString("ExitButton"));

    }
}
