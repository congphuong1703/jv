package social.network.springboot.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import social.network.springboot.ServiceImps.UserServiceImp;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserServiceImp userServiceImp;

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
			   .antMatchers("/home", "/", "/resources/**", "/css/**", "/register", "/confirm_registration","/forgot_password","/reset_password").permitAll()// Cho phép tất cả mọi người truy cập vào  địa chỉ này
			   .antMatchers("/admin/**").hasRole("ADMIN")
			   .and()
			   .authorizeRequests()
			   .anyRequest().authenticated()
			   .and()
			   .formLogin() // Cho phép người dùng xác thực bằng form login
			   .loginPage("/login")
			   .successForwardUrl("/home")
			   .permitAll() // Tất cả đều được truy cập vào địa chỉ này
			   .and()
			   .rememberMe()
			   .and()
			   .logout() // Cho phép logout
			   .permitAll()
			   .and()
			   .exceptionHandling().accessDeniedPage("/access-denied");
	}
}
