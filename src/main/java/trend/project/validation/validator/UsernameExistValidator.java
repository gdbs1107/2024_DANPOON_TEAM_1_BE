package trend.project.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import trend.project.api.code.status.ErrorStatus;
import trend.project.repository.MemberRepository;
import trend.project.validation.annotation.UsernameDuplicate;
import trend.project.validation.annotation.UsernameExist;

@Component
@RequiredArgsConstructor
public class UsernameExistValidator implements ConstraintValidator<UsernameExist, String> {

    private final MemberRepository memberRepository;

    @Override
    public void initialize(UsernameExist constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {

        boolean isValid = memberRepository.existsByUsername(username);

        if (isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MEMBER_USERNAME_DUPLICATE.getMessage()).addConstraintViolation();
        }

        return !isValid;

    }
}
