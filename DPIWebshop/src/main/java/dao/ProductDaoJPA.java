package dao;


import model.Product;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Eric on 04-06-16.
 */
@Named
public class ProductDaoJPA implements ProductDao {

    @PersistenceContext(name = "dpiPU")
    private EntityManager entityManager;

    @Override
    public Product create(Product product) throws Exception {
        try {
            entityManager.persist(product);
            return entityManager.find(Product.class, product.getId());
        } catch (Exception ex) {
            throw new Exception("Could not add product to database", ex);
        }
    }

    @Override
    public Product findById(Long id, Long catId) throws Exception {
        try {
            return (Product) entityManager.createNamedQuery("findProductWithId").setParameter("productId", id).setParameter("catId", catId).getSingleResult();
        } catch (Exception ex) {
            throw new Exception("Could not find product with id: " + id + " and category id: " + catId);
        }
    }

    @Override
    public List<Product> findAllProductsInCategory(Long catId, Long webshopId) throws Exception {
        try {
            return entityManager.createNamedQuery("findProductsWithCategoryId").setParameter("catId", catId).setParameter("webshopId", webshopId).getResultList();
        } catch (Exception ex) {
            throw new Exception("Could not get products from category with id: " + catId + " and webshop id: " + webshopId);
        }
    }

    @Override
    public Product findByName(String name) throws Exception {
        try {
            return (Product) entityManager.createNamedQuery("findProductByName").setParameter("name", name).getSingleResult();
        } catch (NoResultException ex) {
            throw new NoResultException("Product " + name + " does not exist");
        }
    }
}
