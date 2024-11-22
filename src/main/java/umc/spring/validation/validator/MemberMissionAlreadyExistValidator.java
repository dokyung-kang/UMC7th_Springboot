package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPaylaod.code.status.ErrorStatus;
import umc.spring.service.MemberMissionService.MemberMissionQueryService;
import umc.spring.validation.annotation.AlreadyExistMemberMission;

import java.util.List;

// 이미 도전중/도전 완료한 미션인지
@Component
@RequiredArgsConstructor
public class MemberMissionAlreadyExistValidator implements ConstraintValidator<AlreadyExistMemberMission, Long> {

    private final MemberMissionQueryService memberMissionQueryService;

    @Override
    public void initialize(AlreadyExistMemberMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        boolean isValid = memberMissionQueryService.existMissionForMember(value, 1L);   // 유저 하드 코딩

        if (isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MEMBER_MISSION_ALREADY_EXIST.toString()).addConstraintViolation();
            return false;
        }

        return !isValid;
    }
}