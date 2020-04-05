package Bugtracker.BugTracker.controler;

import Bugtracker.BugTracker.helper.UserUpdateHelper;
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
import org.springframework.web.bind.annotation.RequestMethod;

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
        return "user/listofusers";
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

    @RequestMapping(value = "/{userEmail}/edit", method = RequestMethod.GET)
    public String userProfile(Model model, @PathVariable String userEmail){
        User user = userService.findUserByEmail(userEmail);

        model.addAttribute("userUpdate", new UserUpdateHelper());
        model.addAttribute("user", user);

        return "user/userprofile";
    }

    @RequestMapping(value = "/{userEmail}/save", method = RequestMethod.POST)
    public String doUpdateuser(@Valid @ModelAttribute UserUpdateHelper user, BindingResult bindingResult, @PathVariable String userEmail )  {
        if(bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
            for(ObjectError error : errors) {
                System.out.println("This is the error: " +error);
            }
            return "redirect:/{userEmail}/edit";
        }

        User userDB = userService.findUserByEmail(userEmail);
        if(userDB.getPhoneNumber()!= null){
            userDB.setPhoneNumber(userDB.getPhoneNumber());
        }else {
            if(user.getPhoneNumber().isEmpty()){
                userDB.setPhoneNumber(null);
            }else if(!user.getPhoneNumber().isEmpty()){
                userDB.setPhoneNumber(user.getPhoneNumber());
            }
        }
        userDB.setBirthDate(user.getBirthDate());
        userService.saveNewUser(userDB);

        return "redirect:/{userEmail}/edit";
    }

    @RequestMapping(value = "/{userFirstName}/assign/{projectId}")
    public String assignProject(@PathVariable String userFirstName, @PathVariable Long projectId){
       User userDB = userService.findUserByEmail(userFirstName);
       Project projectDB = projectService.findById(projectId);
       List<Project> listOfProjects = new ArrayList<>();
       listOfProjects.add(projectDB);

       userDB.setProjectList(listOfProjects);

       userService.saveNewUser(userDB);

       return "redirect:/modify/project/{projectId}";
    }

}
