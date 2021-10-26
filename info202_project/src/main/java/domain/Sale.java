/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author benstacey
 */
public class Sale {
    private Integer saleId;
    private LocalDateTime date;
    private String status;
    private Customer customer = new Customer();
    private Collection<SaleItem> items = new ArrayList<SaleItem>();
    
    @Override 
    public String toString(){
        return "Sale{" + "saleId=" + saleId + ", date=" + date + ", status=" + status + ", customer=" + customer + ", items=" + items + "}";
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setSaleItem(Collection<SaleItem> saleItem) {
        this.items = saleItem;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Collection<SaleItem> getSaleItem() {
        return items;
    }
    
    public BigDecimal getTotal(){
        BigDecimal sum = new BigDecimal("0");
        for(SaleItem s: items){
            sum = sum.add(s.getItemTotal());
        }
        return sum;
    }
    
    public void addItem(SaleItem saleItem){
        items.add(saleItem);
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSaleId() {
        return saleId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public Collection<SaleItem> getItems() {
        return items;
    }

    public void setItems(Collection<SaleItem> items) {
        this.items = items;
    }
}
