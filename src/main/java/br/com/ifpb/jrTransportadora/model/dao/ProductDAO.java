/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.jrTransportadora.model.dao;

import br.com.ifpb.jrTransportadora.model.entities.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 */
public class ProductDAO implements DAO<Product> {

    private Product product;
    private ClientDAO client;
    private List<Product> list;

    public ProductDAO() {
        client = new ClientDAO();
        list = new ArrayList<Product>();
    }

    public void add(Product product) {
        String sql = "insert into produtos " + "(nome,categoria,preco,peso, id_cliente,quantidade, situacao, iscarga)" + " values (?,?,?,?,?,?,?,?)";
        try {

            java.sql.PreparedStatement stm = ManagerConnection.connect().prepareStatement(sql);

            stm.setString(1, product.getName());
            stm.setString(2, product.getType());
            stm.setFloat(3, product.getPrice());
            stm.setFloat(4, product.getPeso());
            stm.setInt(5, product.getClient().getId());
            stm.setInt(6, product.getQuant());
            stm.setString(7, product.getSituacao());
            stm.setBoolean(8, product.isCharge());
            stm.execute();
            stm.close();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            ManagerConnection.closeConnection();
        }
    }

    public void remove(int id) {
        String sql = "DELETE FROM produtos WHERE id =" + id;

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

    public void edit(Product product, int id) {
        String sql = "UPDATE produtos set nome='"+ product.getName()+"',"
                + "categoria ='"+product.getType()+"', preco = "+ product.getPrice() +", peso ="+ product.getPeso()
                + ",situacao ='"+product.getSituacao()+"', iscarga="+product.isCharge() +", quantidade="+product.getQuant()+  "  WHERE id=" +id;

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

    public Product search(int id) {
        String sql = "SELECT *FROM produtos WHERE id=" + id;

        java.sql.PreparedStatement stm;
        try {
            stm = ManagerConnection.connect().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                product = new Product(rs.getInt("id"), rs.getString("nome"), rs.getString("categoria"), rs.getFloat("preco"), rs.getFloat("peso"),
                         rs.getInt("quantidade"),client.search(rs.getInt("id_cliente")), rs.getString("situacao"), rs.getBoolean("iscarga"));
            }

            rs.close();
            stm.close();
            return product;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerConnection.closeConnection();
        }
    }

    public List<Product> list() {
        String sql = "SELECT *FROM produtos";

        java.sql.PreparedStatement stm;
        try {
            stm = ManagerConnection.connect().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                product = new Product(rs.getInt("id"), rs.getString("nome"), rs.getString("categoria"), rs.getFloat("preco"), rs.getFloat("peso"),
                        rs.getInt("quantidade"),client.search(rs.getInt("id_cliente")),rs.getString("situacao"), rs.getBoolean("iscarga"));
                list.add(product);
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
