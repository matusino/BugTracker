package Bugtracker.BugTracker.controler;

import Bugtracker.BugTracker.model.User;
import Bugtracker.BugTracker.repository.UserRepository;
import Bugtracker.BugTracker.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AuthenticationController {

    private final UserRepository userRepository;
    private final UserService userService;

    public AuthenticationController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @RequestMapping(value = "/")
    public String index(){
        return "login";
    }

    @RequestMapping(value = "/login")
    public String loginForm(){
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationForm(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping(value = "/doregistration", method = RequestMethod.POST)
    public String doRegistration(@Valid @ModelAttribute User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
            for(ObjectError error : errors) {
                System.out.println("This is the error: " +error);
            }
            return "registration";
        }
        userService.saveUser(user);
        return "registrationcomplete";
    }

    @RequestMapping(value = "/test")
    public String test(){
        return "fragments/header2";
    }
}
