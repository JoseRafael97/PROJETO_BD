/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.jrTransportadora.controller.converts;

import br.com.ifpb.jrTransportadora.model.Services.VehicleService;
import br.com.ifpb.jrTransportadora.model.entities.Vehicle;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author rafael
 */
@FacesConverter(value = "converteVehicle", forClass = Vehicle.class)
public class ConverteVehicle implements Converter {

    private VehicleService vehicleService;

    public ConverteVehicle() {
        vehicleService = new VehicleService();
    }

    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && value.trim().length() > 0) {
            return vehicleService.findVehicle(Integer.parseInt(value));
        }
        return null;
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            return String.valueOf(((Vehicle) o).getId());
        } else {
            return null;
        }
    }

}
