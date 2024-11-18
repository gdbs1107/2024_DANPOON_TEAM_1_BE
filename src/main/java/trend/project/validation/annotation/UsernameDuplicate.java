package trend.project.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import trend.project.validation.validator.UsernameDuplicateValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UsernameDuplicateValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface UsernameDuplicate {

    String message() default "중복된 ID 입니다";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
