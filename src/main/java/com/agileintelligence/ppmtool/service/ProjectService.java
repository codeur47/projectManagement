package com.agileintelligence.ppmtool.service;

import com.agileintelligence.ppmtool.domain.Project;
import com.agileintelligence.ppmtool.exceptions.ProjectIdException;
import com.agileintelligence.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project createOrUpdate(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
                return projectRepository.save(project);
        }catch (Exception e) {
            throw new ProjectIdException("Project ID  '"+project.getProjectIdentifier().toUpperCase()+ "'  already exists");
        }
    }

    public Project findByProjectIdentifier(String projectIdentifier) {
        Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
        if(project == null) throw new ProjectIdException("Project ID '"+projectIdentifier.toUpperCase()+"' does not exists");
        return project;
    }
}