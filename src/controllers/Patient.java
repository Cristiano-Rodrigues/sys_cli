package controllers;

import java.sql.Connection;
import java.time.LocalDate;
import repositories.MySQLConnection;
import repositories.PatientRepository;

public class Patient {
    String name;
    LocalDate born;
    String address;
    String phone;

    public Patient (String name, LocalDate date, String address, String phone) {
        this.name = name;
        this.born = date;
        this.address = address;
        this.phone = phone;
    }

    public boolean cadastrarPaciente () {
        try {
            Connection conn = MySQLConnection.getConnection();
            PatientRepository patient = new PatientRepository(conn);

            patient.create(this);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBorn() {
        return born;
    }

    public void setBorn(LocalDate born) {
        this.born = born;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
