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
import model.User;

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

    @FXML
    private TextField logInUsernameLabel;

    @FXML
    private PasswordField logInPasswordLabel;

    @FXML
    private Label locationLabel;

    @FXML
    void onActionExit(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            JDBC.closeConnection();
            System.exit(0);
        }
    }

    @FXML
    void onActionLogIn(ActionEvent event) throws Exception {
        String userName = logInUsernameLabel.getText();
        String password = logInPasswordLabel.getText();
        User userResult = UserDaoImpl.getUser(userName);

        if (userName.isEmpty() || userName.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("ERROR: Invalid login");
            alert.showAndWait();
        }

        else if (UserDaoImpl.validateLogIn(userResult.getUserName(), password ) == true) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/MainMenu.fxml"));
            loader.load();

            String activeUser = userResult.getUserName();
            MainMenu MMController = loader.getController();
            MMController.sendUser(activeUser);

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();


        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("ERROR: Invalid login");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        locationLabel.setText(tz1);

    }
}
