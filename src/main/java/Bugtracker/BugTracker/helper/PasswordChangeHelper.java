package Bugtracker.BugTracker.helper;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class PasswordChangeHelper {

    @Length(min = 5, message = "*Incorrect format of old password")
    @NotEmpty(message = "*Please provide your password")
    private String oldPassword;

    @Length(min = 5, message = "*Your new password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String newPassword;

    public PasswordChangeHelper(@Length(min = 5, message = "*Your password must have at least 5 characters") @NotEmpty(message = "*Please provide your password") String oldPassword, @Length(min = 5, message = "*Your password must have at least 5 characters") @NotEmpty(message = "*Please provide your password") String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public PasswordChangeHelper() {
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
