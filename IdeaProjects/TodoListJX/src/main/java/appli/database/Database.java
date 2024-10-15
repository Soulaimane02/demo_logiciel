package appli.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static Connection connectDatabase() throws SQLException {
        Connection maConnection = DriverManager.getConnection( "jdbc:mysql://localhost:8889/szi_todoListFx","root","php");
        return maConnection;
    }
}
