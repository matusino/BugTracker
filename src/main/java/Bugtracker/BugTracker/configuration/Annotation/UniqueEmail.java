package Bugtracker.BugTracker.configuration.Annotation;

import Bugtracker.BugTracker.configuration.Validator.UniqueEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueEmailValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface UniqueEmail {

    public String message()default "There is already user with this email!";


    //these two are specific for validation and are just copy-pasted to make sure that this will work
    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default{};

}
