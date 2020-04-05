package Bugtracker.BugTracker.helper;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Size;
import java.util.Date;

public class UserUpdateHelper {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    @Nullable
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

//    @Size(min = 5, max = 12,message = "Incorrect format!")
    private String phoneNumber;

    private String password;

    public UserUpdateHelper(Long id, String firstName, String lastName, String email, @Nullable Date birthDate, @Size(min = 5, max = 12, message = "Incorrect format!") String phoneNumber, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public UserUpdateHelper() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Nullable
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(@Nullable Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
