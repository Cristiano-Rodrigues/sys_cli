package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import controllers.Appointment;


public class AppointmentRepository {
  private Connection conn = null;

  public AppointmentRepository (Connection conn) {
    this.conn = conn;
  }

  public void create (Appointment appointment, int patientId, int doctorId, int specialtyId) {
    try {
      String sql = """
        INSERT INTO consulta (estado, id_paciente, id_medico, id_especialidade, data_hora)
        VALUES (?, ?, ?, ?, ?)
      """;
      PreparedStatement stmt = this.conn.prepareStatement(sql);

      stmt.setString(1, appointment.getState());
      stmt.setInt(2, patientId);
      stmt.setInt(3, doctorId);
      stmt.setInt(4, specialtyId);
      stmt.setTimestamp(5, Timestamp.valueOf(appointment.getDateTime()));

      stmt.executeUpdate();

      System.out.println("Dado inserido com sucesso!");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public boolean checkDateAvailability (int doctorId, int patientId, LocalDateTime appointmentDate) {
    try {
      String sql = """
        SELECT * FROM consulta
        WHERE (id_medico = ? OR id_paciente = ?) AND
        data_hora <= ? AND date_add(data_hora, INTERVAL 1 HOUR) > ?
      """;
      PreparedStatement stmt = this.conn.prepareStatement(sql);

      stmt.setInt(1, doctorId);
      stmt.setInt(2, patientId);
      stmt.setTimestamp(3, Timestamp.valueOf(appointmentDate));
      stmt.setTimestamp(4, Timestamp.valueOf(appointmentDate));

      ResultSet rs = stmt.executeQuery();

      return !rs.next();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }
  }
}
