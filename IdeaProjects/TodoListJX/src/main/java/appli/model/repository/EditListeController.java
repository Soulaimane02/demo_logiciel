package appli.model.repository;

import appli.SceneController;
import appli.model.Liste;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
public class EditListeController
{
    @FXML
    private TextField titreField;

    @FXML
    private TextField tacheField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private ComboBox<String> avanceeCombo;

    private Liste listeActuelle;

    @FXML
    public void initialize() {
        // Ajouter les options pour le comboBox de l'avancée (GPT)
        avanceeCombo.getItems().addAll("Pas Fini", "Fini");
    }

    public void setListeActuelle(Liste liste)
    {
        this.listeActuelle = liste;

        titreField.setText(liste.getName());
        tacheField.setText(liste.getTache());
        descriptionField.setText(liste.getDescription());
        avanceeCombo.setValue(liste.getCompleted());
    }

    @FXML
    public void enregistrerListe()
    {
        String titre = titreField.getText();
        String tache = tacheField.getText();
        String description = descriptionField.getText();
        String avancee = avanceeCombo.getValue();

        try {
            if (listeActuelle == null)
            {
                Liste nouvelleListe = new Liste(0, titre, tache, description, avancee);
                RepositoryListe.addListe(nouvelleListe, RepositoryUser.userConnected.getId());
            } else
            {
                listeActuelle.setName(titre);
                listeActuelle.setTache(tache);
                listeActuelle.setDescription(description);
                listeActuelle.setCompleted(avancee);
                RepositoryListe.UpdateListe(listeActuelle);
            }

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/appli/home-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) titreField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void annuler(ActionEvent event) throws IOException
    {
        SceneController scene = new SceneController();
        scene.switchView("home-view.fxml", event);
    }
}

