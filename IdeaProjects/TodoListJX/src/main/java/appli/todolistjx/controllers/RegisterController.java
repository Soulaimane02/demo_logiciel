package appli.todolistjx.controllers;

import appli.todolistjx.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterController {
    @FXML
    private TextField mail_insert;
    @FXML
    private TextField mdp_insert;

    @FXML
    public void register(ActionEvent event) throws SQLException, IOException {
        UserController.register(mail_insert.getText(),mdp_insert.getText());

        SceneController scene = new SceneController();
        scene.switchView("login-view.fxml", event);

    }
}
