package social.network.springboot.Listener;

import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import social.network.springboot.Entities.Users;
import social.network.springboot.Services.VerificationTokenService;
import freemarker.template.Template;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class RegistrationEmailListener implements ApplicationListener<OnRegistrationSuccessEvent> {

	@Autowired
	private VerificationTokenService verificationTokenService;

	@Autowired
	private MessageSource messages;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private Configuration configuration;

	@Override
	public void onApplicationEvent(OnRegistrationSuccessEvent event) {
		this.confirmRegistration(event);
	}

	private void confirmRegistration(OnRegistrationSuccessEvent event) {
		Users users = event.getUsers();
		String token = UUID.randomUUID().toString();
		verificationTokenService.createVerificationToken(users, token);
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		StringBuffer content = new StringBuffer();

		//get email to form register
		String recipient = users.getEmail();
		String subject = "Registration Confirmation";

		String url = "http://localhost:9090" + event.getAppUrl() + "/confirmRegistration?token=" + token;
		String message = messages.getMessage("registrationSuccessConfirmationLink", null, event.getLocale());
//		String htmlMess = "<br><center><a href= \"" + url + "\""
//			   + "style=\"padding : 10px; border-radius : 5px; border : 1px solid blue; background-color : blue; color = black;\">"
//			   + "Xác nhận</a></center>";
//		String resultMes = "<body><b>" + message + "</b>" + htmlMess + "</body>";
		Map<String, Object> model  = new HashMap<>();
		model.put("firstName","Cong Phuong");
		model.put("lastName","Cong Phuong");
		model.put("location", "Pune");
		model.put("signature", "www.technicalkeeda.com");
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, "utf-8");
			Template template = configuration.getTemplate("register.txt");
			content.append(FreeMarkerTemplateUtils.processTemplateIntoString(template,model));

			helper.setText(content.toString(),true);
			helper.setTo(recipient);
			helper.setSubject(subject);

			mailSender.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
