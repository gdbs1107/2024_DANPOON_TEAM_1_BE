package trend.project.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import trend.project.validation.validator.UsernameDuplicateValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UsernameDuplicateValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface UsernameExist {

    String message() default "존재하지 않는 username입니다";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
