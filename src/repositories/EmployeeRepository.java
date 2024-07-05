package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import controllers.Employee;

public class EmployeeRepository {
    private Connection conn = null;

    public EmployeeRepository (Connection conn) {
        this.conn = conn;
    }

    public int create (Employee emp) {
        try {
            String sql = "INSERT INTO funcionario (nome, cargo, salario, data_contratacao, nome_usuario, senha) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = this.conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            Date date = Date.valueOf(emp.getDate());

            stmt.setString(1, emp.getName());
            stmt.setString(2, emp.getRole());
            stmt.setDouble(3, emp.getSalary());
            stmt.setDate(4, date);
            stmt.setString(5, emp.getUsername());
            stmt.setString(6, emp.getPassword());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                return -1;
            }

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            generatedKeys.next();
            int insertId = generatedKeys.getInt(1);

            return insertId;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Insercao nao foi bem sucedida");
        }

        return -1;
    }

    public Employee getByUsername (String username) {
        try {
            String sql = "SELECT * FROM funcionario WHERE nome_usuario = ?";
            PreparedStatement stmt = this.conn.prepareStatement(sql);

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            return new Employee(
                rs.getString("nome"),
                username,
                rs.getString("senha"),
                rs.getString("cargo"),
                rs.getDouble("salario"),
                rs.getDate("data_contratacao").toLocalDate()
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
