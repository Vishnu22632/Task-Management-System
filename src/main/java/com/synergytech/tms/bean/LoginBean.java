
package com.synergytech.tms.bean;

import com.synergytech.tms.model.User;
import com.synergytech.tms.repository.UserRepository;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@RequestScoped
public class LoginBean {

    @Inject
    private UserRepository userRepository;

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        User user = userRepository.findUserByEmailAndPassword(email, password);
        if (user != null) {
            // Redirect to the welcome page or dashboard
            return "home.xhtml?faces-redirect=true";
        } else {
            // Show error message on the same page
            javax.faces.context.FacesContext.getCurrentInstance().addMessage(null,
                new javax.faces.application.FacesMessage("Invalid email or password."));
            return null; // stay on the same page
        }
    }
}