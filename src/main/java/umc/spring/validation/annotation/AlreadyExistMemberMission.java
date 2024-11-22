package umc.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.validation.validator.MemberMissionAlreadyExistValidator;

import java.lang.annotation.*;

// 이미 도전중/도전완료 미션인지 유무
@Documented
@Constraint(validatedBy = MemberMissionAlreadyExistValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface AlreadyExistMemberMission {

    String message() default "이미 도전중/잰행완료인 미션입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
