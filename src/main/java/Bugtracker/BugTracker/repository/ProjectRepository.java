package Bugtracker.BugTracker.repository;

import Bugtracker.BugTracker.model.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long> {
    Project findByName(String name);
}
