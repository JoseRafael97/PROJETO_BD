
import br.com.ifpb.jrTransportadora.model.Services.ChargeService;
import br.com.ifpb.jrTransportadora.model.Services.ClientService;
import br.com.ifpb.jrTransportadora.model.Services.DriverService;
import br.com.ifpb.jrTransportadora.model.Services.ProductService;
import br.com.ifpb.jrTransportadora.model.entities.Product;
import br.com.ifpb.jrTransportadora.model.exceptions.IdentificationException;
import br.com.ifpb.jrTransportadora.model.exceptions.PesoIvalidoException;
import br.com.ifpb.jrTransportadora.model.exceptions.isChargeException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rafael
 */
public class Main {
    public static void main(String []args) throws IdentificationException, PesoIvalidoException, isChargeException{
        System.out.println("ola");
        ArrayList<Product> prod = new ArrayList<Product>();
        
        ClientService client = new ClientService();
        ProductService productService = new ProductService();
        
        ChargeService chargeService = new ChargeService();
        DriverService driverService = new DriverService();
     //   driverService.removeDriver(1
        
        for(Product pro : chargeService.findCharge(13).getProducts()){
            System.out.println("-----"+ pro.getName());
        }
    }
    
    
}
