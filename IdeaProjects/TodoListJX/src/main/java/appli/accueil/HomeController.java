package appli.accueil;

import appli.SceneController;
import appli.model.Liste;
import appli.model.repository.EditListeController;
import appli.model.repository.RepositoryListe;
import appli.model.repository.RepositoryUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private TableView<Liste> tableauListe;

    @FXML
    private TableColumn<Liste, Integer> colonneID;

    @FXML
    private TableColumn<Liste, String> colonneName;

    @FXML
    private TableColumn<Liste, String> colonneTache;

    @FXML
    private TableColumn<Liste, String> colonneDescription;

    @FXML
    private TableColumn<Liste, String> colonneCompleted;

    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<String> filterBox;

    private ObservableList<Liste> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label.setText("Bienvenue " + RepositoryUser.userConnected.getMail() + " !");
        filterBox.getItems().addAll("Tous", "Fini", "Pas Fini");

        try {
            List<Liste> listes = RepositoryListe.liste_user(RepositoryUser.userConnected.getId());
            data.addAll(listes);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        tableauListe.setItems(data);

        colonneID.setCellValueFactory(new PropertyValueFactory<>("id_liste"));
        colonneName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colonneTache.setCellValueFactory(new PropertyValueFactory<>("tache"));
        colonneDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colonneCompleted.setCellValueFactory(new PropertyValueFactory<>("completed"));

        tableauListe.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && tableauListe.getSelectionModel().getSelectedItem() != null) {
                Liste listeSelectionnee = tableauListe.getSelectionModel().getSelectedItem();
                try {
                    ouvrirPageModification(listeSelectionnee);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void ouvrirPageModification(Liste liste) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/appli/edit-view.fxml"));
        Parent root = fxmlLoader.load();

        EditListeController editListeController = fxmlLoader.getController();
        editListeController.setListeActuelle(liste);

        Stage stage = (Stage) tableauListe.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void onSearch(ActionEvent event) {
        String searchText = searchField.getText().toLowerCase();
        tableauListe.setItems(data.filtered(l ->
                l.getName().toLowerCase().contains(searchText) ||
                l.getTache().toLowerCase().contains(searchText) ||
                l.getDescription().toLowerCase().contains(searchText)));
    }


    @FXML
    public void onFilter(ActionEvent event) {
        String filter = filterBox.getValue();
        if (filter == null) return;
        tableauListe.setItems(data.filtered(l -> {
            if ("Fini".equals(filter)) return "Fini".equals(l.getCompleted());
            if ("Pas Fini".equals(filter)) return "Pas Fini".equals(l.getCompleted());
            return true;
        }));
    }
    @FXML
    public void AddEditChangeScene(ActionEvent event) throws IOException {
        SceneController scene = new SceneController();
        scene.switchView("ajout_edit_view.fxml", event);
    }

    @FXML
    public void Logout(ActionEvent event) throws IOException {
        RepositoryUser.userConnected = null;
        SceneController scene = new SceneController();
        scene.switchView("login-view.fxml", event);
    }

    @FXML
    public void sendPasswordResetEmail(ActionEvent event) throws IOException {
        MailController.sendPasswordResetEmail();

        // Afficher un message pour informer l'utilisateur que l'email a été envoyé (GPT)
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Réinitialisation du mot de passe");
        alert.setHeaderText("Demande envoyée");
        alert.setContentText("Un e-mail de réinitialisation de mot de passe a été envoyé. Veuillez vérifier votre boîte de réception.");
        alert.showAndWait();
    }
}
