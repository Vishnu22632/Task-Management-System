package com.synergytech.tms.bean;

import com.synergytech.tms.model.User;
import com.synergytech.tms.repository.UserRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class UserBean {

    @Inject
    private UserRepository userRepository;

    private User user = new User();
    private List<User> users;

    public String createUser() {
        userRepository.createUser(user);
        user = new User(); // Reset the form
        return "user_list?faces-redirect=true";
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
}
