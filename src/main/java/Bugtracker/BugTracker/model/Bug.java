package Bugtracker.BugTracker.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Objects;

@Entity
public class Bug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    @NotEmpty(message = "please provide title")
    private String title;
    @Column(name = "description")
    @NotEmpty(message = "please provide description")
    private String  description;

    private Date creation;//tuto to automaticky nastacit do konstruktova aby setovalo date
    private Date completion;//toto nejako pozret ako to updatnut tak ked sa to zavre

    @ManyToOne
    private User user;

    @ManyToOne
    private Project project;

    public Bug() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public Date getCompletion() {
        return completion;
    }

    public void setCompletion(Date completion) {
        this.completion = completion;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bug bug = (Bug) o;
        return Objects.equals(id, bug.id) &&
                Objects.equals(title, bug.title) &&
                Objects.equals(description, bug.description) &&
                Objects.equals(creation, bug.creation) &&
                Objects.equals(completion, bug.completion) &&
                Objects.equals(user, bug.user) &&
                Objects.equals(project, bug.project);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, creation, completion, user, project);
    }
}
