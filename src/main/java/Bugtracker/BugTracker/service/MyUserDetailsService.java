package Bugtracker.BugTracker.service;

import Bugtracker.BugTracker.model.Role;
import Bugtracker.BugTracker.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public MyUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {
        User user = userService.findUserByFirstName(firstName);
        List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
        return buildUserForAuthentication(user,authorities);
    }

    private List<GrantedAuthority> getUserAuthority(List<Role> userRoles){
        Set<GrantedAuthority> roles = new HashSet<>();
        for(Role role : userRoles){
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> grantedAuthorities){
        return new org.springframework.security.core.userdetails.User(user.getFirstName(), user.getPassword(),true,true,true,true,grantedAuthorities);
    }
}
