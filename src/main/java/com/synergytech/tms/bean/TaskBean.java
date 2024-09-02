package com.synergytech.tms.bean;

import com.synergytech.tms.model.Project;
import com.synergytech.tms.model.Task;
import com.synergytech.tms.repository.ProjectRepository;
import com.synergytech.tms.repository.TaskRepository;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class TaskBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Task task;

    private List<Task> tasks;

    // list of projects
    private List<Project> projects;

    @Inject
    private TaskRepository taskRepository;

    @Inject
    private ProjectRepository projectRepository; // Inject projectRepository

    @PostConstruct
    public void init() {
        task = new Task();
        tasks = taskRepository.findAll();
        projects = projectRepository.findAll();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
    
    
    //getter for enum values
    public List<Task.TaskStatus> getTaskStatusValues() {
        return List.of(Task.TaskStatus.values());
    }
    
    
    // Save task
   public String saveTask() {
    try {
        // Check if the project is null or its ID is null
        System.err.println(task.getProject());
        if (task.getProject() == null || task.getProject().getId() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Project is required "));
            return null;
        }

        if (task.getId() == null) {
            taskRepository.create(task);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Task Added Successfully"));
        } else {
            taskRepository.update(task);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Task Updated Successfully"));
        }

        tasks = taskRepository.findAll(); // Refresh the task list

        return "viewProject?faces-redirect=true";

    } catch (Exception e) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        e.printStackTrace();
        return null;
    }
}

    public String deleteTask(Long id) {
        taskRepository.delete(id);
        return "projectView.xhtml?faces-redirect=true";
    }

    public String updateTask() {
        taskRepository.update(task);
        tasks = taskRepository.findAll();
        return null;
    }

    public void prepareEditTask(Task selectedTask) {
        this.task = selectedTask;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

}




