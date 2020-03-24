package Bugtracker.BugTracker.controler;

import Bugtracker.BugTracker.model.Project;
import Bugtracker.BugTracker.model.User;
import Bugtracker.BugTracker.service.ProjectService;
import Bugtracker.BugTracker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final ProjectService projectService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserService userService, ProjectService projectService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.projectService = projectService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @RequestMapping(value = "/list-of-users")
    public String listOfUsers(Model model){
        List<User> listOfUsers = userService.listOfUsers();

        model.addAttribute("users", listOfUsers);
        return "listofusers";
    }

    //REST controller test
    @RequestMapping(value = "/list-of-users/all")
    public ResponseEntity listOfUsers1(){
        List<User> listOfUsers = userService.listOfUsers();
        return new ResponseEntity<>(listOfUsers, HttpStatus.OK);
    }

    @RequestMapping("/main")
    public String renderMainPage(){
        return "main";
    }

    @RequestMapping("/deleteUser/{userId}")
    public String deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return "redirect:/list-of-users";
    }

    @RequestMapping(value = "/{userEmail}/edit")
    public String userProfile(Model model, @PathVariable String userEmail){
        User user = userService.findUserByEmail(userEmail);

        model.addAttribute("user", user);

        return "userprofile";
    }

    @RequestMapping(value = "/{userEmail}/save")
    public String doUpdateuser(@Valid @ModelAttribute User user, BindingResult bindingResult, @PathVariable String userEmail ) throws ParseException {
        if(bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error : errors) {
                System.out.println("This is the error: " + error);
            }
            return "userprofile";
        }
        User userDB = userService.findUserByEmail(userEmail);

        userService.updateUser(user, userDB);
        userDB.setBirthDate(user.getBirthDate());
        userService.saveNewUser(userDB);

        return "registrationcomplete";
    }

    @RequestMapping(value = "/{userFirstName}/assign/{projectId}")
    public String assignProject(@PathVariable String userFirstName, @PathVariable Long projectId){
       User userDB = userService.findUserByEmail(userFirstName);
       Project projectDB = projectService.findById(projectId);
       List<Project> listOfProjects = new ArrayList<>();
       listOfProjects.add(projectDB);

       userDB.setProjectList(listOfProjects);

       userService.saveNewUser(userDB);

       return "registrationcomplete";
    }

}
