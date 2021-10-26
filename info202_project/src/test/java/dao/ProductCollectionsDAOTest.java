/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import domain.Product;
import java.math.BigDecimal;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

/**
 *
 * @author benstacey
 */
public class ProductCollectionsDAOTest {
    
    private ProductDAO dao;
    private Product product;
    private Product product2;
    private Product product3;
    
    @BeforeEach
    public void setUp() {
      //dao = new ProductCollectionsDAO();
      dao = JdbiDaoFactory.getProductDAO();

      product = new Product();
      product.setProductId("Polkadot Widget1");
      product.setName("bloke");
      product.setDescription("bigman");
      product.setCategory("the");
      product.setListPrice(new BigDecimal("117.1445"));
      product.setQuantityInStock(new BigDecimal("7"));

      product2 = new Product();
      product2.setProductId("Polkadot Widget2");
      product2.setName("bloke2");
      product2.setDescription("bigman");
      product2.setCategory("bloke");
      product2.setListPrice(new BigDecimal("116.1445"));
      product2.setQuantityInStock(new BigDecimal("6"));
      
      product3 = new Product();
      product3.setProductId("Polkadot Widget3");
      product3.setName("boke3");
      product3.setDescription("thisman");
      product3.setCategory("said");
      product3.setListPrice(new BigDecimal("118.1445"));
      product3.setQuantityInStock(new BigDecimal("5"));
      
      dao.saveProduct(product);
      dao.saveProduct(product2);
    }
    
    @AfterEach
    public void tearDown() {
        dao.removeProduct(product);
        dao.removeProduct(product2);
        dao.removeProduct(product3);
    }

    @Test
    public void testSaveProduct() {
        assertThat(dao.getProduct(), hasSize(2));
        dao.saveProduct(product3);
        assertThat(dao.getProduct(), hasSize(3));
        assertThat(dao.getProduct(), hasItem(product3));
    }

    @Test
    public void testGetProduct() {
        assertThat(dao.getProduct(), hasItem(product));
        assertThat(dao.getProduct(), hasItem(product2));
        assertThat(dao.getProduct(), hasSize(2));
    }

    @Test
    public void testRemoveProduct() {
        assertThat(dao.getProduct(), hasSize(2));
        dao.removeProduct(product);
        assertThat(dao.getProduct(), hasSize(1));
    }
    
    @Test
    public void testGetCategories(){
        assertThat(dao.getCategories(), hasItem(product.getCategory()));
        assertThat(dao.getCategories(), hasItem(product2.getCategory()));
        assertThat(dao.getCategories(), hasSize(2));
    }
}
