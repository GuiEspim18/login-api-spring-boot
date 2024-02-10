package com.api.loginApi.infra.exceptions.dto;

import org.springframework.validation.FieldError;

public record ErrorsValidationDTO (
        String field,
        String message
) {
    public ErrorsValidationDTO(FieldError field) {
        this(field.getField(), field.getDefaultMessage());
    }
}
