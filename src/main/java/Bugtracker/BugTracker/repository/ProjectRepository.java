package Bugtracker.BugTracker.repository;

import Bugtracker.BugTracker.model.Project;
import Bugtracker.BugTracker.model.Role;
import Bugtracker.BugTracker.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {
    Project findByName(String name);

}
