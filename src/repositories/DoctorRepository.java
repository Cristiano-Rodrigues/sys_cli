package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DoctorRepository {
    private Connection conn = null;

    public DoctorRepository (Connection conn) {
        this.conn = conn;
    }

    public void create (int employeeId, String crm) {
        try {
            String sql = "INSERT INTO medico (id, numero) VALUES (?, ?)";
            PreparedStatement stmt = this.conn.prepareStatement(sql);

            stmt.setInt(1, employeeId);
            stmt.setString(2, crm);

            stmt.executeUpdate();

            System.out.println("Dado inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Dado nao inserido!");
        }
    }
}
