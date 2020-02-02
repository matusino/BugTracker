package Bugtracker.BugTracker.repository;

import Bugtracker.BugTracker.model.Bug;
import org.springframework.data.repository.CrudRepository;

public interface BugRepository extends CrudRepository<Bug, Long> {
}
