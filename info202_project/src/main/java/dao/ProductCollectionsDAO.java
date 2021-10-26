/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.util.Collection;
import java.util.ArrayList;
import domain.Product;
//import helpers.SimpleListModel;
import java.util.*;

/**
 *
 * @author benstacey
 */
public class ProductCollectionsDAO implements ProductDAO {
    private static final Collection<Product> products = new HashSet<Product>();
    private static final Collection<String> categories = new HashSet<String>();
    //private final SimpleListModel productsModel = new SimpleListModel();
    private static final Map<String, Product> productIds = new HashMap<String, Product>();
    private static final Multimap<String, Product> productCats = ArrayListMultimap.create();
    
    
    @Override
    public void saveProduct(Product p){
        products.add(p);
        categories.add(p.getCategory());
        productIds.put(p.getProductId(), p);
        productCats.put(p.getCategory(), p);
    }
    
    @Override
    public Collection<Product> getProduct(){
        return products;
    }
    
    @Override
    public void removeProduct(Product p){
        products.remove(p);
        productIds.remove(p.getProductId());
        productCats.remove(p.getCategory(), p);
    }
    
    @Override
    public Collection<String> getCategories(){
        return categories;
    }

    @Override
    public Product searchById(String productId){
        if(productIds.containsKey(productId)){
            Product p = productIds.get(productId);
            return p; 
        }else{
            return null;   
        }
    }
    
    @Override
    public Collection<Product> filterByCategory(String category){
        return productCats.get(category);
    }
}


