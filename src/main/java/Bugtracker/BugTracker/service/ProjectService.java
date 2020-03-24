package Bugtracker.BugTracker.service;

import Bugtracker.BugTracker.model.Project;
import Bugtracker.BugTracker.model.Role;
import Bugtracker.BugTracker.model.User;
import Bugtracker.BugTracker.repository.ProjectRepository;
import Bugtracker.BugTracker.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final RoleRepository roleRepository;

    public ProjectService(ProjectRepository projectRepository, RoleRepository roleRepository) {
        this.projectRepository = projectRepository;
        this.roleRepository = roleRepository;
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

    public List<User> findByProjectManager(){

        Role role = roleRepository.findByRole("ROLE_PROJECT_MANAGER");
        Set<User> users = role.getUsers();

        return new ArrayList<>(users);
    }
}
