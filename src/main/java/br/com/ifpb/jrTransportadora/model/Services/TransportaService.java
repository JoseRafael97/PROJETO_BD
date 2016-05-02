/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.jrTransportadora.model.Services;

import br.com.ifpb.jrTransportadora.controller.SituacionEnumaration;
import br.com.ifpb.jrTransportadora.model.dao.ChargeDAO;
import br.com.ifpb.jrTransportadora.model.dao.ProductDAO;
import br.com.ifpb.jrTransportadora.model.dao.TransportsDAO;
import br.com.ifpb.jrTransportadora.model.dao.VehicleDAO;
import br.com.ifpb.jrTransportadora.model.entities.Charge;
import br.com.ifpb.jrTransportadora.model.entities.Product;
import br.com.ifpb.jrTransportadora.model.entities.Transports;
import br.com.ifpb.jrTransportadora.model.entities.Vehicle;
import br.com.ifpb.jrTransportadora.model.exceptions.IdentificationException;
import br.com.ifpb.jrTransportadora.model.exceptions.IntervaloDeDatasInvalido;
import br.com.ifpb.jrTransportadora.model.exceptions.RemoveInvalideException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

/**
 *
 * @author rafael
 */
public class TransportaService {

    private TransportsDAO transportsDAO;
    private VehicleDAO vehicleDAO;
    private ChargeDAO chargeDAO;
    private ProductDAO productDAO;

    public TransportaService() {
        transportsDAO = new TransportsDAO();
        vehicleDAO = new VehicleDAO();
        chargeDAO = new ChargeDAO();
        productDAO = new ProductDAO();
    }

    public void addTransport(Date dtDeparture, Date dtDelivery, Vehicle vehicle, Charge charge,
            String cep, String city, String adress) throws RemoveInvalideException, IntervaloDeDatasInvalido {
        if (dtDeparture.before(dtDelivery)) {
            if (vehicle.getSituacao().equals(SituacionEnumaration.VEHICLENORMAL.getNome())) {
                if (charge.getSituacao().equals(SituacionEnumaration.INICIAL.getNome())) {
                    vehicle.setSituacao(SituacionEnumaration.VEHICLEOCUPADO.getNome());
                    charge.setSituacao(SituacionEnumaration.CARGAENVIADA.getNome());
                    vehicleDAO.edit(vehicle, vehicle.getId());
                    for (Product pro : charge.getProducts()) {
                        pro.setSituacao(SituacionEnumaration.ENVIADO.getNome());
                        productDAO.edit(pro, pro.getId());
                    }
                    chargeDAO.edit(charge, charge.getId());
                    transportsDAO.add(new Transports(dtDeparture, dtDelivery, vehicle, charge, cep, city, adress));
                } else {
                    throw new RemoveInvalideException("Já existe um  transporte com a carga selecionado");
                }
            } else {
                throw new RemoveInvalideException("Já existe um  transporte com o  veículo selecionado");
            }
        } else {
            throw new IntervaloDeDatasInvalido("a data da saída não pode ser superior a data de entrega");
        }
    }

    public void removeTransport(int id) {
        Vehicle vehicle = transportsDAO.search(id).getVehicle();
        vehicle.setSituacao(SituacionEnumaration.VEHICLENORMAL.getNome());
        vehicleDAO.edit(vehicle, vehicle.getId());
        Charge charge = transportsDAO.search(id).getCharge();
        charge.setSituacao(SituacionEnumaration.INICIAL.getNome());
        chargeDAO.edit(charge, charge.getId());
        transportsDAO.remove(id);
    }

    public Transports findTransport(int id) {
        return transportsDAO.search(id);
    }

    public Transports rastreioCarga(int id) throws IdentificationException {
        for (Transports tra : transportsDAO.list()) {
            if (tra.getCharge().getId() == id) {
                return tra;
            } else {
                throw new IdentificationException("Não existe nenhuma carga em transporte com esse indetificador!");
            }
        }
        return null;
    }

    public Transports rastreioProduct(int id) throws IdentificationException {
        for (Transports tra : transportsDAO.list()) {
            for (Product pro : tra.getCharge().getProducts()) {
                if (pro.getId() == id) {
                    return tra;
                }
            }

        }
        throw new IdentificationException("Não existe nenhuma carga em transporte com esse indetificador!");
    }

    public int diferencaEntreDatasEmDias(Date dataDoEvento) {
        LocalDate data = LocalDate.now();

        LocalDate evento = dataDoEvento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        long diasRestantes = ChronoUnit.DAYS.between(data, evento);

        return (int) diasRestantes;
    }

    public List<Transports> listTransports() {
        return transportsDAO.list();
    }

    public TransportsDAO getTransportsDAO() {
        return transportsDAO;
    }

    public void setTransportsDAO(TransportsDAO transportsDAO) {
        this.transportsDAO = transportsDAO;
    }

}
