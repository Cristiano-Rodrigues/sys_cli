package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import controllers.Invoice;

public class InvoiceRepository {
  private Connection conn = null;

  public InvoiceRepository (Connection conn) {
    this.conn = conn;
  }

  public void create (Invoice invoice, int patientId) {
    try {
      String sql = """
        INSERT INTO factura (estado, id_paciente, valor, data_emissao) VALUES (?, ?, ?, ?)
      """;
      PreparedStatement stmt = this.conn.prepareStatement(sql);

      stmt.setBoolean(1, invoice.isState());
      stmt.setInt(2, patientId);
      stmt.setDouble(3, invoice.getValue());
      stmt.setTimestamp(4, Timestamp.valueOf(invoice.getDate()));

      stmt.executeUpdate();

      System.out.println("Dado inserido com sucesso!");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
}
