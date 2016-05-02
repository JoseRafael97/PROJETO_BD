/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.jrTransportadora.model.dao;

import br.com.ifpb.jrTransportadora.controller.TiposFuncionarios;
import br.com.ifpb.jrTransportadora.model.entities.Driver;
import br.com.ifpb.jrTransportadora.model.entities.Employee;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 */
public class DriverDAO implements DAO<Driver> {

    int idEployee;
    float salary;
    float workLoad;
    String name;
    String cpf;
    String rg;
    String type;

    Driver driver;
    List<Driver> listDriver;
    List<Employee> listEmployees;

    public DriverDAO() {
        listDriver = new ArrayList<Driver>();
        listEmployees = new ArrayList<Employee>();
    }

    public void add(Driver driver) {
        String sql = "insert into funcionarios " + "(nome,cpf,rg, salario, carga_horaria, tipo)" + " values (?,?,?,?,?,?)";

        String sql2 = "insert into motoristas " + "(cnh, id_funcionario)" + " values (?,?)";

        try {

            java.sql.PreparedStatement stm = ManagerConnection.connect().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

            stm.setString(1, driver.getName());
            stm.setString(2, driver.getCpf());
            stm.setString(3, driver.getRg());
            stm.setFloat(4, driver.getSalary());
            stm.setFloat(5, driver.getWorkload());
            stm.setString(6, driver.getType());
            stm.execute();
            ResultSet rs = stm.getGeneratedKeys();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            stm.close();
            stm = ManagerConnection.connection.prepareStatement(sql2);
            stm.setString(1, driver.getCnh());
            stm.setInt(2, id);
            stm.execute();
            stm.close();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            ManagerConnection.closeConnection();
        }
    }

    public void remove(int id) {
        int idFunc  = search(id).getId();
        String sql = "DELETE FROM motoristas WHERE id_motorista =" + id;

        try {
            java.sql.PreparedStatement stm = ManagerConnection.connect().prepareStatement(sql);
            stm.execute();
            stm.close();
            
            sql = "DELETE FROM funcionarios WHERE id =" + idFunc;
            stm = ManagerConnection.connect().prepareStatement(sql);
            stm.execute();
            stm.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ManagerConnection.closeConnection();
        }
    }

    public void edit(Driver funcionario, int id) {
        String sql = "UPDATE funcionarios set nome='" + funcionario.getName() + "',"
                + "cpf ='" + funcionario.getCpf() + "', rg = '" + funcionario.getRg() + "', tipo = '"
                + funcionario.getType() + "',carga_horaria = " + funcionario.getWorkload()+ ", salario = "+funcionario.getSalary() + "WHERE id=" + funcionario.getId();

        try {
            java.sql.PreparedStatement stm = ManagerConnection.connect().prepareStatement(sql);
            stm.execute();
            stm.close();
            sql = "UPDATE motoristas set cnh ='" + funcionario.getCnh() +"'  WHERE id_motorista=" + id;
            stm = ManagerConnection.connect().prepareCall(sql);
            stm.execute();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ManagerConnection.closeConnection();
        }
    }

    public Driver search(int id) {
        java.sql.PreparedStatement stm;
        try {
            String sql = "SELECT *FROM motoristas WHERE id_funcionario = " + id;
            stm = ManagerConnection.connect().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                driver = new Driver(rs.getInt("id_motorista"), rs.getString("cnh"),
                        rs.getInt("id_funcionario"), 0, 0, null, null, null, null);
            }
            rs.close();
            stm.close();

            sql = "SELECT *FROM funcionarios WHERE tipo = '" + TiposFuncionarios.MOTORISTA.getNome() + "'";
            stm = ManagerConnection.connect().prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                if (driver.getId() == rs.getInt("id")) {
                    driver.setCpf(rs.getString("cpf"));
                    driver.setName(rs.getString("nome"));
                    driver.setRg(rs.getString("rg"));
                    driver.setSalary(rs.getFloat("salario"));
                    driver.setWorkload(rs.getFloat("carga_horaria"));
                    driver.setType(rs.getString("tipo"));
                }
            }

            return driver;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerConnection.closeConnection();
        }

    }

    public List<Driver> list() {
        java.sql.PreparedStatement stm;
        try {
            String sql = "SELECT *FROM motoristas";
            stm = ManagerConnection.connect().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                driver = new Driver(rs.getInt("id_motorista"), rs.getString("cnh"),
                        rs.getInt("id_funcionario"), 0, 0, null, null, null, null);
                listDriver.add(driver);
            }
            rs.close();
            stm.close();
            sql = "SELECT *FROM funcionarios WHERE tipo = '"+ TiposFuncionarios.MOTORISTA.getNome() +"'";
            for (Driver drive : listDriver) {
                stm = ManagerConnection.connect().prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    if (drive.getId() == rs.getInt("id")) {
                        driver.setCpf(rs.getString("cpf"));
                        driver.setName(rs.getString("nome"));
                        driver.setRg(rs.getString("rg"));
                        driver.setSalary(rs.getFloat("salario"));
                        driver.setWorkload(rs.getFloat("carga_horaria"));
                        driver.setType(rs.getString("tipo"));
                    }
                }
            }
            rs.close();
            stm.close();
            return listDriver;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ManagerConnection.closeConnection();
        }
    }

}
