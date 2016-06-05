package service;

import dao.CategoryDao;
import model.Category;
import model.Product;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Eric on 04-06-16.
 */
@Stateless
public class CategoryService {

    @Inject
    private CategoryDao categoryDao;

    @Inject
    private ProductService productService;

    public Category constructCategory(Category category) throws Exception {
        category.setProducts(productService.getAllProductsForCategory(category.getId(), category.getWebshop().getId()));
        return category;
    }

    public Category addCategroy(Category category) throws Exception {
        return categoryDao.create(category);
    }

    public Category getCategoryById(Long id, Long webshopid) throws Exception {
        Category category = categoryDao.findById(id, webshopid);
        return constructCategory(category);
    }

    public List<Category> getAllCategoriesForWebshop(Long webshopId) throws Exception {
        return categoryDao.findAllCategoriesInWebshop(webshopId);
    }
}
