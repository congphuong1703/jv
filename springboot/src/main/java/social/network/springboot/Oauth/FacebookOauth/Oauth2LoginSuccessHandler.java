package social.network.springboot.Oauth.FacebookOauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import social.network.springboot.Entities.Users;
import social.network.springboot.Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class Oauth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Autowired
	private UserService userService;

	/**
	 * Calls the parent class {@code handle()} method to forward or redirect to the target
	 * URL, and then calls {@code clearAuthenticationAttributes()} to remove any leftover
	 * session data.
	 *
	 * @param request
	 * @param response
	 * @param authentication
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		FacebookOauth2User facebookOauth2User = (FacebookOauth2User) authentication.getPrincipal();
		String email = facebookOauth2User.getEmail();
		String fullName = facebookOauth2User.getFullName();
		String username = facebookOauth2User.getUsername();
		Users user = userService.findByEmail(email);
		if(user == null){

		}
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
