/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.ProductDAO;
import io.jooby.Jooby;

/**
 *
 * @author benstacey
 */
public class ProductModule extends Jooby {

    public ProductModule(ProductDAO dao) {
        get("/api/products", ctx -> dao.getProduct());

        get("/api/products/{id}", ctx -> {
            String id = ctx.path("id").value();
            return dao.searchById(id);
        });
        
        get("/api/categories", ctx -> dao.getCategories());
        
        get("/api/categories/{category}", ctx ->{
            String category = ctx.path("category").value();
            return dao.filterByCategory(category);
        });
    }
}
