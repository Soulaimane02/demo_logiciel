package appli.todolistjx.controllers;

import appli.todolistjx.SceneController;
import appli.todolistjx.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterController {
    @FXML
    private TextField mail_insert;
    @FXML
    private PasswordField mdp_insert;

    @FXML
    private TextField name_insert;

    @FXML
    private TextField firstname_insert;

    @FXML
    private PasswordField mdp_confirm_insert;
    @FXML
    public void register(ActionEvent event) throws SQLException, IOException {
        int succes_register = UserController.register(mail_insert.getText(),mdp_insert.getText(),name_insert.getText(), firstname_insert.getText(),
                mdp_confirm_insert.getText());
        User user = UserController.login(mail_insert.getText(), mdp_insert.getText());
        UserController.userConnected = user;
        if (succes_register == 0)
        {
            SceneController scene = new SceneController();
            scene.switchView("register-view.fxml", event);
        }
        else if (succes_register == 1)
        {
            SceneController scene = new SceneController();
            scene.switchView("home-view.fxml", event);
        }
        else
            System.out.println("c quoi le delire");


    }
}
