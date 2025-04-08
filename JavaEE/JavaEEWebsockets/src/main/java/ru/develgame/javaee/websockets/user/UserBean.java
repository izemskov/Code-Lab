package ru.develgame.javaee.websockets.user;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serializable;

@Named("userBean")
@SessionScoped
public class UserBean implements Serializable {

    public String getUsername() {
        return FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
    }

    private static final long serialVersionUID = -7615739262702035246L;
}
