package appli.model.repository;

import appli.model.User;
import appli.database.Database;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class RepositoryUser {
    public static User userConnected;

    public static User login(String email, String password) throws SQLException {
        Connection conn = Database.connectDatabase();
        PreparedStatement req = conn.prepareStatement(
                "SELECT * FROM user WHERE mail = ?"
        );
        req.setString(1, email);
        ResultSet sqlUser = req.executeQuery();
        if (sqlUser.next()) {
            String hashedPassword = sqlUser.getString("password");

            if (BCrypt.checkpw(password, hashedPassword))
            {
                System.out.println("Connexion parfait");
                User user = new User(sqlUser.getInt("id"), sqlUser.getString("mail"), sqlUser.getString("password"));
                return user;
            } else
            {
                System.out.println("Mdp incorrect");
                return null;
            }
        } else
        {
            return null;
        }
    }

    public static int register(String mail, String mdp, String name, String first_name, String confirm_password) throws SQLException, IOException
    {

        if (!Objects.equals(confirm_password, mdp))
        {
            System.out.println("Les mots de passe ne correspondent pas !");
            return 0;
        }
        Connection conn = Database.connectDatabase();
        PreparedStatement req_insert = conn.prepareStatement(
                "INSERT INTO `user`(mail, password, name, first_name) VALUES (?,?,?,?)"
        );
        String hashedPassword = BCrypt.hashpw(mdp, BCrypt.gensalt());

        req_insert.setString(1, mail);
        req_insert.setString(2, hashedPassword);
        req_insert.setString(3, name);
        req_insert.setString(4, first_name);

        req_insert.executeUpdate();
        System.out.println("Inscription parfaite");
        return 1;
    }
}
