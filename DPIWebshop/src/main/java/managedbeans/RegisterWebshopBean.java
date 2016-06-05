package managedbeans;

import model.Webshop;
import service.WebshopService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 * Created by Eric on 04-06-16.
 */
@ManagedBean
public class RegisterWebshopBean {

    @Inject
    private WebshopService webshopService;

    private Webshop webshop;

    private String errorMessage;

    @PostConstruct
    public void init() {
        this.webshop = new Webshop();
    }

    //<editor-fold desc="Getters and setters">


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

    //</editor-fold>

    public void registerWebshop() {
        try {
            Webshop ws = webshopService.registerWebshop(webshop);
            String contextpath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(contextpath+"/shop/" + ws.getName());
        } catch (Exception ex) {
            errorMessage = ex.getMessage();
        }
    }

}
