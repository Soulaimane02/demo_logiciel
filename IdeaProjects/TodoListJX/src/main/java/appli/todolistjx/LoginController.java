package appli.todolistjx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LoginController {

    @FXML
    private Label email_input;

    @FXML
    protected void onHelloButtonClick()
    {
        email_input.setText("test");
    }
}