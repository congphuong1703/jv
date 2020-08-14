package social.network.springboot.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import social.network.springboot.DTO.UserDTO;
import social.network.springboot.DTO.UserPasswordDTO;
import social.network.springboot.Entities.Users;
import social.network.springboot.Entities.VerificationToken;
import social.network.springboot.Enums.EnumStatus;
import social.network.springboot.Listener.OnForgotPasswordSuccessEvent;
import social.network.springboot.Listener.OnRegistrationSuccessEvent;
import social.network.springboot.Services.UserService;
import social.network.springboot.Services.VerificationTokenService;

import javax.validation.Valid;
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

	@RequestMapping(value = "/forgot_password",method = RequestMethod.POST)
	public String forgotPassword(@Valid @ModelAttribute("userObj") Users userObj, BindingResult bindingResult, Model model, WebRequest request){
		Users user = userService.findByEmail(userObj.getEmail());
		if(user == null)
		{
			bindingResult.rejectValue("email","email.notExists");
			logger.info("Forgot password not find email: " + userObj.getEmail());
			return "forgot_password";
		}
		try {
			String appUrl = request.getContextPath();
			eventPublisher.publishEvent(new OnForgotPasswordSuccessEvent(user, request.getLocale(), appUrl));
		} catch (Exception re) {
			logger.info(re.getMessage());//error while sending confirmation email
		}
		logger.info("Send email forgot password success " + user.getUsername());
		return "redirect:/login";
	}
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String save(@Validated @ModelAttribute("userObj") UserDTO userObj, BindingResult bindingResult, WebRequest request) {
		Users users = userService.findByUsername(userObj.getUserName());

		if (users != null) {
			bindingResult.rejectValue("userName", "username.exists");
			logger.info("There is already an account with this user: " + userObj.getUserName());
			return "register";
		} else if (userService.findByEmail(userObj.getEmail()) != null) {
			bindingResult.rejectValue("userName", "email.exists");
			logger.info("There is already an account with this email: " + userObj.getEmail());
			return "register";
		}
		if (bindingResult.hasErrors())
			return "register";
		users = userService.registerUser(userObj);
		try {
			String appUrl = request.getContextPath();
			eventPublisher.publishEvent(new OnRegistrationSuccessEvent(users, request.getLocale(), appUrl));
		} catch (Exception e){
			logger.info(e.getMessage());
		}
		logger.info("Registration success by " + users.getUsername());
		return "redirect:/login";
	}

	@RequestMapping(value = "/confirm_registration")
	public String confirmRegistration(WebRequest request, Model model, @RequestParam("token") String token) {
		VerificationToken verificationToken = verificationTokenService.findByToken(token);
		Locale locale = request.getLocale();

		boolean checkConfirm = userService.confirmEmail(verificationToken);

		if (!checkConfirm) {
			String message = messages.getMessage("auth.invalidToken", null, locale);
			model.addAttribute("message", message);
			return "access_denied";
		}
		Users users = verificationToken.getUser();
		users.setStatus(EnumStatus.ACTIVE);
		return "redirect:/login";

	}

	@RequestMapping(value = "/reset_password")
	public String confirmForgotPassword(WebRequest request, Model model, @RequestParam("token") String token) {
		VerificationToken verificationToken = verificationTokenService.findByToken(token);
		boolean checkConfirm = userService.confirmEmail(verificationToken);
		Users users = verificationToken.getUser();
		UserPasswordDTO userDTO = new UserPasswordDTO();
		userDTO.setId(users.getId());
		Locale locale = request.getLocale();

		if (!checkConfirm) {
			String message = messages.getMessage("auth.invalidToken", null, locale);
			model.addAttribute("message", message);
			return "access_denied";
		}
		model.addAttribute("userObj",userDTO);
		return "reset_password";
	}

	@RequestMapping(value = "/reset_password",method = RequestMethod.POST)
	public String updatePassword(@Valid @ModelAttribute("userObj") UserPasswordDTO userObj,BindingResult bindingResult, Model model, WebRequest request){
		String resetSuccess = messages.getMessage("resetPasswordSuccess",null,request.getLocale());
		userService.updatePassword(userObj);
		model.addAttribute("resetSuccess",resetSuccess);
		return "reset_password";
	}
}
