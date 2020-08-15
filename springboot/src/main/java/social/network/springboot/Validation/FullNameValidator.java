package social.network.springboot.Validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FullNameValidator implements ConstraintValidator<FullNameValid,String> {

	private Pattern pattern;
	private Matcher matcher;
	private static final String FULLNAME_PATTERN = "^[a-zA-Z ]*$";
	private Boolean notEmpty;
	private String messageNotEmpty;
	private String message;
	private String messageLength;
	private Integer min;
	private Integer max;

	@Override
	public void initialize(FullNameValid fullNameValid) {
		notEmpty = fullNameValid.notEmpty();
		messageNotEmpty = fullNameValid.messageNotEmpty();
		message = fullNameValid.message();
		messageLength = fullNameValid.messageLength();
		min = fullNameValid.min();
		max = fullNameValid.max();
	}

	@Override
	public boolean isValid(final String fullName, final ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();

		if (fullName.length() < min || fullName.length() > max) {
			context.buildConstraintViolationWithTemplate(messageLength).addConstraintViolation();
			return false;
		}

		if (notEmpty && fullName.isEmpty() || fullName == null) {
			context.buildConstraintViolationWithTemplate(messageNotEmpty).addConstraintViolation();
			return false;
		}

		context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

		pattern = Pattern.compile(FULLNAME_PATTERN);
		matcher = pattern.matcher(fullName);
		return matcher.matches();
	}
}
