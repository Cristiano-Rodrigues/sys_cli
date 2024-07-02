package controllers;

import java.sql.Connection;
import java.time.LocalDate;

import repositories.DoctorRepository;
import repositories.MySQLConnection;
import repositories.PatientRepository;
import repositories.SpecialtyRepository;

public class Appointment {
    String state;
    LocalDate dateTime;

    public Appointment (String state, LocalDate dateTime) {
        this.state = state;
        this.dateTime = dateTime;
    }

    public boolean agendarConsulta (int patientId, int doctorId, int specialtyId) {
        int SUNDAY = 7;
        if (
            this.dateTime.isBefore(LocalDate.now()) ||
            this.dateTime.getDayOfWeek().ordinal() == SUNDAY
        ) {
            return false;
        }

        Connection conn = MySQLConnection.getConnection();
        PatientRepository patRepository = new PatientRepository(conn);
        DoctorRepository docRepository = new DoctorRepository(conn);
        SpecialtyRepository specRepository = new SpecialtyRepository(conn);

        if (
            patRepository.getById(patientId) == null ||
            docRepository.getById(doctorId) == null ||
            specRepository.getById(specialtyId) == null
        ) {
            return false;
        }

        // TEMP

        return true;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }
}
