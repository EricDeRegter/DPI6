package dao;

import model.Category;
import sun.reflect.annotation.ExceptionProxy;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Eric on 04-06-16.
 */
@Named
public class CategoryDaoJPA implements CategoryDao {

    @PersistenceContext(name = "dpiPU")
    private EntityManager entityManager;

    @Override
    public Category create(Category category) throws Exception {
        String errormessage = "Could not add category to database";

        try {
            findCategoryByName(category.getName(), category.getWebshop().getId());
            throw new IllegalArgumentException("Category already exists");
        }
        catch(NoResultException exception) {
            try {
                entityManager.persist(category);
                return entityManager.find(Category.class, category.getId());
            }catch (Exception ex) {
                throw new Exception(errormessage, ex);
            }
        }
        catch (IllegalArgumentException ex) {
            throw new Exception(ex.getMessage(), ex);
        }catch (Exception ex) {
            throw new Exception(errormessage, ex);
        }
    }

    @Override
    public List<Category> findAllCategoriesInWebshop(Long webshopId) throws Exception {
        try {
            return entityManager.createNamedQuery("findAllCategoriesForWebshopWithId").setParameter("webshopId", webshopId).getResultList();
        }
        catch (Exception ex) {
            throw new Exception("Could not get categories with webshopId " + webshopId + " from the database");
        }
    }

    @Override
    public Category findById(Long id, Long webshopId) throws Exception {
        try{
            return (Category) entityManager.createNamedQuery("findCategoryWithId").setParameter("catId", id).setParameter("webshopId", webshopId).getSingleResult();
        }
        catch(Exception ex) {
            throw new Exception("Could not get category with id: " + id + ", and webshop id: " + webshopId);
        }
    }

    @Override
    public Category findCategoryByName(String name, Long webshopId) throws Exception {
        try {
            return (Category) entityManager.createNamedQuery("findCategoryByName").setParameter("name", name).setParameter("webshopId", webshopId).getSingleResult();
        }
        catch(NoResultException ex) {
            throw new NoResultException("Category with name " + name + " not found");
        }
    }
}
