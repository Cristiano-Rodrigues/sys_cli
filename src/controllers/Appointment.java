package controllers;

import java.sql.Connection;
import java.time.LocalDateTime;
import repositories.*;

public class Appointment {
    String state;
    LocalDateTime dateTime;

    public Appointment (String state, LocalDateTime dateTime) {
        this.state = state;
        this.dateTime = dateTime;
    }

    public boolean agendarConsulta (int patientId, int doctorId, int specialtyId) {
        int weekDay = this.dateTime.getDayOfWeek().ordinal();
        int SUNDAY = 7;
        if (
            this.dateTime.isBefore(LocalDateTime.now()) ||
            weekDay == SUNDAY
        ) {
            return false;
        }

        try {
            Connection conn = MySQLConnection.getConnection();
            PatientRepository patRepository = new PatientRepository(conn);
            DoctorRepository docRepository = new DoctorRepository(conn);
            SpecialtyRepository specRepository = new SpecialtyRepository(conn);
            TimeRepository timeRepository = new TimeRepository(conn);
            AppointmentRepository appRepository = new AppointmentRepository(conn);

            if (
                patRepository.getById(patientId) == null ||
                docRepository.getById(doctorId) == null ||
                specRepository.getById(specialtyId) == null
            ) {
                return false;
            }

            int startHour = this.dateTime.getHour();
            int endHour = startHour + 1;
            boolean isWorkingDay = timeRepository.checkAvailability(doctorId, weekDay, startHour, endHour);
            if (!isWorkingDay) {
                return false;
            }

            boolean isAppointmentDateAvailable = appRepository.checkDateAvailability(doctorId, patientId, dateTime);
            if (!isAppointmentDateAvailable) {
                return false;
            }

            appRepository.create(this, patientId, doctorId, specialtyId);

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
