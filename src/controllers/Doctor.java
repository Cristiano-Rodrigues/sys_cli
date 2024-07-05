package controllers;

import java.sql.Connection;
import java.time.LocalDate;
import repositories.DoctorRepository;
import repositories.EmployeeRepository;
import repositories.MySQLConnection;

public class Doctor extends Employee {
    String crm;

    public Doctor(String name, String username, String password, String role, Double salary, LocalDate date, String crm) {
        super(name, username, password, role, salary, date);
        this.crm = crm;
    }
    
    public boolean cadastrarMedico () {
        try {
            Connection conn = MySQLConnection.getConnection();
            EmployeeRepository emp = new EmployeeRepository(conn);
            DoctorRepository doc = new DoctorRepository(conn);
            
            int empId = emp.create(this);

            if (empId == -1) {
                return false;
            }

            doc.create(empId, crm);

            return true;
        } catch (Exception e) {
            return false;
        } finally {
            MySQLConnection.closeConnection();
        }
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }
}