package social.network.springboot.Listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import social.network.springboot.Entities.Users;
import social.network.springboot.Services.VerificationTokenService;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class ForgotPasswordEmailListener implements ApplicationListener<ForgotPasswordEvent> {

	@Autowired
	private VerificationTokenService verificationTokenService;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private RegistrationEmailListener registrationEmailListener;

	@Override
	public void onApplicationEvent(ForgotPasswordEvent event) {
		this.confirmResetPassword(event);
	}

	public void confirmResetPassword(ForgotPasswordEvent event){
		Users users = event.getUsers();
		String token = UUID.randomUUID().toString();
		verificationTokenService.createVerificationToken(users,token);

		String recipient = users.getEmail();
		String subject = messageSource.getMessage("confirmResetPassword",null,event.getLocale());

		String url = "http://localhost:9090" + event.getAppUrl() + "/reset_password?token=" + token;
		Map<String, Object> model = new HashMap<>();
		model.put("signature", url);

		MimeMessage email = registrationEmailListener.sendEmail("reset_password.txt", model, recipient, subject);
		mailSender.send(email);
	}

}
