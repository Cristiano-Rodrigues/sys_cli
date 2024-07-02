package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import controllers.Specialty;

public class SpecialtyRepository {
    private Connection conn = null;
    
    public SpecialtyRepository (Connection conn) {
        this.conn = conn;
    }

    public void create (Specialty spec) {
        try {
            String sql = "INSERT INTO especialidade (nome, preco, descricao) VALUES (?, ?, ?)";
            PreparedStatement stmt = this.conn.prepareStatement(sql);

            stmt.setString(1, spec.getName());
            stmt.setDouble(2, spec.getPrice());
            stmt.setString(3, spec.getDescription());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Insercao nao foi bem sucedida");
        }
    }

    public Specialty getById (int id) {
        try {
            String sql = "SELECT * FROM especialidade WHERE id =?";
            PreparedStatement stmt = this.conn.prepareStatement(sql);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            return new Specialty(
                rs.getString("nome"),
                rs.getString("descricao"),
                rs.getDouble("preco")
            );
        } catch (Exception e) {
            return null;
        }
    }
}
