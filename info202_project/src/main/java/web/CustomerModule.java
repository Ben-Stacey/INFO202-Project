/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import domain.Customer;
import dao.CustomerDAO;
import io.jooby.StatusCode;
import io.jooby.Jooby;

/**
 *
 * @author benstacey
 */
public class CustomerModule extends Jooby{
    
    public CustomerModule(CustomerDAO dao){
        get("/api/customers/{username}", ctx ->{
            String username = ctx.path("username").value();
            return dao.getCustomer(username);
          });
        
        post("/api/register", ctx -> {
            Customer customer = ctx.body().to(Customer.class);
            dao.saveCustomer(customer);
            return ctx.send(StatusCode.CREATED);
       });
    }
}
