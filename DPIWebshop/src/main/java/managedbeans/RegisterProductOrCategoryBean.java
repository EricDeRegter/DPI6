package managedbeans;

import model.Category;
import model.Product;
import model.Webshop;
import service.CategoryService;
import service.ProductService;
import service.WebshopService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.IOException;

/**
 * Created by Eric on 04-06-16.
 */
@ManagedBean
public class RegisterProductOrCategoryBean {

    @Inject
    private ProductService productService;

    @Inject
    private CategoryService categoryService;

    @Inject
    private WebshopService webshopService;

    @ManagedProperty(value="#{param.webshopname}")
    private String webshopname;

    private Product product;

    private Category category;

    private Long categoryId;

    private String errormessageProduct;

    private String errormessageCategory;

    private String succesmessageProduct;

    private String succesmessageCategory;

    private Webshop webshop;

    @PostConstruct
    public void init() {
        try {

            webshop = webshopService.getWebshopWithName(webshopname);
            product = new Product();
            category = new Category();
            category.setWebshop(webshop);
        } catch (Exception ex) {
            String contextpath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(contextpath + "/shop/" + this.webshopname);
            } catch (IOException exception) {
                //Could not redirect
            }
        }

    }


    //<editor-fold desc="Getters and setters">
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getErrormessageProduct() {
        return errormessageProduct;
    }

    public void setErrormessageProduct(String errormessageProduct) {
        this.errormessageProduct = errormessageProduct;
    }

    public String getErrormessageCategory() {
        return errormessageCategory;
    }

    public void setErrormessageCategory(String errormessageCategory) {
        this.errormessageCategory = errormessageCategory;
    }

    public String getWebshopname() {
        return webshopname;
    }

    public void setWebshopname(String webshopname) {
        this.webshopname = webshopname;
    }

    public Webshop getWebshop() {
        return webshop;
    }

    public void setWebshop(Webshop webshop) {
        this.webshop = webshop;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getSuccesmessageProduct() {
        return succesmessageProduct;
    }

    public void setSuccesmessageProduct(String succesmessageProduct) {
        this.succesmessageProduct = succesmessageProduct;
    }

    public String getSuccesmessageCategory() {
        return succesmessageCategory;
    }

    public void setSuccesmessageCategory(String succesmessageCategory) {
        this.succesmessageCategory = succesmessageCategory;
    }

    //</editor-fold>

    public void addNewProduct() {
        try {
            Category cat = categoryService.getCategoryById(categoryId, webshop.getId());
            product.setCategory(cat);
            productService.addProduct(product);
            succesmessageProduct = "Successfully added product";
        } catch (Exception ex) {
            errormessageProduct = ex.getMessage();
        }
    }

    public void addNewCategory() {
        try {
            category.setWebshop(webshop);
            categoryService.addCategroy(category);
            succesmessageCategory = "Successfully added category";
        } catch (Exception ex) {
            errormessageCategory = ex.getMessage();
        }
    }

}
