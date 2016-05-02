/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.jrTransportadora.controller;

import br.com.ifpb.jrTransportadora.model.Services.ClientService;
import br.com.ifpb.jrTransportadora.model.Services.ProductService;
import br.com.ifpb.jrTransportadora.model.entities.Client;
import br.com.ifpb.jrTransportadora.model.entities.Product;
import br.com.ifpb.jrTransportadora.model.exceptions.IdentificationException;
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
@ManagedBean(name = "productBean", eager = true)
@ViewScoped
public class ProductBean implements Serializable {

    private Product produto;
    private List<Product> listProduct;
    private ProductService productService;
    private ClientService clientService;
    private Product productSelect;
    private List<Integer> numeros;

    public ProductBean() {
        produto = new Product();
        productService = new ProductService();
        numeros = new ArrayList<Integer>();
        clientService = new ClientService();
        listProduct = productService.listProduct();
        for (int i = 1; i < 401; i++) {
            numeros.add(i);
        }
    }

    public void addProduct() {
        produto.setSituacao(SituacionEnumaration.INICIAL.getNome());
        produto.setCharge(false);
        productService.addProduct(produto.getName(), produto.getType(), produto.getPrice(), produto.getPeso(),
                produto.getClient(), produto.getQuant(), produto.getSituacao(), produto.isCharge());
        FacesMessage msg = new FacesMessage("Produto criado com sucesso!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowEdit(RowEditEvent event) {
        Product product = (Product) event.getObject();
        try {
            productService.editProduct(product, product.getId());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produto alterado com sucesso!",
                    Integer.toString(product.getId())));
        } catch (IdentificationException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
        }
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelado", Integer.toString(((Client) event.getObject()).getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void removeProduct() {
        try {
            productService.removeProduct(productSelect.getId());
            listProduct.remove(productSelect);
            FacesMessage msg = new FacesMessage("Produto removido com sucesso!");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (isChargeException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage(),
                    "Os produtos so podem se removidos se não pertecer a nenhum carga atual!"));
            Logger.getLogger(ProductBean.class.getName()).log(Level.SEVERE, null, ex);
        
        } catch (IdentificationException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
            Logger.getLogger(ProductBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Product getProduto() {
        return produto;
    }

    public void setProduto(Product produto) {
        this.produto = produto;
    }

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    public Product getProductSelect() {
        return productSelect;
    }

    public void setProductSelect(Product productSelect) {
        this.productSelect = productSelect;
    }

    public List<Integer> getNumeros() {
        return numeros;
    }

    public void setNumeros(List<Integer> numeros) {
        this.numeros = numeros;
    }

    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }

}
