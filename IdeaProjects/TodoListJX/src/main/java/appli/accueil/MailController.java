package appli.accueil;

import appli.model.repository.RepositoryUser;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Base64;


// Aide GPT
public class MailController {

    public static void sendPasswordResetEmail() {
        try {
            String userEmail = RepositoryUser.userConnected.getMail();

            if (userEmail == null || userEmail.isEmpty()) {
                System.out.println("Aucun utilisateur connecté");
                return;
            }

            // Connexion à l'API Mailjet
            URI uri = new URI("https://api.mailjet.com/v3.1/send");
            URL url = uri.toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            String apiKey = "bd1e534fe6806cbeecc5175d7ddf6223";
            String secretKey = "a9d272be201d1a8c43873cfe7db06fa0";
            String credentials = apiKey + ":" + secretKey;
            String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

            conn.setRequestProperty("Authorization", "Basic " + encodedCredentials);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Création du JSON pour la requête
            JSONObject emailData = new JSONObject();
            JSONArray messages = new JSONArray();

            JSONObject message = new JSONObject();
            message.put("From", new JSONObject()
                    .put("Email", "zelloufi.soulaimane@gmail.com")
                    .put("Name", "Admin"));
            message.put("To", new JSONArray()
                    .put(new JSONObject()
                            .put("Email", userEmail)
                            .put("Name", "User")));
            message.put("Subject", "Demande de réinitialisation de mot de passe");
            message.put("TextPart", "Bonjour,\n\nVous avez demandé une réinitialisation de mot de passe. "
                    + "Veuillez attendre que notre équipe prenne en charge votre demande.\n\nCordialement,\nL'équipe.");

            // Ajout du message au tableau de messages
            messages.put(message);

            // Mise en place du corps JSON pour l'envoi
            emailData.put("Messages", messages);

            // Envoi de la requête
            OutputStream os = conn.getOutputStream();
            os.write(emailData.toString().getBytes());
            os.flush();

            // Vérification de la réponse
            if (conn.getResponseCode() == 200) {
                System.out.println("E-mail envoyé !");
            } else {
                System.out.println("Erreur lors de l'envoi de l'e-mail. Code de réponse : " + conn.getResponseCode());
            }
        } catch (Exception e) {
        }
    }
}