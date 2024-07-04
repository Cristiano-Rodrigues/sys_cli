package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;

public class MySQLConnection {
    private static Connection connection = null;

    public static Connection getConnection () {
        if (connection != null) {
            return connection;
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Dotenv dotenv = Dotenv.load();

            String url = dotenv.get("DB_URL");
            String username = dotenv.get("DB_USER");
            String password = dotenv.get("DB_PASS");

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
            connection = null;
            return true;
        } catch (SQLException e) {
            return false;
        }

    }
}