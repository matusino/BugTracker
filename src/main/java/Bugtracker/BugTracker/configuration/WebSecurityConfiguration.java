package Bugtracker.BugTracker.configuration;

import Bugtracker.BugTracker.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService myUserDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public WebSecurityConfiguration(MyUserDetailsService myUserDetailsService, BCryptPasswordEncoder passwordEncoder) {
        this.myUserDetailsService = myUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService)
                .passwordEncoder(passwordEncoder);

    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/list-of-all-bugs").hasRole("ADMIN")
                    .antMatchers("/").permitAll()
                    .antMatchers("/registration").permitAll()
                    .and().formLogin().defaultSuccessUrl("/main", true)
                .loginPage("/login")
                    .usernameParameter("email")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();

    }
}
