package controllers;

import java.sql.Connection;
import repositories.MySQLConnection;
import repositories.SpecialtyRepository;

public class Specialty {
    String name;
    String description;
    Double price;

    public Specialty (String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public boolean cadastrarEspecialidade () {
        try {
            Connection conn = MySQLConnection.getConnection();
            SpecialtyRepository spec = new SpecialtyRepository(conn);

            spec.create(this);
            
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}