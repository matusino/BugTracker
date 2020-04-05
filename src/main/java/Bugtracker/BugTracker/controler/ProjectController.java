package Bugtracker.BugTracker.controler;

import Bugtracker.BugTracker.model.Bug;
import Bugtracker.BugTracker.model.Project;
import Bugtracker.BugTracker.model.User;
import Bugtracker.BugTracker.service.BugService;
import Bugtracker.BugTracker.service.ProjectService;
import Bugtracker.BugTracker.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProjectController {

    private final ProjectService projectService;
    private final BugService bugService;
    private final UserService userService;

    public ProjectController(ProjectService projectService, BugService bugService, UserService userService) {
        this.projectService = projectService;
        this.bugService = bugService;
        this.userService = userService;
    }

    @RequestMapping(value = "/admin/create-project")
    public String createProject(Model model){
        List<User> users = projectService.findByProjectManager();

        model.addAttribute("users", users);
        model.addAttribute("project", new Project());
        return "project/addproject";
    }

    @RequestMapping(value = "/admin/addproject", method = RequestMethod.POST)
    public String addProject(@Valid Project project, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "redirect:/admin/create-project";
        }
        projectService.createNewProject(project);
        return "redirect:/listofprojects";
    }

    @RequestMapping(value = "/listofprojects")
    public String listOfProject(Model model){
        List<Project> projects = projectService.listOfProjects();
        model.addAttribute("projects", projects);
        return "project/listofprojects";
    }

    @RequestMapping(value = "/modify/project/{projectId}")
    public String modifyProject(@PathVariable Long projectId, Model model){
        Project project = projectService.findById(projectId);
        List<Bug> bugs = bugService.findByProjectId(projectId);
        List<User> users = userService.findByProjectId(projectId);

        model.addAttribute("users", users);
        model.addAttribute("bugs", bugs);
        model.addAttribute("project", project);
        return "project/reviewproject";
    }

    @RequestMapping(value = "/domodify/{projectId}", method = RequestMethod.POST)
    public String doModify(@PathVariable Long projectId, @ModelAttribute Project project){
       Project projectDB = projectService.findById(projectId);

       projectDB.setName(project.getName());
       projectDB.setStatus(project.getStatus());
       projectDB.setDescription(project.getDescription());

       projectService.createNewProject(projectDB);

       return "redirect:/modify/project/{projectId}";
    }
}
