package social.network.springboot.Validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

@Constraint(validatedBy = PhoneNumberValidator.class)
@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ReportAsSingleViolation
public @interface PhoneNumberValid {

	String message() default "not correct format phone number in Viet Nam";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
