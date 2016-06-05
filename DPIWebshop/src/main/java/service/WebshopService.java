package service;

import dao.CategoryDao;
import dao.WebshopDao;
import model.Webshop;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Eric on 04-06-16.
 */
@Stateless
public class WebshopService {

    @Inject
    private WebshopDao webshopDao;

    @Inject
    private CategoryService categoryService;

    public Webshop constructWebshop(Webshop webshop) throws Exception {
        webshop.setCategories(categoryService.getAllCategoriesForWebshop(webshop.getId()));
        return webshop;
    }

    public Webshop getWebshopWithName(String name) throws Exception {
        try {
            Webshop webshop = webshopDao.findByName(name);
            return constructWebshop(webshop);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public Webshop registerWebshop(Webshop webshop) throws Exception {
        return webshopDao.create(webshop);
    }

    public List<Webshop> getAllWebshops() throws Exception {
        return webshopDao.findAll();
    }
}
