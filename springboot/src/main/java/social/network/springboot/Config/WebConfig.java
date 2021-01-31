package social.network.springboot.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.number.NumberFormatAnnotationFormatterFactory;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.filter.CharacterEncodingFilter;
import social.network.springboot.Entities.Users;
import social.network.springboot.Repositories.UserRepository;


@Configuration
public class WebConfig {

	//register account
	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return (args) -> {
			Users user1 = new Users("user", "$2a$10$PJgmnQcYPAHtkDX8wzCSYeP8CNAstpVLH0PkHK7Bz9r.ZLz5Or2Wu", "user@gmail.com", "USER");
			Users user2 = new Users("admin", "$2a$10$PJgmnQcYPAHtkDX8wzCSYeP8CNAstpVLH0PkHK7Bz9r.ZLz5Or2Wu", "congphuong@gmail.com", "ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);
		};
	}

	//register encode
	@Bean
	CharacterEncodingFilter characterEncodingFilter() {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		return filter;
	}

	@Bean
	public FormattingConversionService conversionService() {

		// Use the DefaultFormattingConversionService but do not register defaults
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(false);

		// Ensure @NumberFormat is still supported
		conversionService.addFormatterForFieldAnnotation(new NumberFormatAnnotationFormatterFactory());

		// Register date conversion with a specific global format
		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		registrar.setFormatter(new DateFormatter("ddMMyyyy"));
		registrar.registerFormatters(conversionService);

		return conversionService;
	}

	@Bean
	public LocalValidatorFactoryBean validator() {
		return new LocalValidatorFactoryBean();
	}

}
