package social.network.springboot.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import social.network.springboot.DTO.UserDTO;
import social.network.springboot.Entities.Users;
import social.network.springboot.Entities.VerificationToken;
import social.network.springboot.Enums.EnumStatus;
import social.network.springboot.Listener.OnRegistrationSuccessEvent;
import social.network.springboot.Services.UserService;
import social.network.springboot.Services.VerificationTokenService;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;
import java.util.logging.Logger;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Autowired
	private VerificationTokenService verificationTokenService;

	@Autowired
	private MessageSource messages;

	private Logger logger = Logger.getLogger(getClass().getName());

	@RequestMapping("/register")
	public String register(Model model) {
		UserDTO userDto = new UserDTO();
		model.addAttribute("userObj", userDto);
		return "register";
	}

	@RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET)
	public String userProfile(@PathVariable(value = "id") Long userId, Model model) {
		Users user = userService.getUserById(userId);
		model.addAttribute("user", user);
		return "userProfile";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("usersObj") UserDTO userObj, BindingResult bindingResult, WebRequest request) {
		Users users = userService.findByUsername(userObj.getUserName());
		Locale locale = request.getLocale();
		String mesUserExists = messages.getMessage("user.exists", null, locale);
		String mesEmailExists = messages.getMessage("email.exists", null, locale);
//		userValidator.validate(userObj, bindingResult);

		if (users != null) {

			bindingResult.rejectValue("username", "error.userexists", mesUserExists);
			logger.info("There is already an account with this user: " + userObj.getUserName());
			return "register";
		} else if (userService.findByUsername(userObj.getUserName()) != null) {

			bindingResult.rejectValue("username", "error.userexists", mesEmailExists);
			logger.info("There is already an account with this email: " + userObj.getEmail());
			return "register";
		}

		if (bindingResult.hasErrors())
			return "register";
		users = userService.registerUser(userObj);
		try {
			String appUrl = request.getContextPath();
			eventPublisher.publishEvent(new OnRegistrationSuccessEvent(users, request.getLocale(),appUrl));
		}catch(Exception re) {
			userService.deleteByUsername(users.getUsername());
			logger.info(re.getMessage());
//			throw new Exception("Error while sending confirmation email");
		}
		logger.info("Registration success by " + users.getUsername());
		return "redirect:/login";
	}

	@RequestMapping(value = "/confirmRegistration")
	public String confirmRegistration(WebRequest request, Model model, @RequestParam("token") String token) {
		Locale locale = request.getLocale();
		VerificationToken verificationToken = verificationTokenService.findByToken(token);
		if (verificationToken == null){
			String message = messages.getMessage("auth.invalidToken", null, locale);
			model.addAttribute("message", message);
			return "redirect:access-denied";
		}
		Users users = verificationToken.getUser();
		Calendar calendar = Calendar.getInstance();
		if((verificationToken.getExpiryDate().getTime() - calendar.getTime().getTime()) <= 0){
			String message = messages.getMessage("auth.expired", null, locale);
			model.addAttribute("message", message);
			return "redirect:access-denied";
		}
		users.setStatus(EnumStatus.ACTIVE);
		return null;
	}
}
