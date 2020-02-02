package Bugtracker.BugTracker.repository;

import Bugtracker.BugTracker.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
