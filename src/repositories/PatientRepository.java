package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import controllers.Patient;

public class PatientRepository {
    private Connection conn = null;

    public PatientRepository (Connection conn) {
        this.conn = conn;
    }

    public void create (Patient patient) {
        try {
            String sql = "INSERT INTO paciente (nome, data_nasc, endereco, telefone) VALUES (?, ?, ?, ?)";
            PreparedStatement  stmt = this.conn.prepareStatement(sql);
            Date date = Date.valueOf(patient.getBorn());

            stmt.setString(1, patient.getName());
            stmt.setDate(2, date);
            stmt.setString(3, patient.getAddress());
            stmt.setString(4, patient.getPhone());

            stmt.executeUpdate();

            System.out.println("Dado inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Insercao nao foi bem sucedida");
        }
    }

    public Patient getById (int id) {
        try {
            String sql = "SELECT * FROM paciente WHERE id =?";
            PreparedStatement stmt = this.conn.prepareStatement(sql);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            return new Patient(
                rs.getString("nome"),
                rs.getDate("data_nasc").toLocalDate(),
                rs.getString("endereco"),
                rs.getString("telefone")
            );
        } catch (SQLException e) {
            return null;
        }
    }
}
