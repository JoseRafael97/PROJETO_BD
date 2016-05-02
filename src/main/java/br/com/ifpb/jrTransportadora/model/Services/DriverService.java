/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.jrTransportadora.model.Services;

import br.com.ifpb.jrTransportadora.model.dao.DriverDAO;
import br.com.ifpb.jrTransportadora.model.dao.VehicleDAO;
import br.com.ifpb.jrTransportadora.model.entities.Driver;
import br.com.ifpb.jrTransportadora.model.entities.Employee;
import br.com.ifpb.jrTransportadora.model.entities.Vehicle;
import br.com.ifpb.jrTransportadora.model.exceptions.RemoveInvalideException;
import java.util.List;

/**
 *
 * @author rafael
 */
public class DriverService {

    private DriverDAO driverDAO;
    private VehicleDAO vehicleDAO;

    public DriverService() {
        driverDAO = new DriverDAO();
        vehicleDAO = new VehicleDAO();
    }

    public void addEployer(String cnh, float salario, float cargaHoraria, String cpf, String rg, String nome, String tipo) {
        driverDAO.add(new Driver(cnh, salario, cargaHoraria, cpf, rg, nome, tipo));
    }

    public void removeDriver(int id) throws RemoveInvalideException {
        for (Vehicle vehicle : vehicleDAO.list()) {
            if (driverDAO.search(id).getIdDriver() == vehicle.getDriver().getIdDriver()) {
                throw new RemoveInvalideException("Existe um Veículo cadastrado com esse motorista!");
            }
        }
        driverDAO.remove(id);
    }

    public void editDriver(Employee employee, int id) {
        if (employee instanceof Driver) {
            driverDAO.edit(((Driver) employee), id);
        }
    }

    public Employee fingDriver(int id) {
        return driverDAO.search(id);
    }

    public List<Driver> listDriver() {
        return driverDAO.list();
    }
}
