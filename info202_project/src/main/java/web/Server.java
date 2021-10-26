/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.JdbiDaoFactory;
import dao.CustomerJdbiDAO;
import dao.ProductJdbiDAO;
import dao.SaleJdbiDAO;
import io.jooby.Jooby;
import io.jooby.ServerOptions;
import io.jooby.json.GsonModule;

/**
 *
 * @author benstacey
 */
public class Server extends Jooby{
    
    ProductJdbiDAO productDao = JdbiDaoFactory.getProductDAO();
    CustomerJdbiDAO customerDao = JdbiDaoFactory.getCustomerDAO();
    SaleJdbiDAO saleDao = JdbiDaoFactory.getSaleDAO();
    
    public static void main(String[] args) throws Exception {
        System.out.println("\nStarting Server.");
        new Server().start();
    }
    
    public Server(){
        setServerOptions(new ServerOptions().setPort(8080));
        install(new GsonModule());
        mount(new ProductModule(productDao));
        mount(new CustomerModule(customerDao));
        mount(new SaleModule(saleDao));
        mount(new StaticAssetModule());
    }
}
