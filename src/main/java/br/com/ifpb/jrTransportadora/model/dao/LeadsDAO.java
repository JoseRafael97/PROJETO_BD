/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.jrTransportadora.model.dao;

import br.com.ifpb.jrTransportadora.model.entities.Leads;
import br.com.ifpb.jrTransportadora.model.entities.Vehicle;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author rafael
 */
public class LeadsDAO implements DAO<Leads> {
   
    private DriverDAO driver;
    private VehicleDAO vehicle;
    private Leads leads;

    public LeadsDAO() {
        vehicle = new VehicleDAO();
        driver = new DriverDAO();
    }
    
    
    public void add(Leads leads) {
        String sql = "insert into moto_conduz_veic " + "(id_motorista,id_veiculo)" + " values (?,?)";

        try {

            java.sql.PreparedStatement stm = ManagerConnection.connect().prepareStatement(sql);
            stm.setInt(1, leads.getDriver().getId());
            stm.setInt(2, leads.getVehicle().getId());
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
        String sql = "DELETE FROM moto_conduz_veic WHERE id =" + id;

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

    public void edit(Leads Object, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Leads search(int id) {
         String sql = "SELECT *FROM veiculos WHERE id=" + id;

        java.sql.PreparedStatement stm;
        try {
            stm = ManagerConnection.connect().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                leads = new Leads(rs.getInt("id"), vehicle.search(rs.getInt("id_veiculo")),driver.search(rs.getInt("id_motorista")));
            }

            rs.close();
            stm.close();
            return leads;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerConnection.closeConnection();
        }

    }

    public List<Leads> list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
