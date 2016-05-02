/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.jrTransportadora.controller.converts;

import br.com.ifpb.jrTransportadora.model.Services.ProductService;
import br.com.ifpb.jrTransportadora.model.entities.Product;
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
@FacesConverter(value = "converteCharge", forClass = Product.class)
public class ConverteProduct implements Converter {

    private ProductService productService;

    public ConverteProduct() {
        productService = new ProductService();
    }

    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return productService.findProduct(Integer.parseInt(value));
            } catch (IdentificationException ex) {
                Logger.getLogger(ConverteProduct.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            return String.valueOf(((Product) o).getId());
        } else {
            return null;
        }
    }

}
