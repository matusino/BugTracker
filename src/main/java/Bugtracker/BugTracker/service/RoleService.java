package Bugtracker.BugTracker.service;

import Bugtracker.BugTracker.model.Role;
import Bugtracker.BugTracker.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> listOfRoles(){
        return roleRepository.findAll();
    }
}
