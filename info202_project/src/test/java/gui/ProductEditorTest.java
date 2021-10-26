/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.ProductDAO;
import domain.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.DialogFixture;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author benstacey
 */
public class ProductEditorTest {
    private ProductDAO dao;
    private DialogFixture fixture;
    private Robot robot;
    
    @BeforeEach
    public void setUp(){
        robot = BasicRobot.robotWithNewAwtHierarchy();
        robot.settings().delayBetweenEvents(10);
        
        Collection<String> categories = new ArrayList<>();
        categories.add("BigHorn");
        categories.add("SmallHorn");
        categories.add("GirthyHorn");
        
        dao = mock(ProductDAO.class);
        
        when(dao.getCategories()).thenReturn(categories);
       
              
    }
    
    @AfterEach
    public void tearDown(){
        fixture.cleanUp();
    }
    
    @Test
    public void testSave(){
        ProductEditor dialog = new ProductEditor(null, true, dao);
        
        fixture = new DialogFixture(robot, dialog);
        fixture.show().requireVisible();
        fixture.click();
        
        fixture.textBox("txtId").enterText("2334");
        fixture.textBox("txtName").enterText("Horn");
        fixture.comboBox("cmbOne").selectItem("BigHorn");
        fixture.textBox("txtPrice").enterText("7.70");
        fixture.textBox("txtQty").enterText("5");
        
        fixture.button("btnSave").click();
        
        ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);
        
        verify(dao).saveProduct(argument.capture());
        
        Product savedProduct = argument.getValue();
        
        assertThat("Ensure the Id was saved", savedProduct, hasProperty("productId", equalTo("2334")));
        assertThat("Ensure the name was saved", savedProduct, hasProperty("name", equalTo("Horn")));
        assertThat("Ensure the cat was saved", savedProduct, hasProperty("category", equalTo("BigHorn")));
        assertThat("Ensure the price was saved", savedProduct, hasProperty("listPrice", equalTo(new BigDecimal("7.70"))));
        assertThat("Ensure the stock was saved", savedProduct, hasProperty("quantityInStock", equalTo(new BigDecimal("5"))));
    }
    
}
