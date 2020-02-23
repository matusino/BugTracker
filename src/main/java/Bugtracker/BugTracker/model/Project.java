package Bugtracker.BugTracker.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String status;

    private String frontendLang;

    private String backendLang;

    @ManyToOne
    private User projectManager;

    private String databaseTechnology;

    @OneToMany(mappedBy = "project")
    private List<Bug> bugs = new ArrayList<>();

    public Project() {
    }

    public String getFrontendLang() {
        return frontendLang;
    }

    public void setFrontendLang(String frontendLang) {
        this.frontendLang = frontendLang;
    }

    public String getBackendLang() {
        return backendLang;
    }

    public void setBackendLang(String backendLang) {
        this.backendLang = backendLang;
    }

    public User getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(User projectManager) {
        this.projectManager = projectManager;
    }

    public String getDatabaseTechnology() {
        return databaseTechnology;
    }

    public void setDatabaseTechnology(String databaseTechnology) {
        this.databaseTechnology = databaseTechnology;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
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
        return Objects.equals(id, project.id) &&
                Objects.equals(name, project.name) &&
                Objects.equals(description, project.description) &&
                Objects.equals(bugs, project.bugs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, bugs);
    }
}
