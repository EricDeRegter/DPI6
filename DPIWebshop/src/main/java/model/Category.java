package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

/**
 * Created by Eric on 04-06-16.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "findAllCategoriesForWebshopWithId", query = "SELECT c FROM Category c WHERE c.webshop.id = :webshopId "),
        @NamedQuery(name = "findCategoryWithId", query = "SELECT c from Category c WHERE c.id = :catId AND c.webshop.id = :webshopId"),
        @NamedQuery(name = "findCategoryByName", query = "SELECT c FROM Category c WHERE c.name = :name AND c.webshop.id = :webshopId")
})

public class Category {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @XmlTransient
    @ManyToOne
    private Webshop webshop;

    @OneToMany
    private List<Product> products;

    private String imageUrl;

    public Category(){
        this.imageUrl = "https://www.drphillipscenter.org/resources/images/default.jpg";
    }

    //<editor-fold desc="Getters and setters">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Webshop getWebshop() {
        return webshop;
    }

    public void setWebshop(Webshop webshop) {
        this.webshop = webshop;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    //</editor-fold>
}
