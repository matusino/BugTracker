package Bugtracker.BugTracker.controler;

import Bugtracker.BugTracker.model.Bug;
import Bugtracker.BugTracker.model.Comment;
import Bugtracker.BugTracker.model.Project;
import Bugtracker.BugTracker.model.User;
import Bugtracker.BugTracker.service.BugService;
import Bugtracker.BugTracker.service.CommentService;
import Bugtracker.BugTracker.service.ProjectService;
import Bugtracker.BugTracker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BugController {
    private final BugService bugService;
    private final UserService userService;
    private final ProjectService projectService;
    private final CommentService commentService;

    public BugController(BugService bugService, UserService userService, ProjectService projectService, CommentService commentService) {
        this.bugService = bugService;
        this.userService = userService;
        this.projectService = projectService;
        this.commentService = commentService;
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
//     
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
    @RequestMapping(value = "/{userEmail}/assignBug/{bugId}")
    public String assignBug (@PathVariable String userEmail, @PathVariable Long bugId){
        User userDB = userService.findUserByEmail(userEmail);
        Bug bugDB = bugService.findByBugId(bugId);
        Comment comment = new Comment();

        bugDB.setUser(userDB);
        bugDB.setStatus("In Progress");
        comment.setBody("Status change: In Progress - Assigned to: " + userDB.getFirstName());
        comment.setBug(bugDB);
        commentService.saveComment(comment);

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

    //Rest controller test
    @RequestMapping("/list-of-all-bugs/2")
    public ResponseEntity listOfAllBugs2(Model model){
        List<Bug> bugs = bugService.findAllBugs();
        return new ResponseEntity<>(bugs, HttpStatus.OK);
    }


    @RequestMapping(value = "/{userEmail}/markAsComplete/{bugId}")
    public String markAsComplete(@PathVariable String userEmail, @PathVariable Long bugId){
        Bug bugDB= bugService.findByBugId(bugId);
        User userDB= userService.findUserByEmail(userEmail);
        Comment comment = new Comment();

        bugDB.setStatus("Completed");
        bugService.completeBug(bugDB);

        comment.setBody("Completed by: "+ userDB.getFirstName());
        comment.setBug(bugDB);
        commentService.saveComment(comment);


        return "registrationcomplete";
    }


}
