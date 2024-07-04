package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TimeRepository {
  private Connection conn = null;

  public TimeRepository (Connection conn) {
    this.conn = conn;
  }

  public boolean checkAvailability (
    int employeeId, int weekDay, int startHour, int endHour) {
      try {
        String sql = """
          SELECT * FROM horario
          WHERE id_funcionario = ? AND dia_semana = ? AND
          hour(hora_inicio) <= ? AND hour(hora_termino) >= ?
        """;

        PreparedStatement stmt = this.conn.prepareStatement(sql);

        stmt.setInt(1, employeeId);
        stmt.setInt(2, weekDay);
        stmt.setInt(3, startHour);
        stmt.setInt(4, endHour);

        ResultSet rs = stmt.executeQuery();

        return rs.next();
      } catch (SQLException e) {
        System.out.println(e.getMessage());
        return false;
      }
  }
}
