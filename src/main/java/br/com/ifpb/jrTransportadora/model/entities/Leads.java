package br.com.ifpb.jrTransportadora.model.entities;

/**
 *
 * @author rafael
 */
public class Leads {

    private int id;
    private Vehicle vehicle;
    private Driver driver;

    public Leads(Vehicle vehicle, Driver driver) {
        this.vehicle = vehicle;
        this.driver = driver;
    }

    public Leads(int id, Vehicle vehicle, Driver driver) {
        this.id = id;
        this.vehicle = vehicle;
        this.driver = driver;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

}
