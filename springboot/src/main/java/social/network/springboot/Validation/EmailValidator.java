package social.network.springboot.Validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailValid, String> {

	private Pattern pattern;
	private Matcher matcher;
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		   + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private Boolean notEmpty;
	private String messageNotEmpty;
	private String message;

	@Override
	public void initialize(EmailValid email) {
		notEmpty = email.notEmpty();
		messageNotEmpty = email.messageNotEmpty();
		message = email.message();
	}

	@Override
	public boolean isValid(final String email, final ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();

		if (notEmpty && email.isEmpty() || email == null) {
			context.buildConstraintViolationWithTemplate(messageNotEmpty).addConstraintViolation();
			return false;
		}

		context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}

}