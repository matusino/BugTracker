package Bugtracker.BugTracker.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    private String description;

    private String status;
//Development: The project is under development phase.
//Release: A new patch of the project is released for testing.
//Stable: When the project development is stable for testing.
//Obsolete: When the project is old or not used currently.

    @OneToMany(mappedBy = "project")
    private List<Bug> bugs = new ArrayList<>();

    public Project() {
    }

    public Long getId() {
        return Id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Bug> getBugs() {
        return bugs;
    }

    public void setBugs(List<Bug> bugs) {
        this.bugs = bugs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(Id, project.Id) &&
                Objects.equals(name, project.name) &&
                Objects.equals(description, project.description) &&
                Objects.equals(bugs, project.bugs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, name, description, bugs);
    }
}
