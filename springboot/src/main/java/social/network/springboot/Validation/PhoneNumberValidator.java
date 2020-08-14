package social.network.springboot.Validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberValid,String> {

	private Pattern pattern;
	private Matcher matcher;
	private static final String PHONE_NUMBER_PATTERN = "(09|03|05)+([0-9]{8})\b";

	@Override
	public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
		pattern = Pattern.compile(PHONE_NUMBER_PATTERN);
		if(phoneNumber != null)
		matcher = pattern.matcher(phoneNumber);
		return matcher.matches();
	}
}
