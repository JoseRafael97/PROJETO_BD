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
public class Driver extends Employee {

    private int idDriver;
    private String cnh;

    public Driver(String cnh, float salario, float cargaHoraria, String cpf, String rg, String nome, String tipo) {
        super(salario, cargaHoraria, cpf, rg, nome, tipo);
        this.cnh = cnh;
    }

    public Driver(int idDriver, String cnh, int id, float salary, float workload, String cpf, String rg, String name, String type) {
        super(id ,salary, workload, cpf, rg, name, type);
        this.idDriver = idDriver;
        this.cnh = cnh;
    }

    public Driver() {
        super();
    }
    
    public int getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(int idDriver) {
        this.idDriver = idDriver;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.idDriver;
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
        final Driver other = (Driver) obj;
        if (this.idDriver != other.idDriver) {
            return false;
        }
        return true;
    }

}
