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
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BugController {
    private final BugService bugService;
    private final UserService userService;
    private final ProjectService projectService;

    public BugController(BugService bugService, UserService userService, ProjectService projectService) {
        this.bugService = bugService;
        this.userService = userService;
        this.projectService = projectService;
    }

    @RequestMapping(value = "/add-new-bug/{projectId}")
    public String addNewBug(Model model, @PathVariable Long projectId){
        List<User> users = userService.listOfUsers();
        model.addAttribute("users", users);

        Project project = projectService.findById(projectId);
        model.addAttribute("project", project);

        model.addAttribute("bug",new Bug());
        return "addnewbug";
    }

    @RequestMapping(value = "/dosave/{projectId}", method = RequestMethod.POST)
    public String saveNewBug(@Valid @ModelAttribute Bug bug, BindingResult bindingResult, @PathVariable Long projectId){
        if(bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
            for(ObjectError error : errors) {
                System.out.println("This is the error: " +error);
            }
            return "error";
        }
//        User user = userService.findById(bug.getUser().getId());
//        if(user == null){
//            System.out.println("USER is NULL");
//        }
        Project project = projectService.findById(projectId);
        if(project == null){
            System.out.println("PROJECT is NULL");
        }
        bug.setStatus("UNASSIGNED");
        bug.setProject(project);
//        bug.setUser(user);
        bugService.saveBug(bug);
        return "registrationcomplete";
    }
    @RequestMapping(value = "/{userFirstName}/assignBug/{bugId}")
    public String assignBug (@PathVariable String userFirstName, @PathVariable Long bugId){
        User userDB = userService.findUserByFirstName(userFirstName);
        Bug bugDB = bugService.findByBugId(bugId);

        bugDB.setUser(userDB);
        bugDB.setStatus("In Progress");

        bugService.assignBug(bugDB);

        return "registrationcomplete";
    }
    @RequestMapping(value = "/list-of-project-bugs/{projectId}")
    public String listOfProjectBugs(Model model, @PathVariable Long projectId){
        List<Bug>bugs = bugService.findByProjectId(projectId);
        Project project = projectService.findById(projectId);

        model.addAttribute("project", project);
        model.addAttribute("bugs",bugs);
        return "listofprojectspecificbug";
    }
    @RequestMapping("/list-of-all-bugs")
    public String listOfAllBugs(Model model){
        List<Bug> bugs = bugService.findAllBugs();
        model.addAttribute("bugs", bugs);
        return "listofallbugs";
    }

    @RequestMapping(value = "/markAsComplete/{bugId}")
    public String markAsComplete(@PathVariable Long bugId){
        Bug bugDB= bugService.findByBugId(bugId);
        bugDB.setStatus("Completed");
        bugService.completeBug(bugDB);
        return "registrationcomplete";
    }


}
