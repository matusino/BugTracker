package Bugtracker.BugTracker.service;

import Bugtracker.BugTracker.model.Role;
import Bugtracker.BugTracker.model.User;
import Bugtracker.BugTracker.repository.RoleRepository;
import Bugtracker.BugTracker.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email){
       return userRepository.findByEmailAddress(email);
    }

    public User findUserByFirstName(String firstName){
        return userRepository.findByFirstName(firstName).orElseThrow(()->new UsernameNotFoundException("NOT FOUND"));
    }

    public User saveUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByRole("ADMIN");
        List<Role> listRoles = new ArrayList<>();
        listRoles.add(userRole);
        user.setRoles(listRoles);
        return userRepository.save(user);
    }
}
