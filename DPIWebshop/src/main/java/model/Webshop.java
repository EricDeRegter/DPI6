package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric on 04-06-16.
 */
@Entity
@NamedQuery(name = "findWebshopByName", query = "SELECT w FROM Webshop w WHERE w.name = :name")
public class Webshop implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    private String description;

    @OneToMany(mappedBy = "webshop", fetch = FetchType.EAGER)
    private List<Category> categories = new ArrayList<>();

    private String imageUrl;

    public Webshop(){
        this.imageUrl = "https://www.drphillipscenter.org/resources/images/default.jpg";
    }

    public Webshop(String name, String description) {
        this();
        this.name = name;
        this.description = description;
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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    //</editor-fold>
}
