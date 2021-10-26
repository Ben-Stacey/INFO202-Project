/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import domain.Sale;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author benstacey
 */
public final class SaleCollectionsDAO implements SaleDAO{
    
    private static final Map<Integer, Sale> sales = new HashMap<>();
    
    @Override
    public void save(Sale sale){
        System.out.println("Saving sale: " + sale);
        sales.put(sale.getSaleId(), sale);
    }
    /**
    @Override
    public Sale getSale(Integer saleID){
        return sales.get(saleID);
    }
    */
 }
