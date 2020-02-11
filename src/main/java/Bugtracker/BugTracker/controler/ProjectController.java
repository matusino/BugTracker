package Bugtracker.BugTracker.controler;

import Bugtracker.BugTracker.model.Project;
import Bugtracker.BugTracker.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/admin/addproject", method = RequestMethod.POST)
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

    @RequestMapping(value = "/modify/project/{projectId}")
    public String modifyProject(@PathVariable Long projectId, Model model){
        Project project = projectService.findById(projectId);
        model.addAttribute("project", project);
        return "modifyproject";
    }

    @RequestMapping(value = "/domodify/{projectId}", method = RequestMethod.POST)
    public String doModify(@PathVariable Long projectId, @ModelAttribute Project project){
       Project projectDB = projectService.findById(projectId);

       projectDB.setName(project.getName());
       projectDB.setStatus(project.getStatus());
       projectDB.setDescription(project.getDescription());

       projectService.createNewProject(projectDB);

       return "registrationcomplete";
    }
//skusit dorobit databazu na komnety aby k progresu ci projeckut alebo bugu
}
