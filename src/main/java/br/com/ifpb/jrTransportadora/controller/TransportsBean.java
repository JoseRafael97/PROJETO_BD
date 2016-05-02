/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.jrTransportadora.controller;

import br.com.ifpb.jrTransportadora.model.Services.TransportaService;
import br.com.ifpb.jrTransportadora.model.Services.VehicleService;
import br.com.ifpb.jrTransportadora.model.entities.Transports;
import br.com.ifpb.jrTransportadora.model.entities.Vehicle;
import br.com.ifpb.jrTransportadora.model.exceptions.IdentificationException;
import br.com.ifpb.jrTransportadora.model.exceptions.IntervaloDeDatasInvalido;
import br.com.ifpb.jrTransportadora.model.exceptions.RemoveInvalideException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author rafael
 */
@ManagedBean
@ViewScoped
public class TransportsBean implements Serializable {

    private TransportaService transportaService;
    private Transports transports;
    private String dataAtual;
    private VehicleService vehicleService;
    private List<Vehicle> listVehicle;
    private List<Transports> listTransport;
    private Transports selectedTransport;
    private int idCarga;

    public TransportsBean() {
        transportaService = new TransportaService();
        transports = new Transports();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        dataAtual = sdf.format(new Date());
        vehicleService = new VehicleService();
        listVehicle = vehicleService.listVehicle();
        listTransport = transportaService.listTransports();
        System.out.println(sdf.format(new Date()));
    }

    public void addTransport() {
        try {
            transportaService.addTransport(transports.getDtDeparture(), transports.getDtDelivery(), transports.getVehicle(), transports.getCharge(),
                    transports.getCep(), transports.getCity(), transports.getAdress());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Novo transporte criado",
                    "Consulte os transportes em andamento para mais detalhes"));
        } catch (RemoveInvalideException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage(),
                    "Consulte os transportes em andamento para mais detalhes"));
        } catch (IntervaloDeDatasInvalido ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage(),
                    "verifique as datas e tente novamente!"));
            Logger.getLogger(TransportsBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void removeTransporte() {
        transportaService.removeTransport(selectedTransport.getId());
        listTransport.remove(selectedTransport);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("transporte removido com sucesso!",
                String.valueOf(selectedTransport.getId())));
    }

    public Transports rastreiaCarga() {
        try {
            return transportaService.rastreioCarga(idCarga);
        } catch (IdentificationException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage(),
                    "Consulte os transportes em andamento para mais detalhes"));
            Logger.getLogger(TransportsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Transports rastreiaProduct() {
        try {
            return transportaService.rastreioProduct(idCarga);
        } catch (IdentificationException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage(),
                    "Consulte os transportes em andamento para mais detalhes"));
            Logger.getLogger(TransportsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public TransportaService getTransportaService() {
        return transportaService;
    }

    public void setTransportaService(TransportaService transportaService) {
        this.transportaService = transportaService;
    }

    public Transports getTransports() {
        return transports;
    }

    public void setTransports(Transports transports) {
        this.transports = transports;
    }

    public String getDataAtual() {
        return dataAtual;
    }

    public void setDataAtual(String dataAtual) {
        this.dataAtual = dataAtual;
    }

    public VehicleService getVehicleService() {
        return vehicleService;
    }

    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public List<Vehicle> getListVehicle() {

        return listVehicle;
    }

    public void setListVehicle(List<Vehicle> listVehicle) {
        this.listVehicle = listVehicle;
    }

    public List<Transports> getListTransport() {
        return listTransport;
    }

    public void setListTransport(List<Transports> listTransport) {
        this.listTransport = listTransport;
    }

    public Transports getSelectedTransport() {
        return selectedTransport;
    }

    public void setSelectedTransport(Transports selectedTransport) {
        this.selectedTransport = selectedTransport;
    }

    public int getIdCarga() {
        return idCarga;
    }

    public void setIdCarga(int idCarga) {
        this.idCarga = idCarga;
    }

}
