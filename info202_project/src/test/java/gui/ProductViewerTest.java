/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.ProductDAO;
import domain.Product;
import helpers.SimpleListModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.DialogFixture;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 *
 * @author benstacey
 */
public class ProductViewerTest {
    private ProductDAO dao;
    private Product product;
    private Product product2;
    private Product product3;
    private Robot robot;
    private DialogFixture fixture;
    
    @BeforeEach
    public void setUp() {
      //dao = new ProductCollectionsDAO();
      robot = BasicRobot.robotWithNewAwtHierarchy();
      robot.settings().delayBetweenEvents(300);
      
      Collection<Product> products = new ArrayList<>();
      Collection<String> categories = new ArrayList<>();
      Collection<Product> cats = new ArrayList<>();

      product = new Product();
      product.setProductId("Hello");
      product.setName("bloke");
      product.setDescription("bigman");
      product.setCategory("the");
      product.setListPrice(new BigDecimal("117.1445"));
      product.setQuantityInStock(new BigDecimal("7"));

      product2 = new Product();
      product2.setProductId("Polkadot Widget2");
      product2.setName("bloke2");
      product2.setDescription("bigman");
      product2.setCategory("said");
      product2.setListPrice(new BigDecimal("116.1445"));
      product2.setQuantityInStock(new BigDecimal("6"));
      
      product3 = new Product();
      product3.setProductId("Polkadot Widget3");
      product3.setName("boke3");
      product3.setDescription("thisman");
      product3.setCategory("said");
      product3.setListPrice(new BigDecimal("118.1445"));
      product3.setQuantityInStock(new BigDecimal("5"));
      
      products.add(product);
      products.add(product2);
      products.add(product3);
      
      categories.add("said");
      categories.add("the");
      
      cats.add(product2);
      cats.add(product3);
      
      dao = mock(ProductDAO.class);
      
      when(dao.getProduct()).thenReturn(products);
      when(dao.getCategories()).thenReturn(categories);
      when(dao.searchById("Hello")).thenReturn(product);
      when(dao.filterByCategory("said")).thenReturn(cats);
      
      Mockito.doAnswer(new Answer<Void>(){
          @Override
          public Void answer(InvocationOnMock invocation) throws Throwable{
              products.remove(product);
              return null;
          }
      }).when(dao).removeProduct(product);
      Mockito.doAnswer(new Answer<Void>(){
      public Void answer(InvocationOnMock invocation) throws Throwable{
              products.remove(product2);
              return null;
          }
      }).when(dao).removeProduct(product2);
    }
    
    @AfterEach
    public void tearDown(){
        fixture.cleanUp();
    }
    
    @Test
    public void testDisplay(){
        ProductViewer dialog = new ProductViewer(null, true, dao);
        
        fixture = new DialogFixture(robot, dialog);
        fixture.show().requireVisible();
        fixture.click();
        
        SimpleListModel model = (SimpleListModel) fixture.list("listProducts"). target().getModel();
        
        fixture.list("listProducts").requireItemCount(3);
        
        fixture.comboBox("cmbCategoryFilter").selectItem("said");
        
        assertThat(model, containsInAnyOrder(product2, product3));
        fixture.list("listProducts").requireItemCount(2);
        
        fixture.textBox("txtSearch").enterText("Hello");
        fixture.button("btnSearch").click();
        
        assertThat(model, containsInAnyOrder(product));
        fixture.list("listProducts").requireItemCount(1);
        
        verify(dao).getProduct();
        verify(dao).getCategories();
    }
    
    @Test
    public void testDelete(){
        ProductViewer dialog = new ProductViewer(null, true, dao);
        
        fixture = new DialogFixture(robot, dialog);
        fixture.show().requireVisible();
        fixture.click();
        
        SimpleListModel model = (SimpleListModel) fixture.list("listProducts"). target().getModel();
    
        fixture.list("listProducts").requireItemCount(3);
        
        fixture.list("listProducts").selectItem(product.toString());
        fixture.button("btnDelete").click();
        fixture.optionPane().requireVisible().yesButton().click();
        
        assertThat(model, containsInAnyOrder(product2, product3));
        fixture.list("listProducts").requireItemCount(2);
        
        verify(dao).removeProduct(product);
    }
}
