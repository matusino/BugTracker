package Bugtracker.BugTracker.repository;

import Bugtracker.BugTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long > {
    Optional<User> findByEmail(String email);
    Optional<User> findByFirstName(String name);
    List<User> findByProjectList_Id(Long projectId);
}
