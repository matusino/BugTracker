package Bugtracker.BugTracker.service;

import Bugtracker.BugTracker.model.Role;
import Bugtracker.BugTracker.model.User;
import Bugtracker.BugTracker.repository.RoleRepository;
import Bugtracker.BugTracker.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
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
       return userRepository.findByEmail(email).orElse(null);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public User findUserByFirstName(String firstName){
        return userRepository.findByFirstName(firstName).orElseThrow(()->new UsernameNotFoundException("NOT FOUND"));
    }

    public List<User> listOfUsers(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public User saveUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByRole("PROJECT_MANAGER");
        List<Role> listRoles = new ArrayList<>();
        listRoles.add(userRole);
        user.setRoles(listRoles);
        return userRepository.save(user);
    }

    public void updateUser(User user, User userDB){
        userDB.setFirstName(user.getFirstName());
        userDB.setLastName(user.getLastName());
        userDB.setPhoneNumber(user.getPhoneNumber());
    }
    public User saveNewUser(User user){
        return userRepository.save(user);
    }

    public List<User> findByProjectId(Long projectId){
        return userRepository.findByProjectList_Id(projectId);
    }

    @Transactional
    public boolean isEmailAlreadyInUse(String email){
        boolean emailInDb = true;
        User userDB = userRepository.findByEmail(email).orElse(null);

        if(userDB == null ) emailInDb = false;
        return emailInDb;
    }
}
