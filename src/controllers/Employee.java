package controllers;

import java.sql.Connection;
import java.time.LocalDate;
import repositories.EmployeeRepository;
import repositories.MySQLConnection;

public class Employee {
    String name;
    String role;
    Double salary;
    LocalDate date;

    public Employee (String name, String role, Double salary, LocalDate date) {
        this.name = name;
        this.role = role;
        this.salary = salary;
        this.date = date;
    }

    public boolean cadastrarFuncionario () {
        if (
            this.date.isAfter(LocalDate.now()) ||
            this.salary <= 0
        ) {
            return false;
        }

        try {
            Connection conn = MySQLConnection.getConnection();
            EmployeeRepository emp = new EmployeeRepository(conn);
            
            emp.create(this);

            return true;
        } catch (Exception e) {
            return false;
        } finally {
            MySQLConnection.closeConnection();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
