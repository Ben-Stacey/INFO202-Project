/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.util.Collection;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 *
 * @author benstacey
 */
public interface ProductJdbiDAO extends ProductDAO{

    @Override
    @SqlQuery("SELECT * FROM PRODUCT WHERE PRODUCTID = :productId")
    @RegisterBeanMapper(Product.class)
    public Product searchById(@Bind("productId") String id);

    @Override
    @SqlUpdate("INSERT INTO PRODUCT (PRODUCTID, NAME, DESCRIPTION, CATEGORY, LISTPRICE, QUANTITYINSTOCK) VALUES (:productId, :name, :description, :category, :listPrice, :quantityInStock)")
    public void saveProduct(@BindBean Product p);

    @Override
    @SqlUpdate("DELETE FROM PRODUCT WHERE PRODUCTID = :productId")
    public void removeProduct(@BindBean Product p);

    @Override
    @SqlQuery("SELECT * FROM PRODUCT ORDER BY PRODUCTID")
    @RegisterBeanMapper(Product.class)
    public Collection<Product> getProduct();

    @Override
    @SqlQuery("SELECT DISTINCT CATEGORY FROM PRODUCT ORDER BY CATEGORY")
    public Collection<String> getCategories();

    @Override
    @SqlQuery("SELECT * FROM PRODUCT WHERE CATEGORY = :category")
    @RegisterBeanMapper(Product.class)
    public Collection<Product> filterByCategory(@Bind("category")String category);

}
    

