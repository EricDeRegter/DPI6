package service;

import dao.ProductDao;
import model.Category;
import model.Product;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Eric on 04-06-16.
 */
@Stateless
public class ProductService {

    @Inject
    private ProductDao productDao;

    public Product addProduct(Product product) throws Exception{
        return productDao.create(product);
    }

    public Product getProductById(Long id, Long catId) throws Exception {
        return productDao.findById(id, catId);
    }

    public List<Product> getAllProductsForCategory(Long catId, Long webshopId) throws Exception {
        return productDao.findAllProductsInCategory(catId, webshopId);
    }
}
