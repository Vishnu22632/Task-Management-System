package com.synergytech.tms.bean;

import com.synergytech.tms.model.User;
import com.synergytech.tms.repository.UserRepository;
import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
public class UserBean {

    @Inject
    private UserRepository userRepository;

    private User user = new User();
    private List<User> users;

    public String createUser() {
        userRepository.createUser(user);
        // Reset the form
        user = new User();
        return "user_list?faces-redirect=true";
    }
    
    
    public void prepareEditUser(User selectedUser) {
        this.user = selectedUser;  // Populate the current user object with the selected user's data
    }

    public String updateUser() {
        userRepository.updateUser(user);
        return "user_list?faces-redirect=true";
    }

    public String deleteUser(Long id) {
        userRepository.deleteUser(id);
        return "user_list?faces-redirect=true";
    }

    public List<User> getUsers() {
        if (users == null) {
            users = userRepository.findAllUsers();
        }
        return users;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // redirect to signupForm
    public void register() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("signupForm.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // redirect to loginForm
    public void login() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("loginForm.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



