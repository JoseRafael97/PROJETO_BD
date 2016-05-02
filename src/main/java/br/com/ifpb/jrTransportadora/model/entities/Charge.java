/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.jrTransportadora.model.entities;

import java.util.List;

/**
 *
 * @author rafael
 */
public class Charge {

    private int id;
    private float weight;
    private String situacao;
    private List<Product> products;

    public Charge(int id, float weight, String situacao, List<Product> products) {
        this.id = id;
        this.weight = weight;
        this.situacao = situacao;
        this.products = products;
    }

    public Charge(float weight, String situacao, List<Product> products) {
        this.weight = weight;
        this.situacao = situacao;
        this.products = products;

    }

    public Charge() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
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
        hash = 89 * hash + this.id;
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
        final Charge other = (Charge) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
