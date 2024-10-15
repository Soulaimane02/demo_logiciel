package appli.model.repository;

import appli.model.Liste;
import appli.database.Database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RepositoryListe
{
    public static List<Liste> liste_user(int id_users) throws SQLException, IOException
    {
        List<Liste> listes = new ArrayList<>();

        if (id_users == 0)
        {
            System.out.println("Ne jouez pas au hackeur svp !");
            return listes;
        }

        Connection conn = Database.connectDatabase();
        PreparedStatement req_liste = conn.prepareStatement("SELECT * FROM `liste` WHERE ref_users = ?");
        req_liste.setInt(1, id_users);
        ResultSet data = req_liste.executeQuery();

        while (data.next()) {
            int idListe = data.getInt("id_liste");
            String name = data.getString("name");
            String tache = data.getString("tache");
            String description = data.getString("description");
            String completed = data.getString("completed");
            if(Objects.equals(completed, "0"))
            {
                completed = "Pas Fini";
            }
            else
            {
                completed = "Fini";
            }

            Liste liste = new Liste(idListe, name, tache, description, completed);
            listes.add(liste);
        }
        return listes;
    }

    public static void addListe(Liste nouvelleListe, int idUser) throws SQLException, IOException
    {
        Connection conn = Database.connectDatabase();
        PreparedStatement reqInsert = conn.prepareStatement(
                "INSERT INTO `liste` (name, tache, description, completed, ref_users) VALUES (?, ?, ?, ?, ?)"
        );
        reqInsert.setString(1, nouvelleListe.getName());
        reqInsert.setString(2, nouvelleListe.getTache());
        reqInsert.setString(3, nouvelleListe.getDescription());
        reqInsert.setString(4, "0");
        reqInsert.setInt(5, idUser);
        reqInsert.executeUpdate();
        System.out.println("Liste ajoutée!");
    }

    public static void UpdateListe(Liste listeEdit) throws SQLException, IOException
    {
        Connection conn = Database.connectDatabase();
        PreparedStatement reqUpdate = conn.prepareStatement(
                "UPDATE `liste` SET name = ?, tache = ?, description = ?, completed = ? WHERE id_liste = ?"
        );
        reqUpdate.setString(1, listeEdit.getName());
        reqUpdate.setString(2, listeEdit.getTache());
        reqUpdate.setString(3, listeEdit.getDescription());
        reqUpdate.setString(4, listeEdit.getCompleted().equals("Fini") ? "1" : "0");
        reqUpdate.setInt(5, listeEdit.getId_liste());
        reqUpdate.executeUpdate();
        System.out.println("Liste modifiée !");
    }
}
