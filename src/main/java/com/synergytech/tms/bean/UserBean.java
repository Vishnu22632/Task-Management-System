package com.synergytech.tms.bean;

import com.synergytech.tms.model.User;
import com.synergytech.tms.repository.UserRepository;
import com.synergytech.tms.utils.PasswordUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class UserBean implements Serializable {

    @Inject
    private UserRepository userRepository;

    private User user = new User();
    private List<User> users;
    
    
    
    public String saveOrUpdateUser(){
        //Hash the password before saving the user
        String hashedPassword=PasswordUtil.hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        
        
        if(user.getId()==null){
            userRepository.create(user);
            addMessage("Success", "User created successfully !!!");
            
        }else{
            userRepository.update(user);
            addMessage("Success", "User updated successfully !!!");
        }
        
        users=userRepository.findAll();
        user=new User();
        return null; // stay on the same page
    }
    
    
    

//    public String createUser() {
//        // Hash the password before saving the user
//        String hashedPassword = PasswordUtil.hashPassword(user.getPassword());
//        user.setPassword(hashedPassword);
//
//        userRepository.create(user);
//        
//        // add message to the userList page
//        addMessage("Success", "User Created successfully!!!");
//        
//        
//        
//        
//        user = new User();
//        return "user_list?faces-redirect=true";
//    }

    public void prepareEditUser(User selectedUser) {
        this.user = selectedUser;
    }

//    public String updateUser() {
//        
//        // Hash the password before saving the user
//        String hashedPassword = PasswordUtil.hashPassword(user.getPassword());
//        user.setPassword(hashedPassword);
//
//        userRepository.update(user);
//        users = userRepository.findAll();
//        
//        addMessage("Success", "User updated successfully!!!");
//        
//        return null;
//    }

    public String deleteUser(Long id) {
        userRepository.delete(id);
        addMessage("success", "User deleted Successfully !!!");
        // Refresh the page by redirecting to the same page
        return "user_list?faces-redirect=true";
    }

    public List<User> getUsers() {
        if (users == null) {
            users = userRepository.findAll();
        }
        return users;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    // Add a method to add messages
    private void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
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
