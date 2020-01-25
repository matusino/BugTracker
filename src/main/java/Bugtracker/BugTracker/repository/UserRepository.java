package Bugtracker.BugTracker.repository;

import Bugtracker.BugTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long > {
    User findByEmailAddress(String email);
    Optional<User> findByFirstName(String name);
}
