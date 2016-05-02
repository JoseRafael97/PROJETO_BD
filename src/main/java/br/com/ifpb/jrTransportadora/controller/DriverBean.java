/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.jrTransportadora.controller;

import br.com.ifpb.jrTransportadora.model.Services.DriverService;
import br.com.ifpb.jrTransportadora.model.entities.Client;
import br.com.ifpb.jrTransportadora.model.entities.Driver;
import br.com.ifpb.jrTransportadora.model.exceptions.RemoveInvalideException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author rafael
 *
 *
 */
@ManagedBean
@ViewScoped
public class DriverBean implements Serializable {

    private DriverService employeeService;
    private Driver driver;
    private List<Driver> listEmployer;
    private Driver driveSelected;
    private String empSelected;
    private boolean campoCNH;

    public DriverBean() {
        employeeService = new DriverService();
        driver = new Driver();
        listEmployer = employeeService.listDriver();
        campoCNH = false;
    }

    public void addEmployer() {
        driver.setType(TiposFuncionarios.MOTORISTA.getNome());
        employeeService.addEployer(driver.getCnh(), driver.getSalary(), driver.getWorkload(), driver.getCpf(), driver.getRg(),
                driver.getName(), driver.getType());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empregado criado com sucesso!","consulte a tabela funários!"));

    }

    public void removeEmployer() {
        try {
            employeeService.removeDriver(driveSelected.getIdDriver());
            listEmployer.remove(driveSelected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empregado removido com sucesso!", String.valueOf(driveSelected.getId())));

        } catch (RemoveInvalideException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage(), "Verifique a tabela Veículos"));
            Logger.getLogger(DriverBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean bloqueiaCampo() {
        if (!empSelected.equals(TiposFuncionarios.MOTORISTA.getNome())) {
            campoCNH = true;
        }
        return campoCNH;
    }

    public void onRowEdit(RowEditEvent event) {
        Driver drive = (Driver) event.getObject();
        employeeService.editDriver(drive, drive.getId());
        FacesMessage msg = new FacesMessage("Cliente editado", String.valueOf(drive.getIdDriver()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", Integer.toString(((Client) event.getObject()).getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public DriverService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(DriverService employeeService) {
        this.employeeService = employeeService;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public List<Driver> getListEmployer() {
        return listEmployer;
    }

    public void setListEmployer(List<Driver> listEmployer) {
        this.listEmployer = listEmployer;
    }

    public Driver getDriveSelected() {
        return driveSelected;
    }

    public void setDriveSelected(Driver driveSelected) {
        this.driveSelected = driveSelected;
    }

    public boolean isCampoCNH() {
        return campoCNH;
    }

    public void setCampoCNH(boolean campoCNH) {
        this.campoCNH = campoCNH;
    }

    public String getEmpSelected() {
        return empSelected;
    }

    public void setEmpSelected(String empSelected) {
        this.empSelected = empSelected;
    }

}
