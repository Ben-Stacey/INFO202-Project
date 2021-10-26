/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.util.Collection;
import java.util.Map;

/**
 *
 * @author benstacey
 */
public interface ProductDAO {

    Collection<Product> filterByCategory(String category);

    Collection<String> getCategories();

    Collection<Product> getProduct();

    void removeProduct(Product p);

    void saveProduct(Product p);

    Product searchById(String productId);
    
}
