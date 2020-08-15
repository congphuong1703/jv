package social.network.springboot.Validation;

import javax.validation.Constraint;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FullNameValidator.class)
@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface FullNameValid {

	String message() default "Full name not correct format";

	String messageNotEmpty() default "Field can't be empty";

	String messageLength() default "Size no correct";

	boolean notEmpty() default false;

	int min() default 6;

	int max() default 30;

	Class<?>[] groups() default {};

	Class<?>[] payload() default {};
}
