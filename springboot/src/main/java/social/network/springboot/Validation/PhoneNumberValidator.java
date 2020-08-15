package social.network.springboot.Validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberValid,String> {

	private Pattern pattern;
	private Matcher matcher;
	private static final String PHONE_NUMBER_PATTERN = "(09|03|05)+([0-9]{8})\b";
	private Boolean notEmpty;
	private String messageNotEmpty;
	private String message;

	@Override
	public void initialize(PhoneNumberValid phoneNumberValid) {
		notEmpty = phoneNumberValid.notEmpty();
		messageNotEmpty = phoneNumberValid.messageNotEmpty();
		message = phoneNumberValid.message();
	}

	@Override
	public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();

		if (notEmpty && phoneNumber.isEmpty() || phoneNumber == null) {
			context.buildConstraintViolationWithTemplate(messageNotEmpty).addConstraintViolation();
			return false;
		}
		context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

		pattern = Pattern.compile(PHONE_NUMBER_PATTERN);
		matcher = pattern.matcher(phoneNumber);
		return matcher.matches();
	}
}
