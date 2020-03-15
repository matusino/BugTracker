package Bugtracker.BugTracker.configuration.Validator;

import Bugtracker.BugTracker.configuration.Annotation.UniqueEmail;
import Bugtracker.BugTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !userService.isEmailAlreadyInUse(value);
    }
}
