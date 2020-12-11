package social.network.springboot.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import social.network.springboot.ServiceImps.FacebookOauth2UserServiceImp;
import social.network.springboot.ServiceImps.UserServiceImp;
import social.network.springboot.Services.FacebookOauth2UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserServiceImp userServiceImp;

	@Autowired
	private FacebookOauth2UserServiceImp facebookOauth2UserServiceImp;

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userServiceImp); //set the custom user details service
		auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
		auth.setHideUserNotFoundExceptions(false);
		return auth;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		// Password encoder, để Spring Security sử dụng mã hóa mật khẩu người dùng
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userServiceImp).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
			   .authorizeRequests()
			   .antMatchers("/home", "/", "/register", "/confirm_registration", "/forgot_password", "/reset_password", "/terms", "/oauth2/**")
			   .permitAll()// Cho phép tất cả mọi người truy cập vào  địa chỉ này
//			   .antMatchers("/admin/**").hasRole("ADMIN")
//			   .antMatchers("/users/**").authenticated()
			   .and()
			   .authorizeRequests()
			   .anyRequest()
			   .permitAll()
			   .and()
			   .formLogin() // allow user authentication with login page
			   .loginPage("/login")
			   .usernameParameter("email")
			   .defaultSuccessUrl("/home")
			   .permitAll()
			   .and()
			   .oauth2Login()
			   .loginPage("/login")
			   .userInfoEndpoint().userService(facebookOauth2UserServiceImp)
			   .and()
			   .and()
			   .rememberMe().key("jremember")
			   .and()
			   .logout() // allow logout
			   .permitAll()
			   .and()
			   .exceptionHandling().accessDeniedPage("/access-denied")
			   .and()
			   .headers().defaultsDisabled()
			   .addHeaderWriter(new StaticHeadersWriter("Cache-Control", " no-cache,max-age=0, must-revalidate"))
			   .addHeaderWriter(new StaticHeadersWriter("Expires", "0"));
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			   .ignoring()
			   .antMatchers("/resources/**", "/static/**", "/webfonts/**", "/css/**", "/js/**", "/images/**", "/template/**");
	}


}
