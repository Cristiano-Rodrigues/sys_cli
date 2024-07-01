package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    private static Connection connection = null;

    public static Connection getConnection () {
        if (connection != null) {
            return connection;
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost/sys_cli";
            String username = "root";
            String password = "";

            connection = DriverManager.getConnection(url, username, password);

            return connection;
        } catch (ClassNotFoundException e) {
            System.out.println("Conector nao encontrado.");
            return null;
        } catch (SQLException e) {
            System.out.println("Conexao nao foi bem sucedida");
            return null;
        }
    }

    public static boolean closeConnection () {
        try {
            connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }

    }
}