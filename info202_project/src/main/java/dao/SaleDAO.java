/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Sale;

/**
 *
 * @author benstacey
 */
public interface SaleDAO {
        
    void save(Sale sale);
    
    //Sale getSale(Integer saleId);
}