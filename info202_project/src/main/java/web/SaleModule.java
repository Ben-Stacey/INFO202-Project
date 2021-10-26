/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import io.jooby.Jooby;
import domain.Sale;
import dao.SaleDAO;
import io.jooby.StatusCode;

/**
 *
 * @author benstacey
 */
public class SaleModule extends Jooby{
    public SaleModule(SaleDAO dao){
        post("/api/sales", ctx -> {
            Sale sale = ctx.body().to(Sale.class);
            dao.save(sale);
            return ctx.send(StatusCode.CREATED);
        });
    }
}
