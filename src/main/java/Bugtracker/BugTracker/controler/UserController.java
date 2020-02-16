package Bugtracker.BugTracker.controler;

import Bugtracker.BugTracker.model.User;
import Bugtracker.BugTracker.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/list-of-users")
    public String listOfUsers(Model model){
        List<User> listOfUsers = userService.listOfUsers();

        model.addAttribute("users", listOfUsers);
        return "listofusers";
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

    @RequestMapping(value = "/{userFirstName}/edit")
    public String userProfile(Model model, @PathVariable String userFirstName){
        User user = userService.findUserByFirstName(userFirstName);
        model.addAttribute("user", user);

        return "userprofile";
    }

    @RequestMapping(value = "/{userFirstName}/save")
    public String doUpdateuser(@Valid @ModelAttribute User user, BindingResult bindingResult, @PathVariable String userFirstName ) throws ParseException {
        if(bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error : errors) {
                System.out.println("This is the error: " + error);
            }
            return "error";
        }

        User userDB = userService.findUserByFirstName(userFirstName);
        userService.updateUser(user, userDB);
        userDB.setBirthDate(user.getBirthDate());
        userService.saveNewUser(userDB);

        return "registrationcomplete";
    }

}
