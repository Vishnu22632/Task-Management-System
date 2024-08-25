package com.synergytech.tms.repository;

import com.synergytech.tms.model.Project;
import javax.ejb.Stateless;

@Stateless
public class ProjectRepository extends BaseRepository<Project, Long> {

    public ProjectRepository() {
        super(Project.class);
    }

    public long countTotalProjects() {
        return count();
    }
}
