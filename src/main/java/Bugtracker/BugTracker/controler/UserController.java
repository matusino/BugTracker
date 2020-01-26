package Bugtracker.BugTracker.controler;

import Bugtracker.BugTracker.model.User;
import Bugtracker.BugTracker.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
