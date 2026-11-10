package com.ps.flowwork_backend.common.validator;

import com.ps.flowwork_backend.common.annotations.ValidDateRange;
import com.ps.flowwork_backend.project_service.model.entity.Project;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateRangeValidator
        implements ConstraintValidator<ValidDateRange, Project> {

    @Override
    public boolean isValid(Project entity, ConstraintValidatorContext context) {

        if (entity.getStartDate() == null || entity.getEndDate() == null) {
            return true;
        }

        return !entity.getEndDate().isBefore(entity.getStartDate());
    }
}