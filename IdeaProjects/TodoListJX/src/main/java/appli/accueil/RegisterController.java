package appli.accueil;

import appli.SceneController;
import appli.model.repository.RepositoryUser;
import appli.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
        int succes_register = RepositoryUser.register(mail_insert.getText(),mdp_insert.getText(),name_insert.getText(), firstname_insert.getText(),
                mdp_confirm_insert.getText());
        User user = RepositoryUser.login(mail_insert.getText(), mdp_insert.getText());
        RepositoryUser.userConnected = user;
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
