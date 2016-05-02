/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.jrTransportadora.controller.converts;

import br.com.ifpb.jrTransportadora.model.Services.DriverService;
import br.com.ifpb.jrTransportadora.model.entities.Client;
import br.com.ifpb.jrTransportadora.model.entities.Driver;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author rafael
 */
@FacesConverter(value = "converteDriver", forClass = Driver.class)
public class converteDriver implements Converter {

    private DriverService driverService;

    public converteDriver() {
        driverService = new DriverService();
    }

    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && value.trim().length() > 0) {
            return driverService.fingDriver(Integer.parseInt(value));
        }
        return null;
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            return String.valueOf(((Driver) o).getId());
        } else {
            return null;
        }
    }

}
