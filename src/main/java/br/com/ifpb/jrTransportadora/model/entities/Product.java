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
public class Product {

    private int id;
    private String name;
    private String type;
    private String situacao;
    private float price;
    private float peso;
    private int quant;
    private Client client;
    private boolean charge = false;

    public Product(int id, String name, String type, float price, float peso, int quant, Client client, String situacao, boolean charge) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.client = client;
        this.peso = peso;
        this.quant = quant;
        this.situacao = situacao;
        this.charge = charge;
    }

    public Product(String name, String type, float price, float peso, int quant, Client client, String situacao, boolean charge) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.client = client;
        this.peso = peso;
        this.quant = quant;
        this.situacao = situacao;
        this.charge = charge;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public boolean isCharge() {
        return charge;
    }

    public void setCharge(boolean charge) {
        this.charge = charge;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.id;
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
        final Product other = (Product) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    
    
}
