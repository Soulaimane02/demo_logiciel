package appli.todolistjx.controllers;

import appli.todolistjx.model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable{
    private static User token;
    @FXML
    private Label label;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label.setText(UserController.userConnected.getMail());
    }
}
