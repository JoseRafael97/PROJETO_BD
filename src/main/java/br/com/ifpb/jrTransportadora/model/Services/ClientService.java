/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.jrTransportadora.model.Services;

import br.com.ifpb.jrTransportadora.model.dao.ClientDAO;
import br.com.ifpb.jrTransportadora.model.dao.ProductDAO;
import br.com.ifpb.jrTransportadora.model.entities.Client;
import br.com.ifpb.jrTransportadora.model.entities.Product;
import br.com.ifpb.jrTransportadora.model.exceptions.IdentificationException;
import br.com.ifpb.jrTransportadora.model.exceptions.IvalideRemoveException;
import java.util.List;

/**
 *
 * @author rafael
 */
public class ClientService {

    private Client cliente;
    private final ClientDAO clienteDao;
    private final ProductDAO productDAO;

    public ClientService() {
        clienteDao = new ClientDAO();
        productDAO = new ProductDAO();
    }

    public void adicioanarCliente(String name, String phone, String cpfcnpj, String Adress)throws IdentificationException{
        for (Client client : clienteDao.list()) {
            if (client.getCnpjCpf().equals(cpfcnpj)) {
                throw new IdentificationException("O cpf/cnpj já existe!");
            }
        }
        clienteDao.add(new Client(name, phone, cpfcnpj, Adress));
    }

    public void removeClient(int id) throws IvalideRemoveException {
        for (Product produto : productDAO.list()) {
            if (id == produto.getClient().getId()) {
                throw new IvalideRemoveException("Existe um produto pedente deste cliente!");
            }
        }
        clienteDao.remove(id);

    }

    public void editClient(Client cliente, int id) throws IdentificationException {

        for (Client c : clienteDao.list()) {

            if (c.getId() == id && cliente != null) {
                clienteDao.edit(cliente, cliente.getId());
                return;
            }
        }
        throw new IdentificationException("Id nao existente!");
    }

    public Client searchClient(int id) throws IdentificationException {
        for (Client c : clienteDao.list()) {
            if (c.getId() == id) {
                return clienteDao.search(id);
            }
        }
        throw new IdentificationException("Id nao existente!");
    }

    public List<Client> listClients() {
        return clienteDao.list();

    }
}
