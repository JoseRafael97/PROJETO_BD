/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.jrTransportadora.model.Services;

/**
 *
 * @author rafael
 */
import br.com.ifpb.jrTransportadora.controller.SituacionEnumaration;
import br.com.ifpb.jrTransportadora.model.dao.ChargeDAO;
import br.com.ifpb.jrTransportadora.model.dao.ProductDAO;
import br.com.ifpb.jrTransportadora.model.dao.TransportsDAO;
import br.com.ifpb.jrTransportadora.model.entities.Charge;
import br.com.ifpb.jrTransportadora.model.entities.Product;
import br.com.ifpb.jrTransportadora.model.entities.Transports;
import br.com.ifpb.jrTransportadora.model.exceptions.PesoIvalidoException;
import br.com.ifpb.jrTransportadora.model.exceptions.RemoveInvalideException;
import br.com.ifpb.jrTransportadora.model.exceptions.isChargeException;
import java.util.List;

public class ChargeService {

    private static final float pesoMax = 2000;
    private ChargeDAO chagerDao;
    private ProductDAO productDAO;
    private TransportsDAO transportsDAO;

    public ChargeService() {
        chagerDao = new ChargeDAO();
        productDAO = new ProductDAO();
        transportsDAO = new TransportsDAO();
    }

    public void addCharge(String situacao, float peso, List<Product> products) throws PesoIvalidoException, isChargeException {
        for (Product pro : products) {
            if (pro.isCharge()) {
                throw new isChargeException("Existe(m) produtos ja cadastrados em outra carga");
            } else {
                if (peso > pesoMax) {
                    throw new PesoIvalidoException("peso superior ao peso limite estabelicido = " + pesoMax);
                } else {
                    pro.setSituacao(SituacionEnumaration.ANDAMENTO.getNome());
                    pro.setCharge(true);
                    productDAO.edit(pro, pro.getId());

                }
            }
        }

        chagerDao.add(new Charge(peso, situacao, products));

    }

    public void removeCharge(int id) throws RemoveInvalideException {
        for (Transports tra : transportsDAO.list()) {
            if (tra.getCharge().getId() == id) {
                throw new RemoveInvalideException("Existe um transporte ultilizando Está carga!");
            }
        }
        for (Product pro : chagerDao.search(id).getProducts()) {
            pro.setSituacao(SituacionEnumaration.INICIAL.getNome());
            pro.setCharge(false);
            productDAO.edit(pro, pro.getId());
        }
        chagerDao.remove(id);
    }

    public void editCharge(Charge charge, int id) {
        chagerDao.edit(charge, id);
    }

    public Charge findCharge(int id) {
        return chagerDao.search(id);
    }

    public List<Charge> listChagers() {
        return chagerDao.list();
    }

}
