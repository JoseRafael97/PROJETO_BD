/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.jrTransportadora.controller;

import br.com.ifpb.jrTransportadora.model.Services.VehicleService;
import br.com.ifpb.jrTransportadora.model.entities.Vehicle;
import br.com.ifpb.jrTransportadora.model.exceptions.AtributoInvalidoException;
import br.com.ifpb.jrTransportadora.model.exceptions.RemoveInvalideException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author rafael
 */
@ManagedBean
@ViewScoped
public class VehicleBean implements Serializable {

    private VehicleService vehicleService;
    private Vehicle vehicle;
    private List<Vehicle> listVehicle;
    private Vehicle vehicleSelected;

    public VehicleBean() {
        vehicle = new Vehicle();
        vehicleService = new VehicleService();
        listVehicle = vehicleService.listVehicle();
    }

    public void addVehicle() {
        try {
            vehicle.setSituacao(SituacionEnumaration.VEHICLENORMAL.getNome());
            vehicleService.addVehicle(vehicle.getBrand(), vehicle.getModel(), vehicle.getBoard(), vehicle.getDriver(), vehicle.getYear(), vehicle.getSituacao());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Veículo adicionado com sucesso!", "Para mais detalhes consute todos os veículos."));
        } catch (AtributoInvalidoException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage(), "Para mais detalhes consute todos os veículos."));
            Logger.getLogger(VehicleBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeVehicle() {
        try {
            vehicleService.removeVehicle(vehicleSelected.getId());
            listVehicle.remove(vehicleSelected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Veículo removido com sucesso!"));
        } catch (RemoveInvalideException ex) {
            Logger.getLogger(VehicleBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));

        }
    }

    public void onRowEdit(RowEditEvent event) {
        Vehicle vehicle = (Vehicle) event.getObject();
        vehicleService.editVehicle(vehicle, vehicle.getId());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Veículo alterado com sucesso!",
                Integer.toString(vehicle.getId())));

    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelado", Integer.toString(((Vehicle) event.getObject()).getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public VehicleService getVehicleService() {
        return vehicleService;
    }

    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public List<Vehicle> getListVehicle() {
        return listVehicle;
    }

    public void setListVehicle(List<Vehicle> listVehicle) {
        this.listVehicle = listVehicle;
    }

    public Vehicle getVehicleSelected() {
        return vehicleSelected;
    }

    public void setVehicleSelected(Vehicle vehicleSelected) {
        this.vehicleSelected = vehicleSelected;
    }

}
