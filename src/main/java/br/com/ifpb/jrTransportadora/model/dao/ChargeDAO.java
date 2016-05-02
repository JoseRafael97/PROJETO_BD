/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.jrTransportadora.model.dao;

import br.com.ifpb.jrTransportadora.model.entities.Charge;
import br.com.ifpb.jrTransportadora.model.entities.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 */
public class ChargeDAO implements DAO<Charge> {

    private Charge charges;
    private ProductDAO productDAO;
    private List<Charge> list;
    private List<Product> listProduct;
    private List<Integer> numeros;

    public ChargeDAO() {
        list = new ArrayList<Charge>();
        productDAO = new ProductDAO();
        listProduct = new ArrayList<Product>();
        numeros = new ArrayList<Integer>();

    }

    @Override
    public void add(Charge charge) {
        String sql = "insert into cargas " + "(peso, situacao)" + " values (?,?)";

        try {

            java.sql.PreparedStatement stm = ManagerConnection.connect().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setFloat(1, charge.getWeight());
            stm.setString(2, charge.getSituacao());
            stm.execute();
            ResultSet rs = stm.getGeneratedKeys();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            stm.close();
            String sql2 = "insert into produto_carga" + "(id_produto,id_carga)" + "values(?,?)";
            stm = ManagerConnection.connect().prepareStatement(sql2);
            for (Product product : charge.getProducts()) {
                stm.setInt(1, product.getId());
                stm.setInt(2, id);
                stm.execute();
            }
            stm.close();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            ManagerConnection.closeConnection();
        }
    }

    @Override
    public void remove(int id) {
        String sql = "DELETE FROM produto_carga WHERE id_carga = " + id;

        try {
            java.sql.PreparedStatement stm = ManagerConnection.connect().prepareStatement(sql);
            stm.execute();
            stm.close();
            sql = "DELETE FROM cargas WHERE id_carga =" + id;
            stm = ManagerConnection.connect().prepareStatement(sql);
            stm.execute();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ManagerConnection.closeConnection();
        }

    }

    @Override
    public void edit(Charge charge, int id) {
        String sql = "UPDATE cargas set peso=" + charge.getWeight() + ",situacao ='" + charge.getSituacao() + "' WHERE id_carga=" + id;

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
    public Charge search(int id) {
        String sql = "SELECT *FROM produto_carga WHERE id_carga=" + id;
        java.sql.PreparedStatement stm;

        try {
            stm = ManagerConnection.connect().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                listProduct.add(productDAO.search(rs.getInt("id_produto")));
            }
            rs.close();
            stm.close();

            sql = "SELECT *FROM cargas WHERE id_carga=" + id;
            stm = ManagerConnection.connect().prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                charges = new Charge(rs.getInt("id_carga"), rs.getFloat("peso"), rs.getString("situacao"), listProduct);
            }
            rs.close();
            stm.close();
            return charges;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerConnection.closeConnection();
        }
    }

    public List<Charge> list() {
        String sql = "SELECT *FROM cargas";
        java.sql.PreparedStatement stm;

        try {
            stm = ManagerConnection.connect().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                charges = new Charge(rs.getInt("id_carga"), rs.getFloat("peso"), rs.getString("situacao"), productDAO.list());
                list.add(charges);
            }
            rs.close();
            stm.close();
            sql = "SELECT *FROM produto_carga";
            for (Charge charge : list) {
                stm = ManagerConnection.connect().prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    if (charge.getId() == rs.getInt("id_carga")) {
                        listProduct.add(productDAO.search(rs.getInt("id_produto")));
                    }
                }
                charge.setProducts(listProduct);
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
