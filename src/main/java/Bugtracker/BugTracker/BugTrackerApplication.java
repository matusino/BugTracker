package Bugtracker.BugTracker;

import Bugtracker.BugTracker.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
//@ComponentScan(basePackages = {"C:\\Users\\Matus\\IdeaProjects\\BugTracker\\src\\main\\java\\Bugtracker\\BugTracker\\configuration"})
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class BugTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BugTrackerApplication.class, args);
	}
//	@Bean
//	public BCryptPasswordEncoder bCryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

}
//Okej takze potrbujem vytvorit uvodnu stranku ktora bude visible prevsetkyhc
//s tym ze tam bude moznost login, a register ked nemas acc
//toto mozes zobrat z toho jedneho prikladu co som nasiel aby tam bolo to male login
//potom vyrenderujes s thyme leafom tu page proste aby tam boli ine buttony ked bude prihlaseny nikto iny
//sprav to tak ze admin je len jeden a kazdy kto re registruje je automaticky user
//ale admin im moze dat aj ine prava a vytvorit novych userov

//https://docs.spring.io/spring-security/site/docs/current/guides/html5/form-javaconfig.html
//https://www.youtube.com/watch?v=IgbhfRv4ZyQ
// este dopln nech vyhlada by mail, cize si dopln mail do entity a potom sak vies
//sprav vsetko , a potom security implentuje az ako poslednu aby sa to lahsie vytvaralo


//skus spravit mozno nejaku metodu ktora v adminskom rozhrani spravi s usera ADMINA ze mu nastavit inu rolu