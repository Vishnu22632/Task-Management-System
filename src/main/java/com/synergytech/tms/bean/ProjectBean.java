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

     public void saveProject() throws IOException {
        projectRepository.create(project);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Project Added Successfully!"));
        FacesContext.getCurrentInstance().getExternalContext().redirect("projectList.xhtml");
    }

    public void prepareEditProject(Project selectedProject) {
        this.project = selectedProject;
    }

    public String updateProject() {
        projectRepository.update(project);
        projects = projectRepository.findAll();
        return null;
    }

    public List<Project> getProjects() {
        if (projects == null) {
            projects = projectRepository.findAll();
        }
        return projects;
    }

    public String deleteProject(Long id) {
        projectRepository.delete(id);
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
}
