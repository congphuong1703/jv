package social.network.springboot.Controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import social.network.springboot.Entities.Users;

@Controller
public class HomeController {

	@RequestMapping(value = "/login")
	public String login(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			return "redirect:/home";
		}
		return "login";
	}

	@RequestMapping(value = {"/home","/",""})
	public String home(){
		return "home";
	}

	@RequestMapping(value = "/forgot_password")
	public String forgotPassword(Model model){
		model.addAttribute("userObj",new Users());
		return "forgot_password";
	}

	@RequestMapping(value = "/access-denied")
	public String accessDenied(Model model){
		return "access_denied";
	}

	@RequestMapping(value = "/admin")
	public String admin(){
		return "admin/admin";
	}
}
