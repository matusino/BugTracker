package Bugtracker.BugTracker.controler;

import Bugtracker.BugTracker.model.Project;
import Bugtracker.BugTracker.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(value = "/admin/create-project")
    public String createProject(Model model){
        model.addAttribute("project", new Project());
        return "addproject";
    }

    @RequestMapping(value = "/admin/addproject")
    public String addProject(@Valid Project project, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/admin/create-project";
        }
        projectService.createNewProject(project);
        return "registrationcomplete";
    }

    @RequestMapping(value = "/listofprojects")
    public String listOfProject(Model model){
        List<Project> projects = projectService.listOfProjects();
        model.addAttribute("projects", projects);
        return "listofprojects";
    }
}
