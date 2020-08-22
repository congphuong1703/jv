package social.network.springboot.Listener;

import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import social.network.springboot.Entities.Users;
import social.network.springboot.Services.VerificationTokenService;
import freemarker.template.Template;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class RegistrationEmailListener implements ApplicationListener<VerificationURLEvent> {

	@Autowired
	private VerificationTokenService verificationTokenService;

	@Autowired
	private MessageSource messages;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private Configuration configuration;

	@Override
	public void onApplicationEvent(VerificationURLEvent event) {
		this.confirmRegistration(event);
	}


	private void confirmRegistration(VerificationURLEvent event) {
		Users users = event.getUsers();
		String token = UUID.randomUUID().toString();
		//get email to form register
		verificationTokenService.createVerificationToken(users, token);
		String recipient = users.getEmail();
		String subject = "Registration Confirmation";

		String url = "http://localhost:9090" + event.getAppUrl() + "/confirm_registration?token=" + token;
		Map<String, Object> model = new HashMap<>();
		model.put("firstName", "Phuong");
		model.put("lastName", "Cong ");
		model.put("location", "Pune");
		model.put("signature", url);

		MimeMessage email = this.sendEmail("register.txt", model, recipient, subject);
		mailSender.send(email);
	}

	public MimeMessage sendEmail(String temp, Map<String,Object> model, String recipient, String subject) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		StringBuffer content = new StringBuffer();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, "utf-8");

			Template template = configuration.getTemplate(temp);
			content.append(FreeMarkerTemplateUtils.processTemplateIntoString(template, model));

			helper.setText(content.toString(), true);
			helper.setTo(recipient);
			helper.setSubject(subject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mimeMessage;
	}
}
