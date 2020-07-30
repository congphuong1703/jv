package social.network.springboot.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.NoHandlerFoundException;

@Controller
public class ErrorController {


	@ExceptionHandler(NoHandlerFoundException.class)
	public String handle(Exception ex) {
		return "redirect:/access-denied";//this is view name
	}

	@RequestMapping(value = "/access-denied")
	public String accessDenied() {
		return "access_denied";
	}
}
