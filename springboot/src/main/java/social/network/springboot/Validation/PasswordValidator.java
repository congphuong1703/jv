package social.network.springboot.Validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<PasswordValid,String> {

	private Pattern pattern;
	private Matcher matcher;
	private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
	private Boolean notEmpty;
	private String messageNotEmpty;
	private String message;

	//At least one upper case character (A-Z)
	//At least one lower case character (a-z)
	//At least one digit (0-9)
	//At least one special character (Punctuation)
	//Password should not start with a digit
	//Password should not end with a special character

	@Override
	public void initialize(PasswordValid passwordValid) {
		notEmpty = passwordValid.notEmpty();
		messageNotEmpty = passwordValid.messageNotEmpty();
		message = passwordValid.message();
	}

	@Override
	public boolean isValid(final String password, final ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();

		if (notEmpty && password.isEmpty() || password == null) {
			context.buildConstraintViolationWithTemplate(messageNotEmpty).addConstraintViolation();
			return false;
		}
		context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

		pattern = Pattern.compile(PASSWORD_PATTERN);
		matcher = pattern.matcher(password);
		return matcher.matches();
	}
}
