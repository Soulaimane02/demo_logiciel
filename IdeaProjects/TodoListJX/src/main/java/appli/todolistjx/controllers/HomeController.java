package appli.todolistjx.controllers;

import appli.todolistjx.SceneController;
import appli.todolistjx.model.Liste;
import appli.todolistjx.model.User;
import appli.todolistjx.repository.repositoryListe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    private static User token;

    @FXML
    private Label label;

    @FXML
    private TableView<Liste> tableauListe;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label.setText("Bienvenue " + UserController.userConnected.getMail() + " !");

        try
        {
            List<Liste> listes = repositoryListe.liste_user(UserController.userConnected.getId());
            tableauListe.getItems().addAll(listes);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        TableColumn<Liste, Integer> colonneID = new TableColumn<>("Id Liste");
        TableColumn<Liste, String> colonneName = new TableColumn<>("Titre");
        TableColumn<Liste, String> colonneTache = new TableColumn<>("Tache");
        TableColumn<Liste, String> colonneDescription = new TableColumn<>("Description");
        TableColumn<Liste, String> colonneCompleted = new TableColumn<>("Avancée");

        colonneID.setCellValueFactory(new PropertyValueFactory<>("id_liste"));
        colonneName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colonneTache.setCellValueFactory(new PropertyValueFactory<>("tache"));
        colonneDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colonneCompleted.setCellValueFactory(new PropertyValueFactory<>("completed"));

        tableauListe.getColumns().addAll(colonneID, colonneName, colonneTache, colonneDescription, colonneCompleted);

        // Aide de'internet et de GPT pour la partie selection
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/appli/todolistjx/edit-view.fxml"));
        Parent root = fxmlLoader.load();

        // Récupérer le contrôleur et passer la liste sélectionnée (GPT)
        EditListeController editListeController = fxmlLoader.getController();
        editListeController.setListeActuelle(liste);

        // Changer de scène pour afficher la page de modification (GPT)
        Stage stage = (Stage) tableauListe.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void AddEditChangeScene(ActionEvent event) throws IOException
    {
        SceneController scene = new SceneController();
        scene.switchView("ajout_edit_view.fxml", event);
    }

    @FXML
    public void Logout(ActionEvent event) throws IOException
    {
        SceneController scene = new SceneController();
        scene.switchView("login-view.fxml", event);
    }
}
