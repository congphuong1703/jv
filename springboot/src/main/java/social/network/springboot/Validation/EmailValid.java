package social.network.springboot.Validation;



import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

@Constraint(validatedBy = EmailValidator.class)
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ReportAsSingleViolation
public @interface EmailValid {

	String message() default "Wrong data of string field";

	String messageNotEmpty() default "Field can't be empty";

	boolean notEmpty() default false;

	Class<?>[] groups() default {};

	Class<?>[] payload() default {};
}
