/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.jrTransportadora.model.dao;

import br.com.ifpb.jrTransportadora.model.entities.Client;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 */
public class ClientDAO implements DAO<Client> {

    private Client cliente;
    private List<Client> list;

    public ClientDAO() {
        list = new ArrayList<Client>();
    }
    
    
    
    @Override
    public void add(Client cliente) {
        String sql = "insert into clientes " + "(nome,cnpj_cpf,telefone,endereco)" + " values (?,?,?,?)";
        try {

            java.sql.PreparedStatement stm = ManagerConnection.connect().prepareStatement(sql);
            stm.setString(1, cliente.getName());
            stm.setString(2, cliente.getCnpjCpf());
            stm.setString(3, cliente.getPhone());
            stm.setString(4, cliente.getAddress());
            stm.execute();
            stm.close();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            ManagerConnection.closeConnection();
        }
    }

    @Override
    public void remove(int id) {
        String sql = "DELETE FROM clientes WHERE id =" + id;

        try {
            java.sql.PreparedStatement stm = ManagerConnection.connect().prepareStatement(sql);
            stm.execute();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ManagerConnection.closeConnection();
        }
    }

    @Override
    public void edit(Client cliente, int id) {
        String sql = "UPDATE clientes set nome='" + cliente.getName() + "',"
                + "cnpj_cpf ='" + cliente.getCnpjCpf() + "', telefone = '" + cliente.getPhone() + "', "
                + "endereco = '" + cliente.getAddress() + "' WHERE id=" + id;

        try {
            java.sql.PreparedStatement stm = ManagerConnection.connect().prepareStatement(sql);
            stm.execute();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ManagerConnection.closeConnection();
        }
    }

    @Override
    public Client search(int id) {
        String sql = "SELECT *FROM clientes WHERE id=" + id;

        java.sql.PreparedStatement stm;
        try {
            stm = ManagerConnection.connect().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                cliente = new Client(rs.getInt("id"), rs.getString("nome"),
                        rs.getString("telefone"), rs.getString("cnpj_cpf"), rs.getString("endereco"));
            }

            rs.close();
            stm.close();
            return cliente;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerConnection.closeConnection();
        }

    }

    public List<Client> list() {
        String sql = "SELECT *FROM clientes";
        
        java.sql.PreparedStatement stm;
        try {
            
            stm = ManagerConnection.connect().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                cliente = new Client(rs.getInt("id"), rs.getString("nome"),
                        rs.getString("telefone"), rs.getString("cnpj_cpf"), rs.getString("endereco"));
                list.add(cliente);
            }

            rs.close();
            stm.close();
            return list;
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerConnection.closeConnection();
        }

    }

}
