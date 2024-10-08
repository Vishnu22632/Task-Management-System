package com.synergytech.tms.repository;

import com.synergytech.tms.model.Task;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

@Stateless
public class TaskRepository extends BaseRepository<Task, Long> {

    public TaskRepository() {
        super(Task.class);
    }

    public List<Task> findByProject(Long projectId) {
        TypedQuery<Task> query = entityManager.createQuery(
                "SELECT t FROM Task t WHERE t.project.id = :projectId", Task.class);
        query.setParameter("projectId", projectId);
        return query.getResultList();
    }

    public Long countTasksByProject(Long projectId) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(t) FROM Task t WHERE t.project.id = :projectId", Long.class);
        query.setParameter("projectId", projectId);
        return query.getSingleResult();
    }

    
    // To count completed tasks on each project 
    public Long countCompletedTasksByProject(Long projectId) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(t) FROM Task t WHERE t.project.id = :projectId AND t.taskStatus = com.synergytech.tms.model.Task$TaskStatus.COMPLETED", Long.class);
        query.setParameter("projectId", projectId);
        return query.getSingleResult();
    }

    public void delete(Task task) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
