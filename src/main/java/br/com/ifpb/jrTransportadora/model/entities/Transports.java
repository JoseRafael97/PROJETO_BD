/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.jrTransportadora.model.entities;

import java.util.Date;

/**
 *
 * @author rafael
 */
public class Transports {
    
    private int id;
    private Date dtDeparture;
    private Date dtDelivery;
    private Vehicle vehicle;
    private Charge charge;
    private String cep;
    private String city;
    private String adress;

    public Transports(int id, Date dtDeparture, Date dtDelivery, Vehicle vehicle, Charge charge,String cep,String city,String adress) {
        this.id = id;
        this.dtDeparture = dtDeparture;
        this.dtDelivery = dtDelivery;
        this.vehicle = vehicle;
        this.charge = charge;
        this.cep = cep;
        this.city = city;
        this.adress = adress;
    }

    public Transports(Date dtDeparture, Date dtDelivery, Vehicle vehicle, Charge charge,String cep,String city,String adress) {
        this.dtDeparture = dtDeparture;
        this.dtDelivery = dtDelivery;
        this.vehicle = vehicle;
        this.charge = charge;
        this.cep = cep;
        this.city = city;
        this.adress = adress;
    }

    public Transports() {
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDtDeparture() {
        return dtDeparture;
    }

    public void setDtDeparture(Date dtDeparture) {
        this.dtDeparture = dtDeparture;
    }

    public Date getDtDelivery() {
        return dtDelivery;
    }

    public void setDtDelivery(Date dtDelivery) {
        this.dtDelivery = dtDelivery;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Charge getCharge() {
        return charge;
    }

    public void setCharge(Charge charge) {
        this.charge = charge;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    
}

