/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.jrTransportadora.controller;

import br.com.ifpb.jrTransportadora.model.Services.ChargeService;
import br.com.ifpb.jrTransportadora.model.entities.Charge;
import br.com.ifpb.jrTransportadora.model.entities.Product;
import br.com.ifpb.jrTransportadora.model.exceptions.PesoIvalidoException;
import br.com.ifpb.jrTransportadora.model.exceptions.RemoveInvalideException;
import br.com.ifpb.jrTransportadora.model.exceptions.isChargeException;
import java.io.Serializable;
import java.util.ArrayList;
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
public class ChargeBean implements Serializable {

    private Charge charge;
    private ChargeService chargeService;
    private List<Product> selectedProducts;
    private List<Charge> charges;
    private Charge chargeSelect;

    private List<Product> listProducts;

    public ChargeBean() {
        charge = new Charge();
        chargeService = new ChargeService();
        charges = new ArrayList<Charge>();
        charges = chargeService.listChagers();
    }

    public void addCharge() {
        try {
            float peso = 0;
            for (Product produ : selectedProducts) {
                peso += (produ.getPeso() * produ.getQuant()) / 1000;

            }
            charge.setWeight(peso);
            charge.setSituacao(SituacionEnumaration.INICIAL.getNome());

            chargeService.addCharge(charge.getSituacao(), charge.getWeight(), selectedProducts);
            FacesMessage msg = new FacesMessage("Nova Carga criada!");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (PesoIvalidoException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage(), "O peso desta carga é" + charge.getWeight()));
            Logger.getLogger(ChargeBean.class.getName()).log(Level.SEVERE, null, ex);

        } catch (isChargeException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
            Logger.getLogger(ChargeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeCharge() {
        try {
            chargeService.removeCharge(chargeSelect.getId());
            charges.remove(chargeSelect);
        } catch (RemoveInvalideException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage(), "consulte Transporte para mais detalhes!"));
            Logger.getLogger(ChargeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onRowEdit(RowEditEvent event) {
        Charge cliente = (Charge) event.getObject();
        chargeService.editCharge(charge, charge.getId());
        FacesMessage msg = new FacesMessage("Charge editado", Integer.toString(cliente.getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", Integer.toString(((Charge) event.getObject()).getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public Charge getCharge() {
        return charge;
    }

    public void setCharge(Charge charge) {
        this.charge = charge;
    }

    public ChargeService getChargeService() {
        return chargeService;
    }

    public void setChargeService(ChargeService chargeService) {
        this.chargeService = chargeService;
    }

    public List<Product> getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(List<Product> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

    public List<Charge> getCharges() {
        return charges;
    }

    public void setCharges(List<Charge> charges) {
        this.charges = charges;
    }

    public List<Product> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<Product> listProducts) {
        this.listProducts = listProducts;
    }

    public Charge getChargeSelect() {
        return chargeSelect;
    }

    public void setChargeSelect(Charge chargeSelect) {
        this.chargeSelect = chargeSelect;
    }

}
