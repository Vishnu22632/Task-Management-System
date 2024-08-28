
package com.synergytech.tms.bean;

import com.synergytech.tms.model.Task;
import com.synergytech.tms.repository.ProjectRepository;
import com.synergytech.tms.repository.TaskRepository;
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
public class TaskBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    Task task=new Task();
    
    private List<Task> tasks;
    
    
    


    public List<Task> getTasks() {
        if(tasks==null){
            tasks=taskRepository.findAll();
        }
        
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    
    //getter for enum values
    public List<Task.TaskStatus> getTaskStatusValues(){
        return List.of(Task.TaskStatus.values());
    }
        
    
    @Inject
    private TaskRepository taskRepository;
    
    
    
    // save task
    public void saveTask() throws IOException{
        taskRepository.create(task);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Task Added Successfully"));
        FacesContext.getCurrentInstance().getExternalContext().redirect("projectView.xhtml");
    }
    
    
    public String deleteTask(Long id){
        taskRepository.delete(id);
        return "projectView.xhtml?faces-redirect=true";
    }
    
    
    
    
    
//    public String updateProject() {
//        projectRepository.update(project);
//        projects = projectRepository.findAll();
//        return null;
//    }
    
    
    
//     public void prepareEditProject(Project selectedProject) {
//        this.project = selectedProject;
//    }
    
    public String updateTask(){
        taskRepository.update(task);
        tasks = taskRepository.findAll();
        return null;
    }
        
    
    
    public void prepareEditTask(Task selectedTask){
        this.task=selectedTask;
    }
    

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public TaskRepository getTaskRepository() {
        return taskRepository;
    }

    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    
    
    
    
    
}
