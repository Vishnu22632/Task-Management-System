package com.synergytech.tms.repository;

import com.synergytech.tms.model.Project;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Stateless
public class ProjectRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Project project) {
        if (project.getId() == null) {
            entityManager.persist(project);
        } else {
            entityManager.merge(project);
        }

    }

    public Project findProjectById(Long id) {
        return entityManager.find(Project.class, id);
    }

    @Transactional
    public void deleteProject(Long id) {
        Project project = findProjectById(id);
        if (project != null) {
            entityManager.remove(project);
        }
    }

    public void updateProject(Project project) {
        entityManager.merge(project);
    }

    public List<Project> findAll() {
        return entityManager.createQuery("SELECT p FROM Project p", Project.class).getResultList();
    }
    
    
   
    
    
    // Method to count total number of projects
    public long countTotalProjects() {
        return entityManager.createQuery("SELECT COUNT(p) FROM Project p", Long.class).getSingleResult();
    }
    

}


