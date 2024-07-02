package controllers;

import java.sql.Connection;
import repositories.MySQLConnection;
import repositories.SpecialtyRepository;

public class Specialty {
    String nome;
    String descricao;
    Double price;

    public Specialty (String nome, String descricao, Double price) {
        this.nome = nome;
        this.descricao = descricao;
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
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}