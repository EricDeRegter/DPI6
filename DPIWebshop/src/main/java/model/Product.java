package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Map;

/**
 * Created by Eric on 04-06-16.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "findProductWithId", query = "SELECT p FROM Product p WHERE p.id = :productId AND p.category.id = :catId"),
        @NamedQuery(name = "findProductsWithCategoryId", query = "SELECT p FROM Product p WHERE p.category.id  = :catId AND p.category.webshop.id = :webshopId"),
        @NamedQuery(name = "findProductByName", query = "SELECT p FROM Product p WHERE p.name = :name")
})
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @XmlTransient
    @ManyToOne
    private Category category;

    @NotNull
    private String name;

    @NotNull
    private double price;

    @NotNull
    private String description;

    private String imageUrl;

    public Product(){
        this.imageUrl = "https://www.drphillipscenter.org/resources/images/default.jpg";
    }

    public Product(String name, String description, double price, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    //<editor-fold desc="Getters and setters">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    //</editor-fold>
}
