package appli.todolistjx.controllers;

import appli.todolistjx.SceneController;
import appli.todolistjx.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField mail_input;
    @FXML
    private TextField mdp_input;



    @FXML
    public void login(ActionEvent event) throws SQLException, IOException{
        User user = UserController.login(mail_input.getText(), mdp_input.getText());
        if(user != null)
        {
            UserController.userConnected = user;
            SceneController scene = new SceneController();
            scene.switchView("home-view.fxml", event);
        }
    }

    @FXML
    public void RegisterButtonAction(ActionEvent event) throws IOException{
        SceneController scence = new SceneController();
        scence.switchView("register-view.fxml", event);
    }

}