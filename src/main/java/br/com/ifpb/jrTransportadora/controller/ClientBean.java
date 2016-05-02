/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.jrTransportadora.controller;

import br.com.ifpb.jrTransportadora.model.Services.ClientService;
import br.com.ifpb.jrTransportadora.model.entities.Client;
import br.com.ifpb.jrTransportadora.model.exceptions.IdentificationException;
import br.com.ifpb.jrTransportadora.model.exceptions.IvalideRemoveException;
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
public class ClientBean implements Serializable {

    private Client client;
    private List<Client> list;
    private ClientService clientService;
    private Client clientSelect;
    private String docu;
    private boolean campoCPF;
    private boolean campoCNPJ;
//

    public ClientBean() {
        client = new Client();
        clientService = new ClientService();
        list = clientService.listClients();
    }

    public void addClient() {

        try {
            bloaqueiaCampos();
            clientService.adicioanarCliente(client.getName(), client.getPhone(), client.getCnpjCpf(), client.getAddress());
            FacesMessage msg = new FacesMessage("Cliente cadastrado com sucesso!");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (IdentificationException ex) {
            Logger.getLogger(ClientBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));

        }

    }

    public void removeClient() {
        try {
            clientService.removeClient(clientSelect.getId());
            list.remove(clientSelect);
            FacesMessage msg = new FacesMessage("Cliente removido com sucesso!");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (IvalideRemoveException ex) {
            FacesMessage msg = new FacesMessage(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            Logger.getLogger(ClientBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void onRowEdit(RowEditEvent event) {
        try {
            Client cliente = (Client) event.getObject();
            clientService.editClient(cliente, cliente.getId());
            FacesMessage msg = new FacesMessage("Cliente editado", Integer.toString(cliente.getId()));
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (IdentificationException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
            Logger.getLogger(ClientBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", Integer.toString(((Client) event.getObject()).getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void bloaqueiaCampos() {
        if (docu.equals("CPF")) {
            campoCNPJ = true;
            campoCPF = false;
        } else if (docu.equals("CNPJ")) {
            campoCPF = true;
            campoCNPJ = false;
        } else if (docu == null) {
            campoCNPJ = false;
            campoCPF = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Selecione Uma opção de documento "));
        }
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    public Client getClientSelect() {
        return clientSelect;
    }

    public void setClientSelect(Client clientSelect) {
        this.clientSelect = clientSelect;
    }

    public List<Client> getList() {
        return list;
    }

    public void setList(List<Client> list) {
        this.list = list;
    }

    public String getDocu() {
        return docu;
    }

    public void setDocu(String docu) {
        this.docu = docu;
    }

    public boolean isCampoCPF() {
        return campoCPF;
    }

    public void setCampoCPF(boolean campoCPF) {
        this.campoCPF = campoCPF;
    }

    public boolean isCampoCNPJ() {
        return campoCNPJ;
    }

    public void setCampoCNPJ(boolean campoCNPJ) {
        this.campoCNPJ = campoCNPJ;
    }

}
