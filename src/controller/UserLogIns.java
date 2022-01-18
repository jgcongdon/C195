package controller;

import DAO.UserDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class UserLogIns implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TableView<User> UserTable;
    @FXML
    private TableColumn<User, Integer> ID;
    @FXML
    private TableColumn<User, String> UserName;
    @FXML
    private TableColumn<User, String> Password;

    @FXML
    void onActionMainMenu(ActionEvent event) throws IOException {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
    }


        ObservableList<User> User = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ID.setCellValueFactory(new PropertyValueFactory<>("userId"));
        // CustomerName.setCellValueFactory(new PropertyValueFactory<>("address"));
        UserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
//       CustomerAddress2.setCellValueFactory(new PropertyValueFactory<>("customerAddress2"));
        Password.setCellValueFactory(new PropertyValueFactory<>("password"));


        try {
            User.addAll(UserDaoImpl.getAllUsers());


        } catch (Exception ex) {
            Logger.getLogger(UserLogIns.class.getName()).log(Level.SEVERE, null, ex);
        }
        UserTable.setItems(User);
        //Using Lambda for efficient selection off a tableview

    }

}
