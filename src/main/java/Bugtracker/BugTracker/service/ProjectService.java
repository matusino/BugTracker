package Bugtracker.BugTracker.service;

import Bugtracker.BugTracker.model.Project;
import Bugtracker.BugTracker.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createNewProject(Project project){
        return projectRepository.save(project);
    }

    public Project findById(Long id){
        return projectRepository.findById(id).orElse(null);
    }

    public List<Project> listOfProjects(){
        List<Project> list = new ArrayList<>();
        projectRepository.findAll().forEach(list::add);
        return list;
    }

    public Project findProjectByName(String name){
        return projectRepository.findByName(name);
    }
}
