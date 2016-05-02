/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.jrTransportadora.model.Services;

import br.com.ifpb.jrTransportadora.model.dao.DriverDAO;
import br.com.ifpb.jrTransportadora.model.dao.TransportsDAO;
import br.com.ifpb.jrTransportadora.model.dao.VehicleDAO;
import br.com.ifpb.jrTransportadora.model.entities.Driver;
import br.com.ifpb.jrTransportadora.model.entities.Transports;
import br.com.ifpb.jrTransportadora.model.entities.Vehicle;
import br.com.ifpb.jrTransportadora.model.exceptions.AtributoInvalidoException;
import br.com.ifpb.jrTransportadora.model.exceptions.RemoveInvalideException;
import java.util.List;

/**
 *
 * @author rafael
 */
public class VehicleService {
   
    private VehicleDAO vehicleDAO;
    private TransportsDAO transportsDAO;
    private DriverDAO driverDAO;
    
    public VehicleService() {
        vehicleDAO = new VehicleDAO();
        transportsDAO = new TransportsDAO();
        driverDAO = new DriverDAO();
    }
    
    public void addVehicle(String brand, String model, String board, Driver drive, String year, String situacao) throws AtributoInvalidoException {
        for(Driver driver : driverDAO.list()){
            if(driver.getId() == drive.getId())
                throw new AtributoInvalidoException("O motorista ja está cadastrado em outro veículo!");
        }
        vehicleDAO.add(new Vehicle(brand, model, board, drive, year, situacao));
    }
    
    public void removeVehicle(int id) throws RemoveInvalideException{
        for(Transports tra : transportsDAO.list()){
            if(tra.getVehicle().getId() == id){
                throw new RemoveInvalideException("Existem um transporte ultilizando este veículo!");
            }
        }
        vehicleDAO.remove(id);
    }
    
    public void editVehicle(Vehicle vehicle, int id){
        vehicleDAO.edit(vehicle, id);
    }
    
    public Vehicle findVehicle(int id){
        return vehicleDAO.search(id);
    }
    public List<Vehicle> listVehicle (){
        return vehicleDAO.list();
    }
}
