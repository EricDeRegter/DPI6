package dao;

import model.Product;

import java.util.List;

/**
 * Created by Eric on 04-06-16.
 */
public interface ProductDao {
    Product create(Product product) throws Exception;

    Product findById(Long id, Long catId) throws Exception;

    List<Product> findAllProductsInCategory(Long catId, Long webshopId) throws Exception;

    Product findByName(String name) throws Exception;
}
