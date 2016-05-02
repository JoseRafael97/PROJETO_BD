/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.jrTransportadora.model.dao;

import br.com.ifpb.jrTransportadora.model.entities.Vehicle;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 */
public class VehicleDAO implements DAO<Vehicle> {

    private Vehicle vehicle;
    private DriverDAO driverDAO;
    private List<Vehicle> listVehicle;

    public VehicleDAO() {
        driverDAO = new DriverDAO();
        listVehicle = new ArrayList<Vehicle>();
    }

    @Override
    public void add(Vehicle veiculo) {
        String sql = "insert into veiculos " + "(marca,modelo,placa,ano,situacao, id_motorista)" + " values (?,?,?,?,?,?)";

        try {

            java.sql.PreparedStatement stm = ManagerConnection.connect().prepareStatement(sql);
            stm.setString(1, veiculo.getBrand());
            stm.setString(2, veiculo.getModel());
            stm.setString(3, veiculo.getBoard());
            stm.setString(4, veiculo.getYear());
            stm.setString(5, veiculo.getSituacao());
            stm.setInt(6, veiculo.getDriver().getId());
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
        String sql = "DELETE FROM veiculos WHERE id_veiculo =" + id;

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
    public void edit(Vehicle veiculo, int id) {
        String sql = "UPDATE veiculos set marca='" + veiculo.getBrand() + "',"
                + "modelo ='" + veiculo.getModel() + "', "
                + "placa = '" + veiculo.getBoard() + "' , ano = '" + veiculo.getYear() + "' , situacao = '" + veiculo.getSituacao() + "'  WHERE id_veiculo =" + id;

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
    public Vehicle search(int id) {
        String sql = "SELECT *FROM veiculos WHERE id_veiculo =" + id;

        java.sql.PreparedStatement stm;
        try {
            stm = ManagerConnection.connect().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                vehicle = new Vehicle(rs.getInt("id_veiculo"), rs.getString("marca"), rs.getString("modelo"), rs.getString("placa"),
                        driverDAO.search(rs.getInt("id_motorista")), rs.getString("ano"), rs.getString("situacao"));
            }

            rs.close();
            stm.close();
            return vehicle;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerConnection.closeConnection();
        }

    }

    public List<Vehicle> list() {
        String sql = "SELECT *FROM veiculos";

        java.sql.PreparedStatement stm;
        try {
            stm = ManagerConnection.connect().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                vehicle = new Vehicle(rs.getInt("id_veiculo"), rs.getString("marca"), rs.getString("modelo"), rs.getString("placa"),
                        driverDAO.search(rs.getInt("id_motorista")), rs.getString("ano"), rs.getString("situacao"));
                listVehicle.add(vehicle);
            }

            rs.close();
            stm.close();
            return listVehicle;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerConnection.closeConnection();
        }
    }

}
