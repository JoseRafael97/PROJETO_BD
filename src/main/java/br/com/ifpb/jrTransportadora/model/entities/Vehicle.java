/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.jrTransportadora.model.entities;

/**
 *
 * @author rafael
 */
public class Vehicle {

    private int id;
    private String brand;
    private String model;
    private String board;
    private Driver driver;
    private String year;
    private String situacao;

    public Vehicle(int id, String brand, String model, String board, Driver drive, String year, String situacao) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.board = board;
        this.driver = drive;
        this.year = year;
        this.situacao = situacao;
    }

    public Vehicle(String brand, String model, String board, Driver drive, String year, String situacao) {
        this.brand = brand;
        this.model = model;
        this.board = board;
        this.driver = drive;
        this.year = year;
        this.situacao = situacao;
    }

    public Vehicle() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vehicle other = (Vehicle) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    

}
