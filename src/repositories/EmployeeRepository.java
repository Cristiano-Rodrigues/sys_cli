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
            String sql = "INSERT INTO funcionario (nome, cargo, salario, data_contratacao) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = this.conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            Date date = Date.valueOf(emp.getDate());

            stmt.setString(1, emp.getName());
            stmt.setString(2, emp.getRole());
            stmt.setDouble(3, emp.getSalary());
            stmt.setDate(4, date);

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
}
