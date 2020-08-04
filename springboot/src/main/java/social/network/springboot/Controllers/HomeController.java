package social.network.springboot.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import social.network.springboot.Entities.Users;

@Controller
public class HomeController {

	@RequestMapping(value = "/login")
	public String login(Model model){
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

	@RequestMapping(value = "/admin")
	public String admin(){
		return "admin/admin";
	}
}
