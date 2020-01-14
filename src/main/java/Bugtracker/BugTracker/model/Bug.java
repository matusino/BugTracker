package Bugtracker.BugTracker.model;

import java.util.Date;
import java.util.List;

public class Bug {

    private Long id;
    private String title;
    private String project;
    private Date creation;
    private Date completion;
    private List<User> users;

}
