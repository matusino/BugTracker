package Bugtracker.BugTracker.repository;

import Bugtracker.BugTracker.model.Bug;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BugRepository extends CrudRepository<Bug, Long> {
    List<Bug> findByProjectId(Long id);
}
