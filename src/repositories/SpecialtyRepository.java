package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

            stmt.setString(1, spec.getNome());
            stmt.setDouble(2, spec.getPrice());
            stmt.setString(3, spec.getDescricao());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Insercao nao foi bem sucedida");
        }
    }
}
