package managedbeans;

import model.Webshop;
import service.WebshopService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Eric on 05-06-16.
 */
@ManagedBean
public class ComparatorBean {

    @Inject
    private WebshopService webshopService;

    private List<Webshop> webshops;

    private String errorMessage;

    @PostConstruct
    public void init() {
        try {
            webshops = webshopService.getAllWebshops();
        }
        catch (Exception ex) {
            errorMessage = "Could not get all webshops";
        }
    }

    //<editor-fold desc="Getters and setters">
    public List<Webshop> getWebshops() {
        return webshops;
    }

    public void setWebshops(List<Webshop> webshops) {
        this.webshops = webshops;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    //</editor-fold>
}
