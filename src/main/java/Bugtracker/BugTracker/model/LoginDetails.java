package Bugtracker.BugTracker.model;

import javax.persistence.*;


public class LoginDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "old_password")
    private String oldPassword;

    @Column(name = "new_password")
    private String newPassword;

    @Column(name = "confirm_password")
    private String confirmPassword;

    private User user;



}
