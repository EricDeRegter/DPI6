package managedbeans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 * Created by Eric on 29-05-16.
 */
@ManagedBean
public class WebshopBean {

    @ManagedProperty(value="#{param.webshopname}")
    private String webshopname;

    @PostConstruct
    public void init() {
        //init the bean

    }

    public String getWebshopname() {

        return webshopname;
    }

    public void setWebshopname(String webshopname) {
        this.webshopname = webshopname;
    }
}
