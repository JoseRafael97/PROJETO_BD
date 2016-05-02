/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.jrTransportadora.controller.converts;

import br.com.ifpb.jrTransportadora.model.Services.ChargeService;
import br.com.ifpb.jrTransportadora.model.entities.Charge;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author rafael
 */
@FacesConverter(value = "converteCarga", forClass = Charge.class)
public class ConverteCharge implements Converter {

    private ChargeService chargeService;

    public ConverteCharge() {
        chargeService = new ChargeService();
    }

    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && value.trim().length() > 0) {
            return chargeService.findCharge(Integer.parseInt(value));
        }
        return null;
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            return String.valueOf(((Charge) o).getId());
        } else {
            return null;
        }
    }

}
