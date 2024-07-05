package controllers;

import java.sql.Connection;
import java.time.LocalDate;
import repositories.EmployeeRepository;
import repositories.MySQLConnection;
import org.jasypt.util.text.BasicTextEncryptor;
import io.github.cdimascio.dotenv.Dotenv;

public class Employee {
    String name;
    String username;
    String password;
    String role;
    Double salary;
    LocalDate date;

    public Employee (String name, String username, String password, String role, Double salary, LocalDate date) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.salary = salary;
        this.date = date;
    }

    public boolean cadastrarFuncionario () {
        if (
            this.date.isAfter(LocalDate.now()) ||
            this.salary <= 0 ||
            this.password.length() < 8
        ) {
            return false;
        }

        try {
            Connection conn = MySQLConnection.getConnection();
            EmployeeRepository empRepository = new EmployeeRepository(conn);

            if (empRepository.getByUsername(username) != null) {
                return false;
            }

            Dotenv dotenv = Dotenv.load();
            String criptoKey = dotenv.get("KEY");
            BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
            textEncryptor.setPassword(criptoKey);

            this.password = textEncryptor.encrypt(password);

            empRepository.create(this);

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
