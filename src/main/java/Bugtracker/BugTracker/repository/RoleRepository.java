package Bugtracker.BugTracker.repository;

import Bugtracker.BugTracker.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}
