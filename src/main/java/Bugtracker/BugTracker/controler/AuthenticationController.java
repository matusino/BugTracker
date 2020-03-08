package Bugtracker.BugTracker.controler;

import Bugtracker.BugTracker.model.User;
import Bugtracker.BugTracker.repository.UserRepository;
import Bugtracker.BugTracker.service.UserService;
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
import java.util.List;

@Controller
public class AuthenticationController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthenticationController(UserRepository userRepository, UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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

    @RequestMapping(value = "/{userFirstName}/edit/password")
    public String changePassword(Model model, @PathVariable String userFirstName){
        User user = userService.findUserByFirstName(userFirstName);

        model.addAttribute("user", user);

        return "changepassword";
    }

    @RequestMapping(value = "/{userFirstName}/save/password")
    public String doChangePassword(@Valid @ModelAttribute User user, BindingResult bindingResult, @PathVariable String userFirstName ) throws ParseException {
        if(bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error : errors) {
                System.out.println("This is the error: " + error);
            }
            return "changepassword";
        }
        User userDB = userService.findUserByFirstName(userFirstName);

        String existingPassword = user.getPassword();
        String newPassword = user.getNewPassword();
        String dbPassword = userDB.getPassword();

        if(bCryptPasswordEncoder.matches(existingPassword, dbPassword)){
            userDB.setPassword(bCryptPasswordEncoder.encode(newPassword));
        }else {
            return "changepassword";
        }

        userService.saveNewUser(userDB);

        return "succespasswordchange";
    }


}
