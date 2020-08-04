package social.network.springboot.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import social.network.springboot.Entities.Users;
import social.network.springboot.Repositories.UserRepository;

@Configuration
public class WebConfig {

	@Bean
	CommandLineRunner init(UserRepository urepository) {
		return (args) -> {
			Users user1 = new Users("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "user@gmail.com","USER");
			Users user2 = new Users("admin", "$2a$08$bCCcGjB03eulCWt3CY0AZew2rVzXFyouUolL5dkL/pBgFkUH9O4J2","congphuong.vietnam62@gmail.com","ADMIN");
			urepository.save(user1);
			urepository.save(user2);
		};
	}

	@Bean
	CharacterEncodingFilter characterEncodingFilter() {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		return filter;
	}

}
