package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPaylaod.code.status.ErrorStatus;
import umc.spring.service.RegionService.RegionQueryService;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.validation.annotation.ExistRegion;

@Component
@RequiredArgsConstructor
public class RegionExistValidator implements ConstraintValidator<ExistRegion, Long> {

    private final RegionQueryService regionQueryService;

    @Override
    public void initialize(ExistRegion constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long regionId, ConstraintValidatorContext context) {
        if (regionId == null) {
            return false;
        }

        boolean isValid = regionQueryService.findRegion(regionId).isPresent();

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.REGION_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;

    }
}