package app.Controller;

import lombok.Data;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name = "userBean")
@ApplicationScoped
@Data
public class UserBean {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void logIn() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getSessionMap().put("LoggedIn" , true);
    }

    public String logOut (){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getSessionMap().remove("LoggedIn");
        return "toIndex";
    }
}
