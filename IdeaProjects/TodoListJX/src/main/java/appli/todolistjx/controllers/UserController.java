package appli.todolistjx.controllers;

import appli.todolistjx.model.User;
import appli.todolistjx.utils.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
import java.util.Objects;

public class UserController {
    public static User userConnected;

    public static User login(String email, String password) throws SQLException {
        Connection conn = Database.connectDatabase();
        PreparedStatement req = conn.prepareStatement(
                "SELECT * FROM user WHERE mail = ? AND password = ?"
        );
        req.setString(1, email);
        req.setString(2, password);
        ResultSet sqlUser = req.executeQuery();
        while(sqlUser.next()){
            if(Objects.equals(email, sqlUser.getString("mail")) && Objects.equals(password, sqlUser.getString("password"))){
                System.out.println("bon");
                User user = new User(sqlUser.getInt("id"), sqlUser.getString("mail"), sqlUser.getString("password"));
                return user;
            }
            else{
                System.out.println("Mdp ou mail incorrect");
                return null;
            }
        }
        return null;
    }
    public static void register(String mail, String mdp) throws SQLException {
        Connection conn = Database.connectDatabase();
        PreparedStatement req_insert = conn.prepareStatement(
                "INSERT INTO `user`( `mail`, `password`) VALUES (?,?)"
        );
        req_insert.setString(1,mail);
        req_insert.setString(2,mdp);
        req_insert.executeUpdate();


    }
}
