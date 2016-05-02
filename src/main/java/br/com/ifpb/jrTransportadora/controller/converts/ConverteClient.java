/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.jrTransportadora.controller.converts;

import br.com.ifpb.jrTransportadora.model.Services.ClientService;
import br.com.ifpb.jrTransportadora.model.entities.Client;
import br.com.ifpb.jrTransportadora.model.exceptions.IdentificationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author rafael
 */
@FacesConverter(value = "converteClient", forClass = Client.class)
public class ConverteClient implements Converter {

    private ClientService clientService;

    public ConverteClient() {
        clientService = new ClientService();
    }

    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return clientService.searchClient(Integer.parseInt(value));
            } catch (IdentificationException ex) {
                Logger.getLogger(ConverteProduct.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            return String.valueOf(((Client) o).getId());
        } else {
            return null;
        }
    }

}
