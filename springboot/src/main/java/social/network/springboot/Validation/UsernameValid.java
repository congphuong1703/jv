package social.network.springboot.Validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

@Documented
@ReportAsSingleViolation
@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsernameValidator.class)
public @interface UsernameValid {

	String message() default "Wrong data of string field";

	String messageNotEmpty() default "Field can't be empty";

	String messageLength() default "Wrong length of field";

	boolean notEmpty() default false;

	int min() default 6;

	int max() default 20;

	Class<?>[] groups() default {};

	Class<?>[] payload() default {};
}
