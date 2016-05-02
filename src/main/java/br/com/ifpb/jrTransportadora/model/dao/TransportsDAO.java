/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.jrTransportadora.model.dao;

import br.com.ifpb.jrTransportadora.model.entities.Transports;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 */
public class TransportsDAO implements DAO<Transports> {

    private VehicleDAO vehicle;
    private ChargeDAO charge;
    private Transports transp;
    private List<Transports> list;

    public TransportsDAO() {
        vehicle = new VehicleDAO();
        charge = new ChargeDAO();
        list = new ArrayList<Transports>();
    }

    public void add(Transports transp) {
        String sql = "insert into transporta " + "(dt_saida,dt_entrega,id_veiculo, id_carga, cep_destino, cidade_uf, endereco)" + " values (?,?,?,?,?,?,?)";

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dtDelivery = sdf.format(transp.getDtDelivery());
            String dtDeparture = sdf.format(transp.getDtDeparture());
            java.sql.PreparedStatement stm = ManagerConnection.connect().prepareStatement(sql);
            stm.setString(1, dtDelivery);
            stm.setString(2, dtDeparture);
            stm.setInt(3, transp.getVehicle().getId());
            stm.setInt(4, transp.getCharge().getId());
            stm.setString(5, transp.getCep());
            stm.setString(6, transp.getCity());
            stm.setString(7, transp.getAdress());

            stm.execute();
            stm.close();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            ManagerConnection.closeConnection();
        }
    }

    public void remove(int id) {
        String sql = "DELETE FROM transporta WHERE id =" + id;

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

    public void edit(Transports transp, int id) {
        String sql = "UPDATE transporta set dt_saida ='" + transp.getDtDelivery() + "',"
                + "dt_entrega ='" + transp.getDtDeparture() + "', cep_destino = '" + transp.getCep() + "', cidade_uf = '" + transp.getCity() + "', "
                + "endereco = '" + transp.getAdress() + "'WHERE id=" + id;

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

    public Transports search(int id) {
        String sql = "SELECT *FROM transporta WHERE id=" + id;

        java.sql.PreparedStatement stm;
        try {
            stm = ManagerConnection.connect().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                transp = new Transports(rs.getInt("id"), rs.getDate("dt_entrega"),
                        rs.getDate("dt_saida"), vehicle.search(rs.getInt("id_veiculo")), charge.search(rs.getInt("id_carga")),
                        rs.getString("cep_destino"), rs.getString("cidade_uf"), rs.getString("endereco"));
            }

            rs.close();
            stm.close();
            return transp;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerConnection.closeConnection();
        }
    }

    public List<Transports> list() {
        String sql = "SELECT *FROM transporta";
        java.sql.PreparedStatement stm;
        try {
            stm = ManagerConnection.connect().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                transp = new Transports(rs.getInt("id"), rs.getDate("dt_entrega"),
                        rs.getDate("dt_saida"), vehicle.search(rs.getInt("id_veiculo")), charge.search(rs.getInt("id_carga")),
                        rs.getString("cep_destino"), rs.getString("cidade_uf"), rs.getString("endereco"));
                list.add(transp);
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
