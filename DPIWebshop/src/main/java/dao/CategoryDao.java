package dao;

import model.Category;

import java.util.List;

/**
 * Created by Eric on 04-06-16.
 */
public interface CategoryDao {
    Category create(Category category) throws Exception;

    List<Category> findAllCategoriesInWebshop(Long webshopId) throws Exception;

    Category findById(Long id, Long webshopId) throws Exception;

    Category findCategoryByName(String name, Long webshopId) throws Exception;
}
