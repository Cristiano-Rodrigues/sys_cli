package controllers;

import java.sql.Connection;
import java.time.LocalDateTime;

import repositories.InvoiceRepository;
import repositories.MySQLConnection;
import repositories.PatientRepository;

public class Invoice {
  boolean state;
  double value;
  LocalDateTime date;

  public Invoice (boolean state, double value) {
    this.state = state;
    this.value = value;
    this.date = LocalDateTime.now();
  }

  public boolean cadastrarFactura (int patientId) {
    try {
      Connection conn = MySQLConnection.getConnection();
      PatientRepository patRepository = new PatientRepository(conn);
      InvoiceRepository invRepository = new InvoiceRepository(conn);

      if (
        patRepository.getById(patientId) == null
      ) {
        return false;
      }

      invRepository.create(this, patientId);

      return true;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    } finally {
      MySQLConnection.closeConnection();
    }
  }

  public boolean isState() {
    return state;
  }

  public void setState(boolean state) {
    this.state = state;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  
}
