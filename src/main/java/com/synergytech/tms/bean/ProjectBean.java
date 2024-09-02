package com.synergytech.tms.bean;

import com.synergytech.tms.model.Project;
import com.synergytech.tms.repository.ProjectRepository;
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
public class ProjectBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Project project = new Project();
    private List<Project> projects;

    @Inject
    private ProjectRepository projectRepository;

    // Method to prepare a new project
    public void prepareNewProject() {
        this.project = new Project();
    }
    
    
    
    private boolean editing = false;

    public boolean isEditing() {
        return editing;
    }

    public void setEditing(boolean editing) {
        this.editing = editing;
    }
    
    
    
/*
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
        setEditing(false);
        return null; // stay on the same page
    }
    
 */
    
    
    
    public String saveOrUpdateProject(){
        
        if(project.getId()==null){
            projectRepository.create(project);
            addMessage("Success", "Project created successfully !!!");
        }else{
            projectRepository.update(project);
            addMessage("Success", "Project Updated successfully !!!");
        }
        
        projectRepository.findAll();
        project =new Project();
        setEditing(false);
        return null;
        
    }
    
    /*

    // Method to handle both add and update
    public void saveProject() throws IOException {
        FacesMessage message;

        if (project.getId() == null) {
            // New project, create it
            projectRepository.create(project);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Project Added Successfully!");

        } else {
            // Existing project, update it
            projectRepository.update(project);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Project Updated Successfully!");
        }

        FacesContext.getCurrentInstance().addMessage(null, message);

        FacesContext.getCurrentInstance().getExternalContext().redirect("projectList.xhtml");
    }

    public String updateProject() {
        projectRepository.update(project);
        projects = projectRepository.findAll();
        return null;
    }

*/

    public void prepareEditProject(Project selectedProject) {
        this.project = selectedProject;
        setEditing(true);
    }

    public List<Project> getProjects() {
        if (projects == null) {
            projects = projectRepository.findAll();
        }
        return projects;
    }

    public String deleteProject(Long id) {
        projectRepository.delete(id);
        addMessage("Success", "Project deleted successfully !!!");
        return "projectList?faces-redirect=true";
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public long totalProjects() {
        return projectRepository.countTotalProjects();
    }
    
    
     // Add a method to add messages
    private void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    
    
    
    
}
