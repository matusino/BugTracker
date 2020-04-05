package Bugtracker.BugTracker.controler;

import Bugtracker.BugTracker.helper.PasswordChangeHelper;
import Bugtracker.BugTracker.model.User;
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
import java.util.List;

@Controller
public class AuthenticationController {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthenticationController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @RequestMapping(value = "/")
    public String index(){
        return "authentication/login";
    }

    @RequestMapping(value = "/login")
    public String loginForm(){
        return "authentication/login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationForm(Model model){
        model.addAttribute("user", new User());
        return "authentication/registration";
    }
    @RequestMapping(value = "/edit/password", method = RequestMethod.GET)
    public String changePassword(Model model){
        model.addAttribute("passwordChangeHelper", new PasswordChangeHelper());
        return "authentication/passwordchange";
    }

    @RequestMapping(value = "/doregistration", method = RequestMethod.POST)
    public String doRegistration(@Valid @ModelAttribute User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
            for(ObjectError error : errors) {
                System.out.println("This is the error: " +error);
            }
            return "authentication/registration";
        }
        userService.saveUser(user);
        return "authentication/registrationcomplete";
    }

    @RequestMapping(value = "/{userFirstName}/save/password", method = RequestMethod.POST)
    public String doChangePassword(@Valid @ModelAttribute PasswordChangeHelper passwordHelp, BindingResult bindingResult, @PathVariable String userFirstName ){
        if(bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error : errors) {
                System.out.println("This is the error: " + error);
            }
            return "authentication/passwordchange";
        }
        User userDB = userService.findUserByEmail(userFirstName);

        String passwordCheck = passwordHelp.getOldPassword();
        String newPassword = passwordHelp.getNewPassword();
        String dbPassword = userDB.getPassword();

        if(bCryptPasswordEncoder.matches(passwordCheck, dbPassword)){
            userDB.setPassword(bCryptPasswordEncoder.encode(newPassword));
        }else {
            return "authentication/incorectpassword";
        }

        userService.saveNewUser(userDB);

        return "authentication/succespasswordchange";
    }
}
