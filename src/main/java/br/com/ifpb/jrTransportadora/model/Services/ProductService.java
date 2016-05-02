/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.jrTransportadora.model.Services;

import br.com.ifpb.jrTransportadora.model.dao.ProductDAO;
import br.com.ifpb.jrTransportadora.model.entities.Client;
import br.com.ifpb.jrTransportadora.model.entities.Product;
import br.com.ifpb.jrTransportadora.model.exceptions.IdentificationException;
import br.com.ifpb.jrTransportadora.model.exceptions.isChargeException;
import java.util.List;

/**
 *
 * @author rafael
 */
public class ProductService {

    private ProductDAO productDAO;

    public ProductService() {
        productDAO = new ProductDAO();
    }

    public void addProduct(String name, String tipo, float preco, float peso, Client client, int quant, String situacao, boolean charge) {
        productDAO.add(new Product(name, tipo, preco, peso, quant, client, situacao, charge));
    }

    public void removeProduct(int id) throws IdentificationException, isChargeException{
        for (Product produ : productDAO.list()) {
            if (produ.getId() == id && !produ.isCharge()) {
                productDAO.remove(id);
                return;
           
            } else{
                throw new isChargeException("O produto está presente em uma Carga não finalizada!");
            }
        }
        throw new IdentificationException("O id não existe!");

    }

    public void editProduct(Product newProdutct, int id) throws IdentificationException {
        for (Product product : productDAO.list()) {
            if (product.getId() == id && newProdutct != null) {
                productDAO.edit(newProdutct, id);
                return;
            }
        }
        throw new IdentificationException("O id não existe!");
    }

    public Product findProduct(int id) throws IdentificationException{
        for (Product product : productDAO.list()) {
            if (product.getId() == id) {
                return productDAO.search(id);
            }
        }
        throw new IdentificationException("O id não existe!");

    }

    public List<Product> listProduct() {
        return productDAO.list();
    }
}
