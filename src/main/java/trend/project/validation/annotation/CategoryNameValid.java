package trend.project.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import trend.project.validation.validator.CategoryNameValidator;
import trend.project.validation.validator.UsernameDuplicateValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CategoryNameValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface CategoryNameValid {

    String message() default "존재하지 않은 카테고리입니다";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}

