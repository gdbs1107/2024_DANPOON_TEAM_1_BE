package trend.project.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import trend.project.domain.enumClass.Category;
import trend.project.validation.annotation.CategoryNameValid;
import trend.project.validation.annotation.UsernameDuplicate;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class CategoryNameValidator implements ConstraintValidator<CategoryNameValid, String> {


    @Override
    public void initialize(CategoryNameValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }


    @Override
    public boolean isValid(String categoryName, ConstraintValidatorContext constraintValidatorContext) {
        // Null 또는 빈 문자열은 유효하지 않음
        if (categoryName == null || categoryName.trim().isEmpty()) {
            return false;
        }

        // Category 열거형 이름과 일치하는지 확인
        try {
            Category.valueOf(categoryName); // Enum 이름과 일치하는지 체크
            return true; // 일치하면 유효
        } catch (IllegalArgumentException e) {
            return false; // 일치하지 않으면 유효하지 않음
        }
    }

}
