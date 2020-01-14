package Bugtracker.BugTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BugTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BugTrackerApplication.class, args);
	}

}
//Okej takze potrbujem vytvorit uvodnu stranku ktora bude visible prevsetkyhc
//s tym ze tam bude moznost login, a register ked nemas acc
//toto mozes zobrat z toho jedneho prikladu co som nasiel aby tam bolo to male login
//potom vyrenderujes s thyme leafom tu page proste aby tam boli ine buttony ked bude prihlaseny nikto iny
//sprav to tak ze admin je len jeden a kazdy kto re registruje je automaticky user
//ale admin im moze dat aj ine prava a vytvorit novych userov