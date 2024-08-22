package com.synergytech.tms.bean;

import com.synergytech.tms.model.Project;
import com.synergytech.tms.repository.ProjectRepository;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@ManagedBean(name = "projectBean")
@ViewScoped
public class ProjectBean implements Serializable {

    private Project project = new Project();
    private List<Project> projects;

    @Inject
    private ProjectRepository projectRepository;

    public void saveProject() throws IOException {
        projectRepository.save(project);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Project Added Successfully!"));
        // Redirect to the project list page

        FacesContext.getCurrentInstance().getExternalContext().redirect("projectList.xhtml");
    }

//    public void prepareEditUser(User selectedUser) {
//        this.user = selectedUser;  // Populate the current user object with the selected user's data
//    }
    public void prepareEditProject(Project selectedProject) {
        this.project = selectedProject;
    }

    // update project
//    public String updateUser() {
//        Transaction trans
//        Long accId;
//        Account acc = acRepo.findById(accId);
//
//        userRepository.updateUser(user);
//        return "user_list?faces-redirect=true";
//    }
    public String updateProject() {
        projectRepository.updateProject(project);
        return "projectList?faces-redirect=true";
    }

    public List<Project> getProjects() {
        if (projects == null) {
            projects = projectRepository.findAll();
        }
        return projects;
    }

    public String deleteProject(Long id) {
        projectRepository.deleteProject(id);
        return "projectList?faces-redirect=true";
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

}
