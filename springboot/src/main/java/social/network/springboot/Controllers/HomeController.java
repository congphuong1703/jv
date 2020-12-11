package social.network.springboot.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import social.network.springboot.DTO.UserDTO;
import social.network.springboot.Entities.Users;
import social.network.springboot.Services.RelationshipService;
import social.network.springboot.Services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@Autowired
	private RelationshipService relationshipService;

	@RequestMapping(value = "/login")
	public String login(Model model) {
//
//		//check role
//		//comment are wrong
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			return "redirect:/home";
		}
		return "login";
	}

	@RequestMapping(value = {"/home", "/", ""})
	public String home(Model model, HttpServletRequest request) {
		Users currentUser = userService.findByUsername(request.getUserPrincipal().getName());
		List<Users> usersList = userService.getAllUser();
		int follower = relationshipService.countByUserRelated(currentUser);

		model.addAttribute("listFriendSuggest", usersList);
		model.addAttribute("follower", follower);
		model.addAttribute("currentUser", currentUser);
		return "home";
	}

	@RequestMapping(value = "/forgot_password")
	public String forgotPassword(Model model) {
		model.addAttribute("userObj", new UserDTO());
		return "forgot_password";
	}

	@RequestMapping(value = "/access-denied")
	public String accessDenied(Model model) {
		return "access_denied";
	}

	@RequestMapping(value = "/admin")
	public String admin() {
		return "admin/admin";
	}

	@RequestMapping(value = "/terms")
	public String term(Model model) {
		return "terms";
	}
}
