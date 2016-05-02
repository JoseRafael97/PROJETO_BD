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
public abstract class Employee {
    private int id;
    private float salary;
    private float workload;
    private String cpf;
    private String rg;
    private String name;
    private String type;

    public Employee(int id, float salary, float workload, String cpf, String rg, String name, String type) {
        this.id = id;
        this.salary = salary;
        this.workload = workload;
        this.cpf = cpf;
        this.rg = rg;
        this.name = name;
        this.type = type;
    }
    public Employee( float salary, float workload, String cpf, String rg, String name, String type) {
        this.salary = salary;
        this.workload = workload;
        this.cpf = cpf;
        this.rg = rg;
        this.name = name;
        this.type = type;
    }

    public Employee(){}
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public float getWorkload() {
        return workload;
    }

    public void setWorkload(float workload) {
        this.workload = workload;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.id;
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
        final Employee other = (Employee) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    
}
