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
import javax.inject.Inject;

/**
 * Created by Eric on 29-05-16.
 */
@ManagedBean
public class WebshopBean {

    @Inject
    private WebshopService webshopService;

    @Inject
    private CategoryService categoryService;

    @Inject
    private ProductService productService;

    @ManagedProperty(value="#{param.webshopname}")
    private String webshopname;

    @ManagedProperty(value="#{param.catid}")
    private String catId;

    @ManagedProperty(value="#{param.productid}")
    private String productId;

    private Webshop webshop;

    private Category currentCategory;

    private Product currentProduct;

    private String errorMessage;

    @PostConstruct
    public void init() {
        //init the bean
        try {
            webshop = webshopService.getWebshopWithName(webshopname);
            if(catId != null) {
                currentCategory = categoryService.getCategoryById(Long.parseLong(catId), webshop.getId());
            }
            if(productId != null) {
                currentProduct = productService.getProductById(Long.parseLong(productId), Long.parseLong(catId));
            }
        } catch (Exception ex) {
            webshop = new Webshop();
            errorMessage = ex.getMessage();
        }
    }

    //<editor-fold desc="Getters and setters">
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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Category getCurrentCategory() {
        return currentCategory;
    }

    public void setCurrentCategory(Category currentCategory) {
        this.currentCategory = currentCategory;
    }

    public Product getCurrentProduct() {
        return currentProduct;
    }

    public void setCurrentProduct(Product currentProduct) {
        this.currentProduct = currentProduct;
    }

    //</editor-fold>
}
