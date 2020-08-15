package social.network.springboot.Validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UsernameValidator implements ConstraintValidator<UsernameValid, String> {

	private Pattern pattern;
	private Matcher matcher;
	private static final String Regex = "[a-zA-Z](?!.*\\.\\.)[a-zA-Z.\\d]{5,19}$";
	private Boolean notEmpty;
	private Integer min;
	private Integer max;
	private String messageNotEmpty;
	private String messageLength;
	private String message;

//	Length: 6 - 20 characters
//	Must start with letter a-zA-Z
//	Can contains a-zA-Z0-9 and dot(.)
//	Can't have 2 consecutive dots

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
		if (username.length() < min || username.length() > max) {
			context.buildConstraintViolationWithTemplate(messageLength).addConstraintViolation();
			return false;
		}
		context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

		pattern = Pattern.compile(Regex);
		matcher = pattern.matcher(username);
		return matcher.matches();
	}
}
