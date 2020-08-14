package social.network.springboot.Validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UsernameValidator implements ConstraintValidator<UsernameValid, String> {

	private Pattern pattern;
	private Matcher matcher;
	private static final String Regex = "[a-zA-Z0-9\\\\._\\\\-]{3,}";
	private Boolean notEmpty;
	private Integer min;
	private Integer max;
	private String messageNotEmpty;
	private String messageLength;
	private String message;

	@Override
	public void initialize(UsernameValid username) {
		notEmpty = username.notEmpty();
		min = username.min();
		max = username.max();
		messageNotEmpty = username.messageNotEmpty();
		messageLength = username.messageLength();
		message = username.message();
	}

	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();
		if (notEmpty && username.isEmpty() || username == null) {
			context.buildConstraintViolationWithTemplate(messageNotEmpty).addConstraintViolation();
			return false;
		}
		if ((min > 0 || max < Integer.MAX_VALUE) && (username.length() < min || username.length() > max)) {
			context.buildConstraintViolationWithTemplate(messageLength).addConstraintViolation();
			return false;
		}
lo
		pattern = Pattern.compile(Regex);

		if (username != null) {
			matcher = pattern.matcher(username);
			return matcher.matches();
		}
		context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
		return false;
	}
}
